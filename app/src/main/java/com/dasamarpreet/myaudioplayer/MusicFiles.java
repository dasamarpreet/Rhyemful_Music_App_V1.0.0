package com.dasamarpreet.myaudioplayer;
//
//It is a class that is used to get and set the path, title, album, artist, duration and id of the song.
//        It is used to access information of each song.

public class MusicFiles {
    private String path;
    private String title;
    private String album;
    private String artist;
    private String duration;
    private String id;

    public MusicFiles(String path, String title, String album, String artist, String duration, String id) {
        this.path = path;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MusicFiles() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
