package com.TLU.SoundVerse.repository;

import com.TLU.SoundVerse.entity.MusicsOfPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MusicsOfPlaylistRepository extends JpaRepository<MusicsOfPlaylist, Integer> {
    List<MusicsOfPlaylist> findByAlbumsId(Integer albumsId);
}
