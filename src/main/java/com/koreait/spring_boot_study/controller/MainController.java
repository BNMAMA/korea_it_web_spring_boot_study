package com.koreait.spring_boot_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
class UserDto{
    private int uerId;
    private String username;
    private int age;
}

//controller => SSR
//즉 서버쪽에서 웹페이지를 렌더링해서 반환하는 SSR

@Controller
public class MainController {
    private List<UserDto> users = new ArrayList<>();


    //이러한 방식은 정적 웹페이지를 보여주는 것
    //데이터 즉 동적인 요소가 없는 정적 웹페이지
    @GetMapping("/main")
    public String getMain() {
        return "main.html";
    }

    //SSR 에 동적을 추가하면 Thymeleaf를 적용하면 된다
    // html파일은 템플릿 패키지 폴더에 있어야 한다
    // Thymeleaf
    //서버에서 HTML을 렌더링 할 때 , 자바 데이터를 끼워넣을 수 있게 해주는 템플릿 엔진

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("username", "<b>김백년</b>");
        model.addAttribute("isAdult", true);
        model.addAttribute("age", 24);
        Map<String, String> userList = new HashMap<>();
        userList.put("김소현", "26");
        userList.put("김성엽", "30");
        userList.put("김백년", "4");

        return "profile.html";
    }

    @GetMapping("/serch")
    public String getSearch(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "search.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit (@RequestParam String name, @RequestParam int age, Model model) {
        UserDto userDto = new UserDto(users.size() + 1, name, age);
        users.add(userDto);
        model.addAttribute("message", name + "님, 가입을 환영합니다");
        return "signup-result";
    }
}
