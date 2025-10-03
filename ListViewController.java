//package MusicPlayer;
package com.MusicPlayer.controllers;

import com.MusicPlayer.models.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class ListViewController {
    
    @FXML private TableView<Song> songsTable;
    @FXML private TableColumn<Song, String> titleColumn;
    @FXML private TableColumn<Song, String> artistColumn;
    @FXML private TableColumn<Song, String> albumColumn;
    @FXML private TableColumn<Song, String> durationColumn;
    
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    
    public void initialize() {
        // Set up table columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        // Load sample songs (in real app, you'd scan a directory)
        loadSampleSongs();
        
        songsTable.setItems(songs);
        
        // Double click to play song
        songsTable.setRowFactory(tv -> {
            TableRow<Song> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Song selectedSong = row.getItem();
                    switchToPlayerView(selectedSong);
                }
            });
            return row;
        });
    }
    
    private void loadSampleSongs() {
        // Add sample songs - replace with actual file paths
        songs.add(new Song("Sample Song 1", "Artist 1", "Album 1", "3:45", "path/to/song1.mp3"));
        songs.add(new Song("Sample Song 2", "Artist 2", "Album 2", "4:20", "path/to/song2.mp3"));
        songs.add(new Song("Sample Song 3", "Artist 3", "Album 3", "2:55", "path/to/song3.mp3"));
        
        // In a real application, you would scan a directory for audio files:
        // scanMusicDirectory(new File("path/to/music/folder"));
    }
    
    private void scanMusicDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> 
                name.toLowerCase().endsWith(".mp3") || 
                name.toLowerCase().endsWith(".wav") ||
                name.toLowerCase().endsWith(".m4a"));
            
            if (files != null) {
                for (File file : files) {
                    // Extract metadata from file (simplified)
                    String fileName = file.getName().replaceFirst("[.][^.]+$", "");
                    songs.add(new Song(fileName, "Unknown Artist", "Unknown Album", "0:00", file.getAbsolutePath()));
                }
            }
        }
    }
    
    private void switchToPlayerView(Song song) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/musicplayer/views/PlayerView.fxml"));
            Parent root = loader.load();
            
            PlayerViewController playerController = loader.getController();
            playerController.setSong(song);
            
            Stage stage = (Stage) songsTable.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Music Player - Now Playing");
            
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load player view.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}