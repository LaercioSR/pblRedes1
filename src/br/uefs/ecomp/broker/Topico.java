package br.uefs.ecomp.broker;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Iterator;

public class Topico {
    private String nome;
    private String valor;
    private ArrayList<DatagramPacket> subscribers;

    public Topico(String nome) {
        this.nome = nome;
        this.subscribers = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void addSubscriber(DatagramPacket subscriber) {
        subscribers.add(subscriber);
    }

    public Iterator iteratorSubscribers() {
        return subscribers.iterator();
    }
}
