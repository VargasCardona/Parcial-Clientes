package com.excepciones;

public class ClienteEnUsoException extends RuntimeException{

    public ClienteEnUsoException() {
        super("El cliente está todavía en un pedido");
    }
    
}
