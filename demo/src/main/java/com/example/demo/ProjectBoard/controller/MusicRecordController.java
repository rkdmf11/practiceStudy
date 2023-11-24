package com.example.demo.ProjectBoard.controller;

import com.example.demo.ProjectBoard.domain.Music;
import com.example.demo.ProjectBoard.dto.MusicDto;
import com.example.demo.ProjectBoard.dto.MusicRequestDto;
import com.example.demo.ProjectBoard.service.MusicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MusicRecordController {

    private final MusicRecordService musicRecordService;

    @Autowired
    public MusicRecordController(MusicRecordService musicRecordService) {
        this.musicRecordService = musicRecordService;
    }

    /**
     * 화면 이동
     *
     * @return
     */
    @GetMapping("/")
    public String home() {
        return "ProjectBoard/mainProjectBoard";
    }

    @GetMapping(value = "/listModify")
    public String saveForm() {
        return "ProjectBoard/musicCRUD";
    }

    /**
     * 내역 조회
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/listInquiry")
    public String musicList(Model model) {
        List<Music> music = musicRecordService.findMusics();
        model.addAttribute("musicList", music);
        return "ProjectBoard/musicList";  //music/musicList
    }

    /**
     * 내역 저장
     */
    @GetMapping("/music/new")
    public String createForm() {
        return "ProjectBoard/musicCRUD";
    }

    @PostMapping("/music/new")
    public String create(MusicRequestDto musicRequestDto) {
        System.out.println(musicRequestDto);
        musicRecordService.save(musicRequestDto);
        return "redirect:/";
    }
}
