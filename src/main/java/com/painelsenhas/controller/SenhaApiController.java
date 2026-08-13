package com.painelsenhas.controller;

import com.painelsenhas.dto.SenhaStatusDTO;
import com.painelsenhas.service.QueueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API REST simples usada pelo painel (/display) para se atualizar em tempo
 * quase real via polling (fetch a cada poucos segundos), sem exigir
 * WebSocket/SignalR.
 */
@RestController
@RequestMapping("/api/senha")
public class SenhaApiController {

    @GetMapping
    public SenhaStatusDTO status() {
        QueueService queueService = QueueService.getInstance();
        return new SenhaStatusDTO(queueService.getLastTicket(), queueService.getHistory());
    }
}
