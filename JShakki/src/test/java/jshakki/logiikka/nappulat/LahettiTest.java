/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jshakki.logiikka.nappulat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class LahettiTest {
    
@Test
    public void onOikeaNimi() {
        Ruutu nappula = new Lahetti(Vari.MUSTA, 0, 0);
        assertEquals("Väärä tunnus", 'l', nappula.nimi());
    }
}
