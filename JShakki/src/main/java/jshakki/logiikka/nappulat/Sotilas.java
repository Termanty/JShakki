/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.liikkeet.MustaSotilas;
import jshakki.logiikka.liikkeet.ValkeaSotilas;

/**
 *
 * @author termanty
 */
public class Sotilas implements Ruutu {
    private final char nimi = 's';
    private final Vari vari;
    private final ArrayList<Liike> liikkeet;

    public Sotilas (Vari vari) {
        this.vari = vari;
        liikkeet = new ArrayList<>();
        if (vari == Vari.VALKOINEN) {
            liikkeet.addAll(Arrays.asList(ValkeaSotilas.values()));
        } else {
            liikkeet.addAll(Arrays.asList(MustaSotilas.values()));
        }
    }

    @Override
    public char nimi() {
        return nimi;
    }

    @Override
    public Vari vari() {
        return vari;
    }

    @Override
    public ArrayList<Liike> liikkeet() {
        return liikkeet;
    }
}
