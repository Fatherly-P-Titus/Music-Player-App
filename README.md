# Music Player Application - Detailed Documentation

## Overview
A JavaFX-based music player application with a modern GUI that allows users to browse their music library and play audio files with full playback controls.

## Architecture

### Package Structure
```
com.MusicPlayer/
├── Main.java                    // Application entry point
├── controllers/
│   ├── ListViewController.java  // Library view controller
│   └── PlayerViewController.java // Player view controller
└── models/
    └── Song.java               // Data model for songs
```

## Component Breakdown

### 1. Main Application Class (`Main.java`)

**Purpose**: Application entry point and primary stage initialization

**Key Responsibilities**:
- Extends `javafx.application.Application`
- Initializes the main application window
- Loads the initial FXML view (ListView)
- Sets window properties and constraints

**Key Features**:
- Window title: "Music Player - Library"
- Initial dimensions: 800x600 pixels
- Minimum size: 600x400 pixels
- Loads `ListView.fxml` as the initial scene

### 2. Song Data Model (`Song.java`)

**Purpose**: Represents a music track with metadata

**Properties** (all using JavaFX StringProperty for data binding):
- `title`: Song title
- `artist`: Performing artist
- `album`: Album name
- `duration`: Track duration (formatted string)
- `filePath`: Absolute path to audio file

**Methods**:
- Constructor with all properties
- Standard getters for each property
- Property getters for JavaFX data binding

### 3. Library View Controller (`ListViewController.java`)

**Purpose**: Manages the song library browsing interface

#### UI Components
- `TableView<Song> songsTable`: Displays song list in tabular format
- Table columns: Title, Artist, Album, Duration
- Double-click functionality for song selection

#### Key Methods

**`initialize()`**:
- Sets up table column bindings using `PropertyValueFactory`
- Loads sample songs (placeholder implementation)
- Configures double-click row factory for playback

**`loadSampleSongs()`**:
- Adds placeholder songs with sample data
- Includes commented code for directory scanning functionality

**`scanMusicDirectory(File directory)`**:
- Scans specified directory for audio files (.mp3, .wav, .m4a)
- Creates Song objects from file metadata
- **Note**: Currently not implemented (commented out)

**`switchToPlayerView(Song song)`**:
- Transitions to player view when song is double-clicked
- Passes selected song to PlayerViewController
- Handles scene switching and window title update

### 4. Player View Controller (`PlayerViewController.java`)

**Purpose**: Manages music playback interface and controls

#### UI Components
- **Labels**: Song title, artist, album, current time, total time
- **Sliders**: Progress bar and volume control
- **Buttons**: Play/Pause, Stop, Previous, Next, Back to Library
- **ImageView**: Album art display

#### Core Functionality

**Media Playback**:
- Uses `MediaPlayer` and `Media` classes from JavaFX
- Supports play, pause, stop, and seek operations
- Volume control with slider

**Progress Tracking**:
- Real-time progress updates using `Timeline` animation
- Automatic progress slider synchronization
- Time formatting (MM:SS)

**UI Synchronization**:
- Dynamic button text (▶/❚❚) for play/pause states
- Real-time time label updates
- Album art display (placeholder image)

#### Key Methods

**`setSong(Song song)`**:
- Receives song from library view
- Initializes media player and updates UI

**`initializeMediaPlayer()`**:
- Creates Media object from file path
- Sets up event handlers for media readiness
- Configures progress tracking system

**Playback Controls**:
- `playPause()`: Toggles between play and pause states
- `stop()`: Stops playback and resets UI
- `seek()`: Handles progress slider drag events
- `adjustVolume()`: Updates media player volume

**Navigation**:
- `backToList()`: Returns to library view, stops playback
- `previousSong()`, `nextSong()`: Placeholder implementations

## Data Flow

### Application Launch
```
Main.start() → Load ListView.fxml → ListViewController.initialize()
```

### Song Selection
```
User double-clicks song → ListViewController.switchToPlayerView() 
→ PlayerViewController.setSong() → MediaPlayer initialization
```

### Playback Control
```
UI Events (button clicks, slider drags) → PlayerViewController methods 
→ MediaPlayer API calls → UI updates via property bindings
```

## Key Features

### 1. Library Management
- Tabular display of song metadata
- Sortable columns
- Double-click to play

### 2. Playback Controls
- Play/Pause/Stop functionality
- Progress seeking
- Volume adjustment
- Time display (current/total)

### 3. Navigation
- Seamless transition between library and player views
- Persistent window with updated titles

### 4. Extensibility
- Modular design for easy feature additions
- Placeholder methods for future enhancements

## Technical Implementation Details

### JavaFX Components Used
- **TableView**: Song list display with custom row factory
- **MediaPlayer**: Audio playback engine
- **Timeline**: Progress update animations
- **Property Binding**: UI-data synchronization
- **FXML**: View definition and controller injection

### Design Patterns
- **MVC (Model-View-Controller)**: Clear separation of concerns
- **Observer Pattern**: Property bindings for automatic UI updates
- **Factory Pattern**: Row factory for table view

## Current Limitations & Future Enhancement Areas

### 1. Music Library
- Sample data only (no real file scanning)
- Limited metadata extraction
- No playlist support

### 2. Playback Features
- No previous/next song implementation
- No repeat/shuffle modes
- Basic error handling

### 3. UI/UX
- Placeholder album art only
- No progress bar drag feedback
- Limited visual customization

### 4. Performance
- No media pre-loading
- Basic error handling for file loading

## File Dependencies

### Required Resources
- `ListView.fxml`: Library view layout
- `PlayerView.fxml`: Player view layout  
- `/images/default-album.png`: Default album art
- Audio files in supported formats (.mp3, .wav, .m4a)

### JavaFX Modules Required
- javafx.controls
- javafx.fxml  
- javafx.media
- javafx.base

## Developer Information
___
* Developer: **Fatherly P. Titus**
* Email: **fatherlytitus69@gmail.com**
* *copyright© 2025*
