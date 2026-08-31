package com.painelsenhas.factory;
import com.painelsenhas.model.ISenha;
import com.painelsenhas.model.SenhaNormal;

public class SenhaNormalCreator extends SenhaCreator {
    @Override
    public ISenha criarSenha() {
        return new SenhaNormal();
    }
}