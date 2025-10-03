//package MusicPlayer;

package com.musicplayer.models;

import javafx.beans.property.*;

public class Song {
    private final StringProperty title;
    private final StringProperty artist;
    private final StringProperty album;
    private final StringProperty duration;
    private final StringProperty filePath;
    
    public Song(String title, String artist, String album, String duration, String filePath) {
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.duration = new SimpleStringProperty(duration);
        this.filePath = new SimpleStringProperty(filePath);
    }
    
    // Getters
    public String getTitle() { return title.get(); }
    public String getArtist() { return artist.get(); }
    public String getAlbum() { return album.get(); }
    public String getDuration() { return duration.get(); }
    public String getFilePath() { return filePath.get(); }
    
    // Property getters
    public StringProperty titleProperty() { return title; }
    public StringProperty artistProperty() { return artist; }
    public StringProperty albumProperty() { return album; }
    public StringProperty durationProperty() { return duration; }
    public StringProperty filePathProperty() { return filePath; }
}