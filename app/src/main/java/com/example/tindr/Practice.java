package com.example.tindr;

public class Practice {
    private double lat, lon;
    private Phone[] phones;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setPhones(Phone[] phones) {
        this.phones = phones;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Phone[] getPhones() {
        return phones;
    }
}
