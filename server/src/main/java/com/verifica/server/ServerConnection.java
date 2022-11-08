package com.verifica.server;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerConnection {
    private  final Socket socket;

    private BufferedReader inputStream;
    private DataOutputStream outputStream;

    private ArrayList<Ticket> tickets = new ArrayList<>();

    public ServerConnection(Socket _socket, ArrayList<Ticket> _tickets) {
        socket = _socket;
        tickets = _tickets;
        
        try {
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void connect() {
            Message message = read();

            if (message.getTickets().size() == 0) { write( new Message(tickets) ); }

            message = read();
            
            ArrayList<Ticket> foundTickets = new ArrayList<>();

            for (int i = 0; i < message.getTickets().size(); i++) {
                    
                for (int j = 0; j < tickets.size(); j++) {
                    if (message.getTickets().get(i).equals(tickets.get(j))) {
                        foundTickets.add(tickets.get(j));
                        tickets.remove(j);
                        j--;
                    }    
                }
                
            }

            write(new Message(foundTickets));
        
        
    }

    public Message read() {
        try { 
            String i = inputStream.readLine();
            System.out.println("reading line" + i);
            return Utils.serializeJson(i); 
        }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public void write(Message _message) {
        try { outputStream.writeBytes(Utils.deserializeJson(_message) + "\n"); }
        catch (IOException e) { e.printStackTrace(); }
    }

}
