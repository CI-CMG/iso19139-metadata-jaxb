package edu.colorado.cires.cmg.isometadata;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SchemaScraper {

  private static final String BASE_URL = "https://data.noaa.gov/resources/iso19139/";
  private static final Path LOCATION = Paths.get("src/main/resources/gov/noaa/data/iso19139");

  public static void downloadXsd(Path path) {
    try {
      FileUtils.copyURLToFile(new URL(BASE_URL + path.toString()), LOCATION.resolve(path).toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void getUrls(Path parent, Set<Path> urls) {
    String url = BASE_URL + (parent == null ? "" : parent.toString());
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    doc.select("a").stream()
        .map(a -> a.attr("href"))
        .forEach(href -> {
          if (href.endsWith(".xsd")) {
            urls.add(parent == null ? Paths.get(href) : parent.resolve(href));
          } else if (href.endsWith("/")) {
            getUrls(parent == null ? Paths.get(href) : parent.resolve(href), urls);
          }
        });
  }

  public static void main(String[] args) throws Exception {

    Set<Path> urls = new TreeSet<>();
    getUrls(null, urls);

    FileUtils.deleteQuietly(LOCATION.toFile());
    Files.createDirectories(LOCATION);

    urls.forEach(url ->  {
      System.out.println(url);
      downloadXsd(url);
    });

  }

}
