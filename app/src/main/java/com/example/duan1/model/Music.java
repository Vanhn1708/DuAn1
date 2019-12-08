package com.example.duan1.model;

public class Music {
    public String music;
    public String path;
    public String artist;

    public Music(String music, String path, String artist) {
        this.music = music;
        this.path = path;
        this.artist = artist;
    }

    public Music() {
    }

    public String music() {
        return music;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public void setName(String music) {
        this.music = music;
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
