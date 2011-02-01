package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;

/**
 *
 * @author Excalipoor
 */

import java.util.Date;

public class Zufallszahl {
    long lDateTime;



    public Zufallszahl() {
        lDateTime = new Date().getTime();

}
    public Zufallszahl(long max) {
        lDateTime = (new Date().getTime() % max);



}
}
