package com.TLU.SoundVerse.service;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import com.TLU.SoundVerse.dto.response.ApiResponse;
import com.TLU.SoundVerse.entity.MusicsOfPlaylist;
import com.TLU.SoundVerse.entity.Playlist;
import com.TLU.SoundVerse.repository.MusicsOfPlaylistRepository;
import com.TLU.SoundVerse.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("hiding")
@Service
public class PlaylistService<HttpServletRequest> {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicsOfPlaylistRepository musicsOfPlaylistRepository;

    // ✅ Lấy danh sách Playlist của user (từ request)
    public ApiResponse<List<Playlist>> getUserPlaylists(HttpServletRequest request) {
        Integer userId = getUserIdFromRequest(request);
        List<Playlist> playlists = playlistRepository.findByUserId(userId);
        return new ApiResponse<>(200, "Danh sách Playlist", "SUCCESS", playlists);
    }

    // ✅ Tạo Playlist mới (gán userId từ request)
    public ApiResponse<Playlist> createPlaylist(Playlist playlist, HttpServletRequest request) {
        Integer userId = getUserIdFromRequest(request);
        playlist.setUserId(userId); // Gán userId
        Playlist savedPlaylist = playlistRepository.save(playlist);
        return new ApiResponse<>(201, "Playlist đã được tạo", "SUCCESS", savedPlaylist);
    }

    // ✅ Xoá Playlist (xoá thật luôn)
    public ApiResponse<Void> deletePlaylist(Integer playlistId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        if (playlistOpt.isPresent()) {
            playlistRepository.deleteById(playlistId);
            return new ApiResponse<>(200, "Playlist đã bị xoá", "SUCCESS", null);
        }
        return new ApiResponse<>(404, "Playlist không tồn tại", "FAILED", null);
    }

    // ✅ Thêm bài hát vào Playlist
    public ApiResponse<MusicsOfPlaylist> addMusicToPlaylist(Integer playlistId, Integer musicId) {
        MusicsOfPlaylist newMusic = new MusicsOfPlaylist();
        newMusic.setAlbumsId(playlistId);
        newMusic.setMusicId(musicId);
        musicsOfPlaylistRepository.save(newMusic);
        return new ApiResponse<>(201, "Bài hát đã được thêm vào Playlist", "SUCCESS", newMusic);
    }

    // ✅ Xoá bài hát khỏi Playlist
    public ApiResponse<Void> removeMusicFromPlaylist(Integer playlistId, Integer musicId) {
        List<MusicsOfPlaylist> musics = musicsOfPlaylistRepository.findByAlbumsId(playlistId);
        for (MusicsOfPlaylist music : musics) {
            if (music.getMusicId().equals(musicId)) {
                musicsOfPlaylistRepository.delete(music);
                return new ApiResponse<>(200, "Bài hát đã bị xoá khỏi Playlist", "SUCCESS", null);
            }
        }
        return new ApiResponse<>(404, "Bài hát không tồn tại trong Playlist", "FAILED", null);
    }

    // ✅ Helper: Lấy userId từ request
    private Integer getUserIdFromRequest(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Map<String, Object> user = (Map<String, Object>) ((ServletRequest) request).getAttribute("user");
    
        if (user == null || user.get("id") == null) {
            return null; // Tránh lỗi NullPointerException nếu không có user
        }
    
        try {
            return Integer.valueOf(String.valueOf(user.get("id"))); // Ép kiểu về Integer
        } catch (NumberFormatException e) {
            return null; // Tránh lỗi nếu id không phải số
        }
    }
    
}
