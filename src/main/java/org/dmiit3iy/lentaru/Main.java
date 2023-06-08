package org.dmiit3iy.lentaru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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

          String[]mass= x.split("\\d\\d:\\d\\d");
            for (String z:mass
                 ) {
                System.out.println(z);

            }

        }

    }
}