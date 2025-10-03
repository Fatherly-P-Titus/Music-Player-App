//package MusicPlayer;

package com.MusicPlayer.controllers;

import com.MusicPlayer.models.Song;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PlayerViewController {
    
    @FXML private Label songTitleLabel;
    @FXML private Label artistLabel;
    @FXML private Label albumLabel;
    @FXML private Label currentTimeLabel;
    @FXML private Label totalTimeLabel;
    @FXML private Slider progressSlider;
    @FXML private Slider volumeSlider;
    @FXML private Button playPauseButton;
    @FXML private ImageView albumArtImageView;
    
    private Song currentSong;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private Timeline progressTimeline;
    
    public void setSong(Song song) {
        this.currentSong = song;
        updateUI();
        initializeMediaPlayer();
    }
    
    private void updateUI() {
        songTitleLabel.setText(currentSong.getTitle());
        artistLabel.setText(currentSong.getArtist());
        albumLabel.setText(currentSong.getAlbum());
        
        // Set default album art (replace with actual album art loading)
        albumArtImageView.setImage(new Image(getClass().getResourceAsStream("/images/default-album.png")));
    }
    
    private void initializeMediaPlayer() {
        try {
            Media media = new Media(new File(currentSong.getFilePath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            // Set up media player event handlers
            mediaPlayer.setOnReady(() -> {
                totalTimeLabel.setText(formatTime(mediaPlayer.getTotalDuration()));
                volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            });
            
            // Set up progress updates
            setupProgressUpdates();
            
        } catch (Exception e) {
            showAlert("Error", "Could not load audio file: " + e.getMessage());
        }
    }
    
    private void setupProgressUpdates() {
        progressTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> updateProgress()));
        progressTimeline.setCycleCount(Timeline.INDEFINITE);
        
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            updateProgress();
        });
    }
    
    private void updateProgress() {
        if (mediaPlayer != null) {
            Duration currentTime = mediaPlayer.getCurrentTime();
            Duration totalTime = mediaPlayer.getTotalDuration();
            
            if (totalTime.greaterThan(Duration.ZERO)) {
                double progress = currentTime.toMillis() / totalTime.toMillis();
                progressSlider.setValue(progress * 100);
                currentTimeLabel.setText(formatTime(currentTime));
            }
        }
    }
    
    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    @FXML
    private void playPause() {
        if (mediaPlayer != null) {
            if (isPlaying) {
                mediaPlayer.pause();
                playPauseButton.setText("▶");
            } else {
                mediaPlayer.play();
                playPauseButton.setText("❚❚");
                progressTimeline.play();
            }
            isPlaying = !isPlaying;
        }
    }
    
    @FXML
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
            playPauseButton.setText("▶");
            progressTimeline.stop();
        }
    }
    
    @FXML
    private void seek() {
        if (mediaPlayer != null) {
            double seekPosition = progressSlider.getValue() / 100.0;
            Duration seekTime = mediaPlayer.getTotalDuration().multiply(seekPosition);
            mediaPlayer.seek(seekTime);
        }
    }
    
    @FXML
    private void adjustVolume() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
        }
    }
    
    @FXML
    private void backToList() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                progressTimeline.stop();
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/musicplayer/views/ListView.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) songTitleLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Music Player - Library");
            
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not return to library view.");
        }
    }
    
    @FXML
    private void previousSong() {
        // Implement previous song functionality
        showAlert("Info", "Previous song feature to be implemented");
    }
    
    @FXML
    private void nextSong() {
        // Implement next song functionality
        showAlert("Info", "Next song feature to be implemented");
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}