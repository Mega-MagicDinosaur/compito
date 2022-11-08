package com.verifica.client;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        Client client = new Client();

        ArrayList<Ticket> arr = new ArrayList<>();
        Utils.deserializeJson(new Message(arr));

        boolean connected = client.connect("localhost", 5500);
        if (connected) { client.communicate(); } 
        else { System.out.println("connection failed!"); }
    }
}
