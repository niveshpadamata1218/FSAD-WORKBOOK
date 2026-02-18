package com.klu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.demo.service.CalciService;

@RestController
@RequestMapping("/calculator")
public class CalciController {

    @Autowired
    private CalciService service;

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return service.add(a, b);
    }

    @GetMapping("/subtract/{a}/{b}")
    public int sub(@PathVariable int a, @PathVariable int b) {
        return service.sub(a, b);
    }

    @GetMapping("/multiply/{a}/{b}")
    public int multiply(@PathVariable int a, @PathVariable int b) {
        return service.multiple(a, b);
    }q

    @GetMapping("/division")
    public double division(@RequestParam int a, @RequestParam int b) {
        return service.division(a, b);
    }

    @GetMapping("/modulo")
    public int modulo(@RequestParam int a, @RequestParam int b) {
        return service.module(a, b);
    }
}
