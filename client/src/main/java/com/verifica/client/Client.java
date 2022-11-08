package com.verifica.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    
    private BufferedReader inputStream;
    private DataOutputStream outputStream;

    private final BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    public Client() {}

    public boolean connect(String _name, int _port) {
        try {
            socket = new Socket(_name, _port);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch(IOException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public void communicate() {
        write(new Message(new ArrayList<Ticket>()));
        Message availableTicketsMessage = read();
        ArrayList<Ticket> availableTickets = availableTicketsMessage.getTickets();

        System.out.println("available tickets:");
        System.out.println( availableTickets.toString() );

        System.out.println("write the tickets you want to buy like this: 1-5-3-...");
        String input = readKeyboard();

        String[] tickets_ids = input.split("-");
        ArrayList<Ticket> wantedTickets = new ArrayList<>();

        for (int i = 0; i < tickets_ids.length; i++) {
            wantedTickets.add(
                availableTickets.get(
                    Integer.parseInt(tickets_ids[i])-1 ) );    
        }

        write(new Message(wantedTickets));
        Message boughtTicketsMessage = read();
        ArrayList<Ticket> boughtTickets = boughtTicketsMessage.getTickets();

        System.out.println("you bought these tickets:");
        System.out.println(boughtTickets.toString());

        close();
    }

    public Message read() {
        try { return Utils.serializeJson(inputStream.readLine()); }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public void write(Message _message) {
        try { outputStream.writeBytes(Utils.deserializeJson(_message) + "\n"); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void close() {
        try { socket.close(); } 
        catch (IOException e) { e.printStackTrace(); }
    }

    public String readKeyboard() { 
        try { return keyboard.readLine(); } 
        catch (IOException e) { e.printStackTrace(); } 
        return null;
    }
    
}

