package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class HomeController {

/*
    @RequestMapping("/")
    public String home(){

        log.info("home controller");
        return "login/login";
   }*/

    @GetMapping("/test")
    public List<Map<String, Object>> list() {
        List<Map<String, Object>> boardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            Map<String, Object> board = new HashMap<>();
            board.put("title", "동화 이야기_" + num);
            board.put("content", "아름답고 신비한 도깨비 이야기" + num);
            board.put("author", "작가님_" + num);
            boardList.add(board);
        }
        return boardList;
    }
}
