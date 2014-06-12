

package jshakki.jshakki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tämä luokka lataa ja tallentaa pelejä.
 * 
 * EI VIELÄ KÄYTÖSSÄ !!!
 * 
 */
public class HistoriatiedostonKasittelija {
    private final String tiedostonNimi = "historia.txt";
    private final File tiedosto;  
    private List<String> listaPeleista;

    public HistoriatiedostonKasittelija() {
        listaPeleista = new ArrayList<>();            
        tiedosto = new File(tiedostonNimi);
        lueTiedosto();
    }
    
    public List<String> lataa(String pelinNimi) {
        for (String peli : listaPeleista) {
            if (peli.startsWith(pelinNimi)) {      
                return otaSiirot(peli);
            }
        }
        return null;
    }
    
    public void tallenna(String nimi, List<String> siirtoLista) {
        String uusi = nimi;
        for (String siirto : siirtoLista) {
            uusi = uusi + " " + siirto;
        }
        listaPeleista.add(uusi);
    }

    private List<String> otaSiirot(String peliTallenne) {
        String[] siirrot = peliTallenne.split(" ");
        List<String> siirtoLista = new ArrayList<>();
        for (int i = 1; i < siirrot.length; i++) {
            siirtoLista.add(siirrot[i]);
            
        }
        return siirtoLista;
    }

    private void lueTiedosto() {
        try {
            Scanner lukija = new Scanner(tiedosto);
            listaPeleista = new ArrayList<>();
            while (lukija.hasNextLine()) {
                listaPeleista.add(lukija.nextLine());
            }
            lukija.close();
        } catch (FileNotFoundException e){
            System.err.println(tiedostonNimi + " tiedostoa ei löydetty");
        }
    } 
}
