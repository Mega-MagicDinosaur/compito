package com.verifica.server;

import java.io.IOException;

import com.fasterxml.jackson.databind.json.JsonMapper;

public class Utils {
    private static final JsonMapper jsonMapper = new JsonMapper();
    
    public static String deserializeJson(Message _message) {
        try { return jsonMapper.writeValueAsString(_message); }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public static Message serializeJson(String _string) {
        try { return jsonMapper.readValue(_string, Message.class); }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

}
