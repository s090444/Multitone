package clemensNeu;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;


import java.io.*;
/**
 *
 * @author Excalipoor
 */
public class Eingabe {

 // Eingabe.readString() liest eine Zeile von der Tastatur ein, und liefert einen
 // String zurück, der dann mit den üblichen Klassenfunktionen in int, float etc.
 // umgewandelt werden kann.
 public static String readString() {
   try {
     BufferedReader in = new BufferedReader(
              new InputStreamReader(System.in) );
     String s = in.readLine();
     return s;     // Gelesenen String zurückgeben
   } catch( IOException ex ) {
     System.out.println( ex.getMessage() );
   }
   return "- Nichts gelesen -";
 }

  public static String readString(String prompt) {
  System.out.print(prompt);
  return readString();
 }

}
