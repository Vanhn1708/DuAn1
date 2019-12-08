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

    public String music() {
        return name;
    }

    public String getMusic() {
        return name;
    }

    public void setMusic(String music) {
        this.name = music;
    }

    public void setName(String music) {
        this.name = music;
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
