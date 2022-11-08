package com.verifica.server;

public class Ticket {
    private static int IDc = 0;
    private int ID;
    
    private String code;

    public Ticket() {}

    public Ticket(String _code) {
        code = _code;
        ID = IDc++;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ID;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (ID != other.ID)
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    public int getID() { return ID; }
    public String getCode() { return code; }

    public void setID(int _ID) { ID = _ID; }
    public void setCode(String _code) { code = _code; }
}
