package com.chryl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chr.yl on 2020/6/24.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/odd")
public class OrderController2 {

    @GetMapping("/api1/aaa")
    public boolean show() {
        return true;
    }

    @GetMapping("/api1/bbb")
    public boolean show2() {
        return true;

    }

    @GetMapping("/api2/demo")
    public boolean show3() {
        return true;

    }


    @GetMapping("/api2/aaa")
    public boolean show4() {
        return true;
    }
}
