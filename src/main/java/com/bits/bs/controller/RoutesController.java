package com.bits.bs.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class RoutesController {
//    TODO: Add comments mambala iwe
    @GetMapping("/")
    public String index() {
        return "index";
    }

//
    @GetMapping("/books/new")
    public String booksForm() {
        return "books/create";
    }

}
