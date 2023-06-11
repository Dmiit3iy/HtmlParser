package org.dmiit3iy.MoscowMetro;

import java.util.Objects;

public class Station {
    private String nameStation;
    private Line lineName;

    public Station(String nameStation, Line lineName) {
        this.nameStation = nameStation;
        this.lineName = lineName;
    }

    public Station() {
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public Line getLineName() {
        return lineName;
    }

    public void setLineName(Line lineName) {
        this.lineName = lineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station station)) return false;
        return Objects.equals(nameStation, station.nameStation) && Objects.equals(lineName, station.lineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameStation, lineName);
    }

    @Override
    public String toString() {
        return nameStation;
    }
}
