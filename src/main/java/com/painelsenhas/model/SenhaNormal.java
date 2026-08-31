package com.painelsenhas.model;

public class SenhaNormal implements ISenha {
    private static int contador = 1;

    @Override
    public String gerarTicket() {
        return "N-" + contador++;
    }
}