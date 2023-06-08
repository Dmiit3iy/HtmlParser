package org.dmiit3iy.DubaiAirport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class AirPortsParser {
    public static void main(String[] args) {
        ArrayList<Airport> airports = new ArrayList<>();
        String url = "https://www.dubai-airport.xyz/en/arrivals/";
        try {
            Document document = Jsoup.connect(url).maxBodySize(0).get();
            Elements element = document.getElementsByClass("searchable");
            StringBuilder stringBuilder = new StringBuilder();
            element.stream().forEach(x -> stringBuilder.append(x.text()));
            String[] mass = stringBuilder.toString().split(">>");
            for (String x : mass) {
                airports.add(getAirport(x));
            }
           // System.out.println(airports);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Аэропорт из которого происходит максимальное количество рейсов в Дубай:");
        Map<String, Long> map =airports.stream().map(x->x.getName()).collect(Collectors.groupingBy(x->x, Collectors.counting()));
        String name = null;
        long count =0;
        for (var pair:map.entrySet()
             ) {
            if(pair.getValue()>count){
                count=pair.getValue();
                name = pair.getKey();
            }

        }
        System.out.println(name+" количество вылетов в Дубай "+count);

        System.out.println("Самый загруженный временной интервал:");
        Map<String, Long> map2 =airports.stream().map(x->String.valueOf(x.getTime().getHour())).collect(Collectors.groupingBy(x->x, Collectors.counting()));
        String hour= null;
        long count2 =0;
        for (var pair:map2.entrySet()
        ) {
            if(pair.getValue()>count2){
                count2=pair.getValue();
                hour = pair.getKey();
            }

        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H");
        System.out.println("В промежуток с "+hour+" до "+String.valueOf( LocalTime.parse(hour,formatter).plusHours(1))+" запланировано "+ count2+" прилетов");    }

    /**
     * A method for determining the airport's double encoding
     *
     * @param str
     * @return
     */
    public static boolean twoBracket(String str) {
        int x = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                x++;
            }
        }
        if (x > 1) {
            return true;
        }
        return false;
    }

    /**
     * Method of generating Airports from parsed HTML code
     *
     * @param str
     * @return
     */
    public static Airport getAirport(String str) {
        if (!twoBracket(str)) {
            String[] mass = str.split("\\(");
            String name = mass[0];
            String[] mass2 = mass[1].split("\\)");
            String code = mass2[0];
            String[] mass3 = mass2[1].split(" ");
            String indexOfFlight = mass3[1];
            LocalTime arrivalTime = LocalTime.parse(mass3[2]);
            return new Airport(name, code, indexOfFlight, arrivalTime);
        } else {
            String[] mass = str.split("\\(");
            String name = mass[0];
            String[] mass2 = mass[1].split("\\)");
            String code = mass2[0];
            String[] mass3 = mass[2].split(" ");
            String indexOfFlight = mass3[mass3.length - 4];
            LocalTime arrivalTime = LocalTime.parse(mass3[mass3.length - 3]);
           //System.out.println(name + " " + code + " " + indexOfFlight + " " + arrivalTime);
            return new Airport(name, code, indexOfFlight, arrivalTime);
        }
    }
}
