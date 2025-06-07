/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VehiculoWebController {

    @RequestMapping("/vehiculos")
    public String page() {
        return "vehiculos"; // Retorna la vista vehiculos.html desde /templates/
    }
}
