package com.svc.productshop.web.contlrolers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index1(){
        return super.view("index");
    }

}
