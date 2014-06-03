
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Point;

/**
 *
 * @author termanty
 */
public class Oikeudet {
    
    public static final int X = 740;
    public static final int Y = 535;
    public static final int LEV = 220;
    public static final int KOR = 40;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    public static boolean korosta = false;
    
    public static boolean hiiriPaalla(Point p, int X_SOVITA, int Y_SOVITA) {
        return p.x >= X && p.x <= X_LOPPU && p.y + Y_SOVITA >= Y && p.y + Y_SOVITA <= Y_LOPPU;
    } 
}
