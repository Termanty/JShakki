package jshakki.domain;

/**
 *
 * @author termanty
 */
public class Kuningas implements Nappula{
    private final char nimi = 'k';
    private final Vari vari;
    private final int[][] liikkeet = {{0,0,1,1,1,-1,-1,-1},
                                      {1,-1,1,0,-1,1,0,-1}};

    public Kuningas(Vari vari) {
        this.vari = vari;
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
    public int[][] liikkeet() {
        return liikkeet;
    }

    
      
}
