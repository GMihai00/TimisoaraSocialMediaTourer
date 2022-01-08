package com.tourer.gui.map;

import java.util.Objects;
import java.util.Set;

public class Location {
    String name;
    String description;
    Double longitude;
    Double latitude;
    int likes;
    int dislikes;
    String photo;
    Set <String> userlikes;
    Set <String> userdislikes;
    

    public Location() {
    }

    public Location(String name, String description, Double longitude, Double latitude, int likes, int dislikes, String photo, Set<String> userlikes, Set<String> userdislikes) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.likes = likes;
        this.dislikes = dislikes;
        this.photo = photo;
        this.userlikes = userlikes;
        this.userdislikes = userdislikes;
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

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<String> getUserlikes() {
        return this.userlikes;
    }

    public void setUserlikes(Set<String> userlikes) {
        this.userlikes = userlikes;
    }

    public Set<String> getUserdislikes() {
        return this.userdislikes;
    }

    public void setUserdislikes(Set<String> userdislikes) {
        this.userdislikes = userdislikes;
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
        setLongitude(longitude);
        return this;
    }

    public Location latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    public Location likes(int likes) {
        setLikes(likes);
        return this;
    }

    public Location dislikes(int dislikes) {
        setDislikes(dislikes);
        return this;
    }

    public Location photo(String photo) {
        setPhoto(photo);
        return this;
    }

    public Location userlikes(Set<String> userlikes) {
        setUserlikes(userlikes);
        return this;
    }

    public Location userdislikes(Set<String> userdislikes) {
        setUserdislikes(userdislikes);
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
        return Objects.equals(name, location.name) && Objects.equals(description, location.description) && Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude) && likes == location.likes && dislikes == location.dislikes && Objects.equals(photo, location.photo) && Objects.equals(userlikes, location.userlikes) && Objects.equals(userdislikes, location.userdislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, longitude, latitude, likes, dislikes, photo, userlikes, userdislikes);
    }

    @Override
    public String toString() {
        return getName();
            
    }

    

}
