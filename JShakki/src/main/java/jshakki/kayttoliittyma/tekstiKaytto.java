/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jshakki.kayttoliittyma;

import jshakki.logiikka.Nappula;
import jshakki.logiikka.Tilanne;

/**
 *
 * @author termanty
 */
public class tekstiKaytto implements Kayttoliittyma {
    Nappula[][] peli;
    
    public tekstiKaytto(Nappula[][] uusi) {
        this.peli = uusi;
    }
    
    public void tulostaPelitilanne() {
        for (int i = 7; i >= 0; i++) {
            System.out.print((i+1) + " ║");
            for (int j = 0; j < 8; j++) {
                if (peli[i][j] == null) {
                    System.out.print("…");
                } else {
                    System.out.print("");
                }
            }
        }
        System.out.print("—╚═══════════════");
        System.out.println("——a   b   c   d   e   f   g   h");
        System.out.println((int) '♟');
        //    
//    8 ║♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜
//7 ║♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟
//6 ║… … … … … … … …
//5 ║… … … … … … … …
//4 ║… … … … … … … …
//3 ║… … ♘ … … … … …
//2 ║♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙
//1 ║♖ … ♗ ♕ ♔ ♗ ♘ ♖
//—╚═══════════════
//——a   b   c   d   e   f   g   h
    
    }
}
