package org.dmiit3iy.DubaiAirport;

import java.time.LocalTime;
import java.util.Objects;

public class Airport {
    private String name;

    private String code;
    private String indexOfFlight;
    private LocalTime arrivalTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndexOfFlight() {
        return indexOfFlight;
    }

    public void setIndexOfFlight(String indexOfFlight) {
        this.indexOfFlight = indexOfFlight;
    }

    public LocalTime getTime() {
        return arrivalTime;
    }

    public void setTime(LocalTime time) {
        this.arrivalTime = time;
    }

    public Airport(String name, String code, String indexOfFlight, LocalTime time) {
        this.name = name;
        this.code = code;
        this.indexOfFlight = indexOfFlight;
        this.arrivalTime = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;
        return Objects.equals(name, airport.name) && Objects.equals(code, airport.code) && Objects.equals(indexOfFlight, airport.indexOfFlight) && Objects.equals(arrivalTime, airport.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, indexOfFlight, arrivalTime);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", indexOfFlight='" + indexOfFlight + '\'' +
                ", time=" + arrivalTime +
                '}';
    }
}
