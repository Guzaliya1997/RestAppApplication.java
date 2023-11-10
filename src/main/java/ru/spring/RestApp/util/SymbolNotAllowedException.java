package ru.spring.RestApp.util;

public class SymbolNotAllowedException extends  RuntimeException{
    public SymbolNotAllowedException(){
        super("Error.Only english letters are allowed to input");
    }
}
