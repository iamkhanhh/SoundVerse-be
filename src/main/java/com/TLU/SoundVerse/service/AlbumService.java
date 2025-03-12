package com.TLU.SoundVerse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TLU.SoundVerse.entity.Album;
import com.TLU.SoundVerse.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumService {
  AlbumRepository albumRepository;

  public List<Album> getAlbumsByUserId(Integer userId) {
    List<Album> albums = albumRepository.findByArtistId(userId);
    return albums;
  }
}
