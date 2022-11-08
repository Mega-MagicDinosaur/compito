package com.verifica.server;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        Ticket t1 = new Ticket("palco-1");
        Ticket t2 = new Ticket("palco-2");
        Ticket t3 = new Ticket("palco-3");
        Ticket t4 = new Ticket("palco-4");
        Ticket t5 = new Ticket("palco-5");

        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(t1);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        tickets.add(t5);

        Server server = new Server(tickets);
        server.listen(5500);
    }
}
