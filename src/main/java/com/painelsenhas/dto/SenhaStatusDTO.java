package com.painelsenhas.dto;

import java.util.List;

/**
 * DTO simples exposto pela API /api/senha, consumido via fetch() pelo
 * painel (Display) para atualizar a tela automaticamente, sem precisar de
 * recarregar a pagina.
 *
 * No projeto C# original, Display.razor era renderizado apenas uma vez no
 * servidor (sem @rendermode InteractiveServer) e por isso nao atualizava
 * sozinho. Esse endpoint resolve essa limitacao no lado Java.
 */
public class SenhaStatusDTO {
    private String senhaAtual;
    private List<String> historico;

    // Construtores
    public SenhaStatusDTO() {}

    public SenhaStatusDTO(String senhaAtual, List<String> historico) {
        this.senhaAtual = senhaAtual;
        this.historico = historico;
    }

    // Getters e Setters
    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }
}
