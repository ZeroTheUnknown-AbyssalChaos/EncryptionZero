package EncryptionZero.text;

import java.util.Scanner;

public interface ConsoleFormat {


    default String line(){
        return line(500);
    }
    default String line(int size){
        return line(size, '-');
    }
    default String line(char c){
        return line(500, c);
    }
    default String line(int size, char c){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(c).repeat(Math.max(0, size)));
        return sb.toString();
    }
    default void printLine(){
        System.out.println(line());
    }
    default void printLine(int size){
        System.out.println(line(size));
    }
    default void printLine(char c){
        System.out.println(line(c));
    }
    default void printLine(int size, char c){
        System.out.println(line(size, c));
    }

    static String consoleIn(Scanner sc){
        return sc.nextLine();
    }
    static String consoleInPrint(Scanner sc, String message){
        System.out.println(message);
        return consoleIn(sc);
    }
    default String consoleInPrint(Scanner sc){
        return consoleInPrint(sc, "\r\n\r\n\tWhat now?\r\n");
    }


}
