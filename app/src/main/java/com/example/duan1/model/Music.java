package com.example.duan1.model;

public class Music {
    public String name;
    public String path;
    public String artist;

    public Music(String name, String path, String artist) {
        this.name = name;
        this.path = path;
        this.artist = artist;
    }
    public Music() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
