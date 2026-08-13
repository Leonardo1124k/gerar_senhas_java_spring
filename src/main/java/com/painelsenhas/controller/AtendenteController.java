package com.painelsenhas.controller;

import com.painelsenhas.service.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Equivalente a Components/Pages/Atendente.razor ("Painel de Controle -
 * Geracao de Senhas").
 *
 * No Blazor Server, o clique no botao chama GerarNovaSenha() diretamente no
 * servidor via SignalR (rendermode InteractiveServer). Aqui, o equivalente
 * em uma arquitetura MVC classica e um POST de formulario que aciona o
 * Singleton e redireciona de volta para a mesma pagina (padrao
 * Post/Redirect/Get).
 */
@Controller
@RequestMapping("/atendente")
public class AtendenteController {

    @GetMapping
    public String atendente(Model model) {
        // Acesso global via Singleton classico - igual ao C#:
        // QueueService.GetInstance().GetLastTicket()
        int currentDisplay = QueueService.getInstance().getLastTicket();
        model.addAttribute("currentDisplay", currentDisplay);
        return "atendente";
    }

    @PostMapping("/gerar")
    public String gerarNovaSenha() {
        // Acesso global via Singleton classico - igual ao C#:
        // QueueService.GetInstance().GenerateTicket() + CallNext(novaSenha)
        int novaSenha = QueueService.getInstance().generateTicket();
        QueueService.getInstance().callNext(novaSenha);
        return "redirect:/atendente";
    }
}
