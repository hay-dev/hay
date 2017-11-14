package com.depromeet.hay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
