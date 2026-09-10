package com.painelsenhas.service;

import com.painelsenhas.factory.SenhaCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueService {

    // Instância única do Singleton.
    // volatile é necessário para que o double-checked locking seja seguro.
    private static volatile QueueService uniqueInstance;

    private String currentTicket;
    private final List<String> calledTickets;

    // Construtor privado: impede que outras classes criem instâncias diretamente.
    private QueueService() {
        this.calledTickets = new ArrayList<>();
    }

    // Único ponto de acesso à instância do Singleton.
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

    // Factory Method: recebe um SenhaCreator e gera o tipo de senha correspondente.
    public synchronized String generateTicket(SenhaCreator creator) {
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
        return Collections.unmodifiableList(new ArrayList<>(calledTickets));
    }

    public synchronized void reset() {
        this.currentTicket = null;
        this.calledTickets.clear();
    }
}
