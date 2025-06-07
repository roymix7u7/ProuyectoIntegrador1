
package com.utp.integradorspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AlumnosController {
    @Autowired
    @RequestMapping("/alumnos")
    public String page() {
        return "home";
    }
}
