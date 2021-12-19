package com.tourer.gui.map;

import java.util.Objects;

public class Location {
    String name;
    String description;
    Double longitude;
    Double latitude;

    public Location() {
    }

    public Location(String name, String description, Double longitude, Double latitude) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getlongitude() {
        return this.longitude;
    }

    public void setlongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Location name(String name) {
        setName(name);
        return this;
    }

    public Location description(String description) {
        setDescription(description);
        return this;
    }

    public Location longitude(Double longitude) {
        setlongitude(longitude);
        return this;
    }

    public Location latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(description, location.description) && longitude == location.longitude && latitude == location.latitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, longitude, latitude);
    }

    @Override
    public String toString() {
        return getName();
    }
    
}
