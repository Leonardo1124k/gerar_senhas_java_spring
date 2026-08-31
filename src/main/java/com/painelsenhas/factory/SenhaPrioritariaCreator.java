package com.painelsenhas.factory;
import com.painelsenhas.model.ISenha;
import com.painelsenhas.model.SenhaPrioritaria;

public class SenhaPrioritariaCreator extends SenhaCreator {
    @Override
    public ISenha criarSenha() {
        return new SenhaPrioritaria();
    }
}