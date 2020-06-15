package bztf.shopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Home Controller
 * - Dieser Controller ist für die Root-Webpfad verantwortlich 
 */
@Controller // Das ist ein Controller
@RequestMapping("/") // Wir beantworten den Root-Webpfad
public class HomeController {

    // Automatische Ausführung/Instanzierung bei einem Aufruf
    @Autowired
    public HomeController() {
    }
    
    // GetMapping -> Wir lassen nur HTTP-GET auf localhost:8080 zu
    @GetMapping("index")
    public String showIndex() {
        return "index"; // wir wollen das Template index.html laden
    }
    
}