<div align="center">
<h1><kbd>🧩 AudioFilePlayer</kbd></h1>
An extension for MIT App Inventor 2.<br>
Extension component for AudioFilePlayer. Created using Rush.
</div>

## 📝 Specifications
* **
💾 **Size:** 9.74 KB
⚙️ **Version:** 1.3
📱 **Minimum API Level:** 26
📅 **Updated On:** [date=2025-01-14 timezone="Asia/Taipei"]
💻 **Built & documented using:** [FAST-CLI](https://community.appinventor.mit.edu/t/fast-an-efficient-way-to-build-extensions/129103?u=jewel)

## <kbd>Events:</kbd>
**AudioFilePlayer** has total 2 events.

### 💛 OnCompletion
Triggered when the audio finishes playing.

### 💛 OnError
Triggered when an error occurs during playback.

| Parameter | Type
| - | - |
| message | text

## <kbd>Methods:</kbd>
**AudioFilePlayer** has total 21 methods.

### 💜 PlayAudio
Play audio from the given file name in the device's environment.

| Parameter | Type
| - | - |
| fileName | text

### 💜 StopAudio
Stop audio playback.

### 💜 PauseAudio
Pause audio playback.

### 💜 ResumeAudio
Resume audio playback.

### 💜 SeekToPrevious
Seek backward by a specific duration (in milliseconds).

| Parameter | Type
| - | - |
| milliseconds | number

### 💜 Loop
Enable or disable looping. Pass true to enable, false to disable.

| Parameter | Type
| - | - |
| loop | boolean

### 💜 IsPlaying
Check if the audio is currently playing.

### 💜 GetDuration
Get the total duration of the audio in milliseconds.

### 💜 GetCurrentPosition
Get the current playback position in milliseconds.

### 💜 SeekTo
Seek to a specific position in milliseconds.

| Parameter | Type
| - | - |
| position | number

### 💜 SeekToEnd
Seek to end position of audio player

### 💜 SavePlaybackPosition
Save the current playback position.

### 💜 ResumeFromSavedPosition
Resume playback from the saved position.

### 💜 SetVolume
Set the volume level for playback. Range: 0.0 (mute) to 1.0 (maximum).

| Parameter | Type
| - | - |
| volume | number

### 💜 ToggleMute
Toggles mute

### 💜 SetPlaybackSpeed
Set the playback speed. Normal speed is 1.0. Slower: <1.0, Faster: >1.0.

| Parameter | Type
| - | - |
| speed | number

### 💜 GetArtist
Get the artist of the currently playing audio.

### 💜 GetAlbum
Get the album of the currently playing audio.

### 💜 GetTitle
Get the title of the currently playing audio.

### 💜 GetGenre
Get the genre of the currently playing audio.

### 💜 Volume
Gets volume of player

