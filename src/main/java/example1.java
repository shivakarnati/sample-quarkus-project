import java.io.*;
import  java.util.*;

public class example1 {
    static void traversestring(String str){
        for (int i=0; i< str.length(); i++){
            System.out.println(str.charAt(i)+" ");
        }
    }

    public static void main(String[] args){
        String str="Hey! This is example....!";

        traversestring(str);
    }
}
