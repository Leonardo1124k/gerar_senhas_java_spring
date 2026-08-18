package com.painelsenhas.controller;

import com.painelsenhas.service.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A pagina carrega o estado inicial via server-side rendering (Thymeleaf) e,
 * a partir dai, se auto-atualiza via JavaScript consultando a
 * SenhaApiController (/api/senha) periodicamente.
 */
@Controller
public class DisplayController {

    @GetMapping("/display")
    public String display(Model model) {
        QueueService queueService = QueueService.getInstance();
        model.addAttribute("currentDisplay", queueService.getLastTicket());
        model.addAttribute("history", queueService.getHistory());
        return "display";
    }
}
