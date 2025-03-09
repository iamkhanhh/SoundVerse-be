package com.TLU.SoundVerse.controller;

import com.TLU.SoundVerse.dto.response.ApiResponse;
import com.TLU.SoundVerse.entity.MusicsOfPlaylist;
import com.TLU.SoundVerse.entity.Playlist;
import com.TLU.SoundVerse.service.PlaylistService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @SuppressWarnings("rawtypes")
    @Autowired
    private PlaylistService playlistService;

    // ✅ Lấy danh sách Playlist của user
    @GetMapping("/my")
    public ResponseEntity<?> getMyPlaylists(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        ApiResponse<List<Playlist>> response = playlistService.getUserPlaylists(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // ✅ Tạo Playlist mới
    @PostMapping("/create")
    public ResponseEntity<?> createPlaylist(@RequestBody Playlist playlist, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        ApiResponse<Playlist> response = playlistService.createPlaylist(playlist, request);
        return ResponseEntity.status(response.getCode()).body(response);    }

    // ✅ Xoá Playlist
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Integer id) {
        @SuppressWarnings("unchecked")
        ApiResponse<Void> response = playlistService.deletePlaylist(id);
        return ResponseEntity.status(response.getCode()).body(response);    }

    // ✅ Thêm bài hát vào Playlist
    @PostMapping("/{playlistId}/add-music/{musicId}")
    public ResponseEntity<?> addMusicToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId) {
        @SuppressWarnings("unchecked")
        ApiResponse<MusicsOfPlaylist> response = playlistService.addMusicToPlaylist(playlistId, musicId);
        return ResponseEntity.status(response.getCode()).body(response);    }

    // ✅ Xoá bài hát khỏi Playlist
    @DeleteMapping("/{playlistId}/remove-music/{musicId}")
    public ResponseEntity<?> removeMusicFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId) {
        @SuppressWarnings("unchecked")
        ApiResponse<Void> response = playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return ResponseEntity.status(response.getCode()).body(response);    }
}
