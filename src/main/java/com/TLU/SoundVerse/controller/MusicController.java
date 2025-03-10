package com.TLU.SoundVerse.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TLU.SoundVerse.dto.request.CreateMusicDto;
import com.TLU.SoundVerse.dto.response.ApiResponse;
import com.TLU.SoundVerse.entity.Music;
import com.TLU.SoundVerse.service.MusicService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@RestController
@RequestMapping("music")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MusicController {
  MusicService musicService;

  @PostMapping
  ApiResponse<Music> createMusic(HttpServletRequest request, @RequestBody CreateMusicDto createMusicDto) {
    @SuppressWarnings("unchecked")
    Map<String, Object> user = (Map<String, Object>) request.getAttribute("user");
    Integer id = Integer.parseInt(String.valueOf(user.get("id")));
    System.out.println(createMusicDto);
    Music music = musicService.createMusic(createMusicDto, id);

    ApiResponse<Music> apiResponse = new ApiResponse<Music>();
    apiResponse.setStatus("success");
    apiResponse.setMessage("Upload Music successfilly");
    apiResponse.setData(music);
    return apiResponse;
  }
}
