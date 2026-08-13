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
public record SenhaStatusDTO(int currentTicket, List<Integer> history) {
}
