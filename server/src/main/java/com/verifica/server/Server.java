package com.verifica.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server;

    ArrayList<Ticket> tickets = new ArrayList<>();
    ArrayList<Socket> sockets = new ArrayList<>();

    public Server(ArrayList<Ticket> _tickets) {
        tickets = _tickets;
    }

    public void listen(int _port) {
        try {
            server = new ServerSocket(_port);

            while (true) {
                System.out.println("listening for clients");
                Socket socket = server.accept();
                System.out.println("client is connected");
                sockets.add(socket);

                Thread thread = new Thread(new Runnable(){
                    @Override public void run() {       
                        ServerConnection connection = new ServerConnection(sockets.get(sockets.size()-1), tickets);
                        connection.connect();
                    }
                });
                thread.start();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
