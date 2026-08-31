package com.painelsenhas.service;

import com.painelsenhas.factory.SenhaCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueService {

    // 1. Atributo estático privado que guarda a única instância da classe
    private static QueueService uniqueInstance;

    // Atributos de negócio da classe alterados para String
    private String currentTicket;
    private final List<String> calledTickets;

    // 2. Construtor privado
    private QueueService() {
        this.calledTickets = new ArrayList<>();
    }

    // 3. Método estático público: o único ponto de acesso global à instância.
    // Double-checked locking mantido.
    public static QueueService getInstance() {
        if (uniqueInstance == null) {
            synchronized (QueueService.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new QueueService();
                }
            }
        }
        return uniqueInstance;
    }

    // Métodos de negócio atualizados para usar Factory e String

    public synchronized String generateTicket(SenhaCreator creator) {
        // A Factory cria o tipo correto e gera o número do ticket
        this.currentTicket = creator.criarSenha().gerarTicket();
        return this.currentTicket;
    }

    public synchronized String getLastTicket() {
        return this.currentTicket != null ? this.currentTicket : "-";
    }

    public synchronized void callNext(String ticket) {
        this.calledTickets.add(ticket);
    }

    public synchronized List<String> getHistory() {
        // Cópia defensiva mantida
        return Collections.unmodifiableList(new ArrayList<>(calledTickets));
    }

    public synchronized void reset() {
        this.currentTicket = null;
        this.calledTickets.clear();
    }
}