

package skocko;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.swing.*;


public class Skocko {
    public int korak = 1; 
    public int brojPogodaka = 0;
    public int nisuNaMestu = 0; 
    public int ukupno = 0;  
     
     //public Kombinacija kombo = new Kombinacija();  
    int [] glavniNiz ;
    
    public Skocko(){
           glavniNiz = generisiRandomKombinaciju();
    }  
    
    public int[] generisiRandomKombinaciju(){
        Random rand = new Random();
        int [] temp = new int [4];
        for (int i = 0 ; i < 4; i++){
            temp[i] = rand.nextInt(6) + 1;
        }
        return temp;
    }
    
    public void resetSkockoParametara(){
        brojPogodaka = 0;
        ukupno = 0;
        nisuNaMestu = 0;
    } 
    
    public boolean daLiJeKombinacija(int [] niz){

        /* 
        Metod uzima unesenu korisnikovu kombinaciju kao niz od 4 clana. 
        Uzmimo za slucaj da je trenutna unešena kombinacija 2 2 1 3, a nasumicno 
        generisana 2 2 4 4.
        Broj direktnih pogodaka lako je zakljuciti, uporedjujemo pojedinacne 
        clanove niza sa odgovarajucim clanovima niza random kombinacije. 
        Pa tako, 2 odgovara 2, sto je +1 pogodak. 1 ne odgovara 4, pa to nije 
        pogodak itd. Time dobijamo broj direktnih pogodaka. 
        Broj pogodaka koji nisu na mestu nalazimo kao
        Ukupni broj podataka - broj direktnih podataka.
        
        U proslom primeru, ne bi bila dobra praksa proveravati koliko se puta 
        broj 2 ponavlja u nizu, jer bi u prvom obracunavanju zakljucak bio da se 
        broj dva u nizu nalazi dva puta, a u sledecem obracunu jos dva puta,
        pa bi to bilo 4, a ustvari broj 2 se ponavlja dva puta:
        
        2 -> (2)(2)(4)(4) --- 2 puta
        2 -> (2)(2)(4)(4) --- 2 puta
        1 -> (2)(2)(4)(4) --- 0 puta
        3 -> (2)(2)(4)(4) --- 0 puta
        
        Zato pravimo soritiranu listu, koja na pocetku izgleda ovako :
        
        1 2 2 3 
        
        Da ne bi dva puta proveravali koliko puta se broj 2 nalazi u nizu, "provlacimo"
        listu kroz hashset koji izbacuje duplikate, rezultat vracamo u listu i 
        na kraju lista izgleda ovako : 1 2 3. 
        
        Sada proveravamo koliko se puta svaki od tih brojeva nalazi u nizu, dobijamo
        ukupan broj, oduzmemo broj direktnih pogodaka i dobijamo broj onih koji
        nisu na mestu.
         
        */ 
        List<Integer> lista = new ArrayList<>(); 
        Set <Integer> hs = new HashSet<>();
        
        int jedan = 0 , dva = 0, tri = 0, cetiri = 0, pet = 0, sest = 0;
        
        for (int i = 0; i < 4; i++){
            lista.add(niz[i]);
            if (lista.get(i) == glavniNiz[i]){
                brojPogodaka++;
            }
        } 
        
        for (int i = 0; i < lista.size() ; i++){
            if (lista.get(i) == 1){
                jedan++;
            }else if (lista.get(i) == 2){
                dva++;
            }
            else if (lista.get(i) == 3){
                tri++;
            }
            else if (lista.get(i) == 4){
                cetiri++;
            }
            else if (lista.get(i) == 5){
                pet++;
            }
            else if (lista.get(i) == 6){
                sest++;
            }
        } 
        hs.addAll(lista);
        lista.clear();
        lista.addAll(hs);  
        
        for (int k = 0; k < lista.size(); k++) {
            int temp2 = 0;
            for (int r = 0; r < 4; r++){ 
                if (lista.get(k) == glavniNiz[r]){
                     temp2++;
                } 
            }
            switch (lista.get(k)){
                case 1: 
                    if (temp2 <= jedan){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + jedan;
                    }
                    break;
                case 2: 
                    if (temp2 <= dva){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + dva;
                    }
                    break;
                case 3: 
                    if (temp2 <= tri){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + tri;
                    }
                    break;
                case 4: 
                    if (temp2 <= cetiri){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + cetiri;
                    }
                    break;
                case 5: 
                    if (temp2 <= pet){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + pet;
                    }
                    break;
                case 6: 
                    if (temp2 <= sest){
                        ukupno = ukupno + temp2;
                    }else{
                        ukupno = ukupno + sest;
                    }
                    break;
            }
        } 
        nisuNaMestu = ukupno - brojPogodaka;  
        
        if (brojPogodaka == 4){
            return true;
        }else{ 
            return false;
        } 
    }
}

 
