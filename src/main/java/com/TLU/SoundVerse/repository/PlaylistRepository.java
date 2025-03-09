package com.TLU.SoundVerse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TLU.SoundVerse.entity.Playlist;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findByUserId(Integer userId);
}

