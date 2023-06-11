package org.dmiit3iy.MoscowMetro;

import org.dmiit3iy.MoscowMetro.Station;

import java.util.ArrayList;
import java.util.Objects;

public class Line {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private ArrayList<Station> stations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public Line() {
    }

    public Line(String name) {
        this.name = name;
    }

    public Line(String name, ArrayList<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line line)) return false;
        return Objects.equals(name, line.name) && Objects.equals(stations, line.stations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stations);
    }

    @Override
    public String toString() {
        return "Линия " +name+"-"+
                 id + ", станции:" + stations;
    }
}
