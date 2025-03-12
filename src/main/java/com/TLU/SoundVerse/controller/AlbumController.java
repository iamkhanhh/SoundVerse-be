package com.TLU.SoundVerse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TLU.SoundVerse.dto.response.ApiResponse;
import com.TLU.SoundVerse.entity.Album;
import com.TLU.SoundVerse.service.AlbumService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@RestController
@RequestMapping("album")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumController {
  AlbumService albumService;

  @GetMapping("get-albums-by-userid")
  public ApiResponse<List<Album>> checkAdmin(HttpServletRequest request) {
    @SuppressWarnings("unchecked")
    Map<String, Object> user = (Map<String, Object>) request.getAttribute("user");
    Integer id = Integer.parseInt(String.valueOf(user.get("id")));

    List<Album> albums = albumService.getAlbumsByUserId(id);

    ApiResponse<List<Album>> apiResponse = new ApiResponse<List<Album>>();

    apiResponse.setStatus("success");
    apiResponse.setMessage("Login successfilly");
    apiResponse.setData(albums);
    return apiResponse;
  }
}