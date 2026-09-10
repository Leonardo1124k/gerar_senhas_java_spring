package com.painelsenhas.controller;

import com.painelsenhas.dto.SenhaStatusDTO;
import com.painelsenhas.factory.SenhaCreator;
import com.painelsenhas.factory.SenhaNormalCreator;
import com.painelsenhas.factory.SenhaPrioritariaCreator;
import com.painelsenhas.service.QueueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/senhas")
public class SenhaApiController {

    // Usa a mesma instância Singleton utilizada pelos outros controllers.
    private final QueueService queueService = QueueService.getInstance();

    @PostMapping("/gerar/normal")
    public String gerarSenhaNormal() {
        return gerarSenha(new SenhaNormalCreator());
    }

    @PostMapping("/gerar/prioritaria")
    public String gerarSenhaPrioritaria() {
        return gerarSenha(new SenhaPrioritariaCreator());
    }

    // Método geral de geração que recebe a abstração SenhaCreator.
    private String gerarSenha(SenhaCreator creator) {
        String novaSenha = queueService.generateTicket(creator);
        queueService.callNext(novaSenha);
        return novaSenha;
    }

    // Retorna o estado completo do painel em uma única requisição.
    @GetMapping
    public SenhaStatusDTO getStatus() {
        return new SenhaStatusDTO(
                queueService.getLastTicket(),
                queueService.getHistory()
        );
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
