/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//import dlb.DLB;

/**
 *
 * @author Chris
 */
public class dlbMain {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        String read;
        long averageTime = 0;
        ArrayList list = new ArrayList();
        StringBuilder key = new StringBuilder();
        FileReader fr = new FileReader("dictionary.txt");
        BufferedReader br = new BufferedReader(fr);
        PrintWriter writer = new PrintWriter("user_history.txt", "UTF-8");
        DLB dictionary = new DLB();
        
        while((read = br.readLine()) != null){
            dictionary.add(read);
        }
         for(int i = 0; ;i++) {
             int count = 1;
             count++;
            long startTime = System.nanoTime();
            System.out.printf("Enter character: ");
            String word = in.nextLine();
            if(word.equals("!")){
                System.out.println("Average Time: " + averageTime);
                System.out.println("Bye!");
                break;
            }
            key.insert(i, word);
            switch(word){
                case "1":{
                 System.out.println("WORD COMPLETED " + list.get(1));
                 writer.print(list.get(1) + "\n");
                 list.clear();
                 key.delete(0, key.length());
                 i = -1;
                 continue;
                }
                case "2":{
                System.out.println("WORD COMPLETED " + list.get(2));
                writer.print(list.get(2) + "\n");
                list.clear();
                key.delete(0, key.length());
                i = -1;
                 continue;
                }
                case "3":{
                System.out.println("WORD COMPLETED " + list.get(3));
                writer.print(list.get(3) + "\n");
                list.clear();
                key.delete(0, key.length());
                 i = -1;
                continue;
                }
                case "4":{
                System.out.println("WORD COMPLETED " + list.get(4));
                writer.print(list.get(4) + "\n");
                list.clear();
                key.delete(0, key.length());
                 i = -1;
                continue;
                }
                case "5":{
                System.out.println("WORD COMPLETED " + list.get(5));
                writer.print(list.get(5) + "\n");
                list.clear();
                key.delete(0, key.length());
                i = -1;
                continue;
                }
            }
            list = dictionary.search(key);
           if(list.isEmpty()){
                System.out.println("Word not found. Try again.");
                list.clear();
                key.delete(0, key.length());
                i = -1;
                continue;
            }
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            averageTime = (averageTime + elapsedTime) / count;
            TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
            System.out.println("(" + elapsedTime + "ns" +")");
            for(int j = 1; j <= 5; j++){
                System.out.printf("(%d) %s ", j, list.get(j));
            }                
         }
        br.close();
        writer.close();
    }
}