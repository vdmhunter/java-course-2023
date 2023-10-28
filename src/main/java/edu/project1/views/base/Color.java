package edu.project1.views.base;

// https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797#256-colors

public enum Color {
    RESET("\u001B[0m"),         //0
    BLACK("\u001B[30m"),        //1
    RED("\u001B[38;5;196m"),    //2
    GREEN("\u001B[32m"),        //3
    YELLOW("\u001B[33m"),       //4
    BLUE("\u001B[34m"),         //5
    PURPLE("\u001B[35m"),       //6
    CYAN("\u001b[38;5;51m"),    //7
    WHITE("\u001B[38;5;195m"),  //8
    GRASS("\u001b[38;5;46m"),   //9
    HANGMAN("\u001b[38;5;94m"), //A
    HAT("\u001b[38;5;226m"),    //B
    BODY("\u001b[38;5;187m"),   //C
    EYE("\u001b[38;5;33m"),     //D
    LIPS("\u001b[38;5;124m"),   //E
    SHIRT("\u001b[38;5;93m");   //F

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
