package com.painelsenhas.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueService {

    // 1. Atributo estatico privado que guarda a unica instancia da classe
    private static QueueService uniqueInstance;

    // Atributos de negocio da classe
    private int currentTicket;
    private final List<Integer> calledTickets;

    // 2. Construtor privado: ninguem fora desta classe pode dar "new QueueService()"
    private QueueService() {
        this.currentTicket = 0;
        this.calledTickets = new ArrayList<>();
    }

    // 3. Metodo estatico publico: o unico ponto de acesso global a instancia.
    // Double-checked locking para inicializacao thread-safe com baixo overhead.
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

    // Metodos de negocio

    public synchronized int generateTicket() {
        this.currentTicket = this.currentTicket + 1;
        return this.currentTicket;
    }

    public synchronized int getLastTicket() {
        return this.currentTicket;
    }

    public synchronized void callNext(int ticket) {
        this.calledTickets.add(ticket);
    }

    public synchronized List<Integer> getHistory() {
        // Copia defensiva e imutavel: evita que quem consome a lista
        // altere o estado interno do Singleton por engano.
        return Collections.unmodifiableList(new ArrayList<>(calledTickets));
    }

    /**
     * Utilitario de apoio, sem equivalente direto no C#: reseta o estado do
     * painel. Util para testes/demonstracoes sem precisar reiniciar a
     * aplicacao.
     */
    public synchronized void reset() {
        this.currentTicket = 0;
        this.calledTickets.clear();
    }
}
