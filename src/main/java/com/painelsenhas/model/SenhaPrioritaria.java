package com.painelsenhas.model;

public class SenhaPrioritaria implements ISenha {
    private static int contador = 1;

    @Override
    public String gerarTicket() {
        return "P-" + contador++;
    }
}