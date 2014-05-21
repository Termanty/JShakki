/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jshakki.logiikka;

/**
 *
 * @author termanty
 */
public enum Vari {
    VALKOINEN("valkoinen"), MUSTA("musta");
    
    private final String NIMI;
    
    Vari(String nimi) {
        this.NIMI = nimi;
    }

    @Override
    public String toString() {
        return NIMI;
    }
    
    
}
