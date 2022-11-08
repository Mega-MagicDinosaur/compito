package com.verifica.client;

import java.util.ArrayList;

public class Message {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Message() {}

    public Message(ArrayList<Ticket> _tickets) {
        tickets = _tickets;
    }

    public ArrayList<Ticket> getTickets() { return tickets; }
    public void setTickets(ArrayList<Ticket> _tickets) { tickets = _tickets; }
}
