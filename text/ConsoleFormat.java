package EncryptionZero.text;

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

}
