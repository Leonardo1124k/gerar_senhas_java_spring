package com.painelsenhas.controller;

import com.painelsenhas.factory.SenhaNormalCreator;
import com.painelsenhas.factory.SenhaPrioritariaCreator;
import com.painelsenhas.service.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("senhaAtual", QueueService.getInstance().getLastTicket());
        return "atendente";
    }

    @PostMapping("/gerar/normal")
    public String gerarNormal() {
        String novaSenha = QueueService.getInstance().generateTicket(new SenhaNormalCreator());
        QueueService.getInstance().callNext(novaSenha);
        return "redirect:/atendente";
    }

    @PostMapping("/gerar/prioritaria")
    public String gerarPrioritaria() {
        String novaSenha = QueueService.getInstance().generateTicket(new SenhaPrioritariaCreator());
        QueueService.getInstance().callNext(novaSenha);
        return "redirect:/atendente";
    }
}