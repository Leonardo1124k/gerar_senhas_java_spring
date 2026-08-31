package com.painelsenhas.controller;

import com.painelsenhas.factory.SenhaCreator;
import com.painelsenhas.factory.SenhaNormalCreator;
import com.painelsenhas.factory.SenhaPrioritariaCreator;
import com.painelsenhas.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senhas")
public class SenhaApiController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/gerar/normal")
    public String gerarSenhaNormal() {
        return gerarSenha(new SenhaNormalCreator());
    }

    @PostMapping("/gerar/prioritaria")
    public String gerarSenhaPrioritaria() {
        return gerarSenha(new SenhaPrioritariaCreator());
    }

    // Método geral de geração que recebe a abstração SenhaCreator
    private String gerarSenha(SenhaCreator creator) {
        String novaSenha = queueService.generateTicket(creator);
        queueService.callNext(novaSenha);
        return novaSenha;
    }

    @GetMapping("/historico")
    public List<String> getHistorico() {
        return queueService.getHistory();
    }

    @GetMapping("/atual")
    public String getSenhaAtual() {
        return queueService.getLastTicket();
    }
}