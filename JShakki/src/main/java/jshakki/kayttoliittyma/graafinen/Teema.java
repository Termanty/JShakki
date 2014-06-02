

package jshakki.kayttoliittyma.graafinen;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author termanty
 */
public class Teema {
    
    public static final int X = 740;
    public static final int Y = 480;
    public static final int LEV = 220;
    public static final int KOR = 40;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    public static boolean vaihdettu = true;
    public static boolean korosta = false;
    
    public static String nimi = "Black&White";
    public static String tausta = "images/backrounds/Black&White.jpg";
    public static String shakkilauta = "images/boards/Board black.jpg";
    public static String nappulat = "images/pieces/";
    public static Color vaaleaPohja = MyColor.VALKOINEN_KUULTAVA;
    public static Color korostettuVaaleaPohja = MyColor.VALKOINEN_VAHAKUULTAVA;
    public static Color tummaPohja = MyColor.MUSTA_KUULTAVA;
    public static Color vaaleaTeksti = MyColor.VALKOINEN_VAHAKUULTAVA;
    public static Color tummaTeksti = MyColor.MUSTA_VAHAKUULTAVA;
    public static Color ruudunKorostus = MyColor.VIHREA_LAPIKUULTAVA;
    
    public static boolean hiiriPaalla(Point p, int X_SOVITA, int Y_SOVITA) {
        return p.x >= X && p.x <= X_LOPPU && p.y + Y_SOVITA >= Y && p.y + Y_SOVITA <= Y_LOPPU;
    }

}
