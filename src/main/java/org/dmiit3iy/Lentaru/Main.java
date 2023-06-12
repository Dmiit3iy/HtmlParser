package org.dmiit3iy.Lentaru;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dmiit3iy.MoscowMetro.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        try {
            getPictures("https://lenta.ru/", "IMG");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Метод для получения списка новостей
     */
    public static void getNews(){
        ArrayList<String> newsArrayList = new ArrayList<>();
        String name = "https://lenta.ru/";
        try {
            Document document = Jsoup.connect(name).maxBodySize(0).get();
            //System.out.println(document);
            Elements element = document.getElementsByClass("topnews");
            for (Element x : element
            ) {
                newsArrayList.add(x.text());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String x : newsArrayList
        ) {

            String[] mass = x.split("\\d\\d:\\d\\d");
            for (String z : mass
            ) {
                System.out.println(z);

            }

        }
    }

    /**
     * Метод для парсинга изображений
     *
     * @param siteName
     * @param folderName
     * @throws IOException
     */
    public static void getPictures(String siteName, String folderName) throws IOException {
        HashSet<String> links = new HashSet<>();
        Document document = Jsoup.connect(siteName).maxBodySize(0).get();
        Elements elements = document.getElementsByTag("img");
        for (Element x : elements) {
            if (x.attr("src").matches("^https?://\\S+(?:jpg|jpeg|png)$")) {
                links.add(x.attr("src"));
            }
        }
        File folder = new File(folderName);
        folder.mkdir();
        for (String x : links) {
            String[] mass = x.split("/");
            String fileName = folder.getAbsolutePath() + "\\" + mass[mass.length - 1];
            System.out.println(fileName);
            try {
                loadIMG(x, fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void loadIMG(String urlSS, String fileName) throws IOException {
        URL url = new URL(urlSS);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] bytes = bufferedInputStream.readAllBytes();
            for (byte x : bytes) {
                fileOutputStream.write(x);
            }

        }
    }

}