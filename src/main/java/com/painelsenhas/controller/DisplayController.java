package com.painelsenhas.controller;

import com.painelsenhas.service.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/display")
public class DisplayController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("senhaAtual", QueueService.getInstance().getLastTicket());
        model.addAttribute("historico", QueueService.getInstance().getHistory());
        return "display";
    }
}