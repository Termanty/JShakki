
package jshakki.kayttoliittyma.graafinen;

import java.awt.Color;

/**
 *
 * @author termanty
 */
public class Teema {
    private int teemaNum;
    final private int MAARA = 2;
    
    public boolean vaihdettu = true;
    
    public String nimi;
    public String tausta;
    public String shakkilauta;
    public String nappulat;
    public Color vaaleaPohja;
    public Color korostettuVaaleaPohja;
    public Color tummaPohja;
    public Color vaaleaTeksti;
    public Color tummaTeksti;
    public Color ruudunKorostus;
    
    public Teema() {
        teemaNum = 0;
        alustaTeema();
    }
    
    public void vaihdaTeema() {
        teemaNum = (teemaNum + 1) % MAARA;
        alustaTeema();
        vaihdettu = true;
    }

    private void alustaTeema() {
        switch (teemaNum) {
            case 0:
                teemaBlackWhite();
                break;
            case 1:
                teemaOceanBlue();
                break;   
        }
    }

    private void teemaBlackWhite() {
        nimi = "Black&White";
        tausta = "images/backrounds/Black&White.jpg";
        shakkilauta = "images/boards/Board black.jpg";
        nappulat = "images/pieces/";
        vaaleaPohja = MyColor.VALKOINEN_KUULTAVA;
        korostettuVaaleaPohja = MyColor.VALKOINEN_VAHAKUULTAVA;
        tummaPohja = MyColor.MUSTA_KUULTAVA;
        vaaleaTeksti = MyColor.VALKOINEN_VAHAKUULTAVA;
        tummaTeksti = MyColor.MUSTA_VAHAKUULTAVA;
        ruudunKorostus = MyColor.VIHREA_LAPIKUULTAVA;
    }

    private void teemaOceanBlue() {
        nimi = "Ocean Blue";
        tausta = "images/backrounds/Blue Ocean.jpg";
        shakkilauta = "images/boards/Board blue.jpg";
        nappulat = "images/pieces/Blue ";
        vaaleaPohja = MyColor.VAALEA_SININEN_KUULTAVA;
        korostettuVaaleaPohja = MyColor.VAALEA_SININEN_VAHAKUULTAVA;
        tummaPohja = MyColor.SININEN_KUULTAVA;
        vaaleaTeksti = MyColor.VAALEA_SININEN_VAHAKUULTAVA;
        tummaTeksti = MyColor.SININEN_VAHAKUULTAVA;
        ruudunKorostus = MyColor.VIHREA_LAPIKUULTAVA;
    }
    
    
}
