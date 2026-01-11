package com.example.splitapp.common.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaForwardingController {

    /**
     * Redirect all requests that are not files related
     * and were not served by '/api' controllers
     */
    @RequestMapping(value = "{path:[^\\.]*}")
    public String redirect() {
        // Forward to index.html, so React can take control
        return "forward:/index.html";
    }
}
