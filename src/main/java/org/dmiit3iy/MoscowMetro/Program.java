package org.dmiit3iy.MoscowMetro;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {
            getHTML(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getHTML(String url) throws IOException {
        ArrayList<Line> lineArrayList = new ArrayList<>();
        ArrayList<Station> stationArrayList = new ArrayList<>();
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements elements = document.getElementsByClass("js-metro-line");
        for (Element x : elements) {
            Line line = new Line();
            line.setId(x.attr("data-line"));
            line.setName(x.text());
            lineArrayList.add(line);
        }
        System.out.println(lineArrayList);
        Elements elements1 = document.getElementsByClass("js-metro-stations");
        for (Element x : elements1) {
            String idLine = x.attr("data-line");
            System.out.println(x.attr("data-line"));

            Line line = lineArrayList.stream().filter(b -> b.getId().equals(idLine)).findFirst().get();

            ArrayList<Station> stations = new ArrayList<>();
            Elements elements2 = x.getElementsByAttribute("data-metrost");
            for (Element q : elements2) {
                stations.add(new Station(q.text(), line));
            }
            line.setStations(stations);
        }
        for (Line z : lineArrayList) {
            System.out.println(z);
            System.out.println();
        }
    }

    public static void toJason(ArrayList<Line> lines){

    }

}
