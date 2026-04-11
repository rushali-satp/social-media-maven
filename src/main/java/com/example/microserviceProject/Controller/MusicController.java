package com.example.microserviceProject.Controller;

import com.example.microserviceProject.Dto.MusicDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    @GetMapping("/music-list")
    public List<MusicDTO> getMusicList() {

        String url = "https://api.jamendo.com/v3.0/tracks?client_id=3c5d6407&format=json&limit=20&search=indian";

        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        List<MusicDTO> musicList = new ArrayList<>();

        for (Map<String, Object> item : results) {
            MusicDTO dto = new MusicDTO();
            dto.setId(item.get("id").toString());
            dto.setName(item.get("name").toString());
            dto.setArtist(item.get("artist_name").toString());
            dto.setAudio(item.get("audio").toString());
            dto.setImage(item.get("image").toString());

            musicList.add(dto);
        }

        return musicList;
    }
}
