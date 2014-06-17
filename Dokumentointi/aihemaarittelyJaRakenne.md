# JShakki 
simppeli shakkiohjelma

Tämä shakkiohjelma on kauniilla graafisella käyttöliittymällä toteutettu shakkipelin versio. Ohjelma mahdollistaa kahden pelaajan pelaamisen toisiaan vastaan. Toteutus myös sisältää mahdollisuuden pelata satunnaisesti siirtoja arpovaa tietokonetta vastaa. 

Shakkipeli sääntöjen toteutus on vajaa kahdella tavalla. Tornitusta ei ole mukana toteutuksessa ja peli ei väli shakkaus tilanteesta mitään, eikä tunnista. Peli päätty kun tehdään siirto, joka syö vastustajan kuninkaan.

## Pelin toiminnot
- pelitavan valita: kaksin peli tai yksinpeli tietokonetta vastaan.
- pelin aloitus ja lopetus.
- vuorotellen tapahtuva shakkinappuloiden siirtely.
- pelitilanteen talletus levylle. EI TOTEUTETTU.
- vanhan pelin lataus levyltä.EI TOTEUTETTU.
- Käyttöliittymän graaffisen teeman vaihto klikkaamalla teeman vaihto elementiä.

## Rakennekuvaus

Ohjelman on jaettu kahteen pääosaan: Graafiseen käyttöliitymään ja toiminnallisuuden toteutukseen.

### Graafinen käyttöliittymä

Käyttöliittymä on toteutettu piirtoalustalla ja hiirentoimintojen kuuntelijalla. Piirtoalusta piirtää näytettävät elementit. Elementtejä on kolmenlaisia; Aina näytettävät, aloitustilassa näytettävät, pelitilanteessa näytettävät. Käyttöliittymään on toteutettu myös erilaiset teemat, jota voi vaihtaa klikkaamalla teeman vaihto elementtiä. Hiirenkuuntelija korostaa aktiivisia elementtejä ollessaan niiden päällä ja suorittaa siihen liityvän toiminnon klikkauksella. Siirrossa hiirenkuuntelija seuraa hiiren napin pohjaan painallusta pelinappulan kohdalla ja korostaa ruudut johon nappula voi siirtyä. Vapauttamalla painallus ruudun kohdalla johon halutaan siirtyä, käynnistää siirron suorituksen.

### Toiminnallisuus

Toiminnallinen puoli ohjelmasta on jaettu JShakkirunko, Logiikka, Pelihistoria ja Tekoaly luokkiin. 
JShakkirunko huolehtii korkeimmalla tasolla ohjelman tominnasta tuntee toteutuksen muut osapuolet. Se hallitsee vuorottelua aloitustilan ja pelitilan välillä sekä mahdollistaa tekoalyn pelaamisen joko valkoisilla tai mustilla nappuloilla.

Logiikka luokka hallitsee pelin säännöt. Se tarkistaa säännön laillisuuden ja tekee siirron. Luokka tuntee Pelihistoria otuksen, johon se tekee siirron talletuksen. Logiikka myös suorittaa peli vuoron vaihdon ja siihen tarvittavien muuttujien arvoista huolehtimisen. Pelin alussa Logiikka luo pelilaudan ja asettaa nappulat aloitus paikoilleen. Tämä luokka myös palvelee käyttöliitymää siten että se osaa kertoa mitä siirtoja jokin napulla kyseisessä pelitilanteessa voi tehdä ja ilmoittamalla paivitys muutujan kautta tarpeen alustaa graafisen käyttöliitymän nappulat uudelleen. Tämä tapahtuu esim. silloin kun sotilas korotetaan kuningatareksi ja käytöliitymän täytyy vaihtaa nappulan grafiikkaa.

Pelihistoria luokka pitää yksinkertaista kirjanpitoa tehdyistä siirroista laittamalla ne tekstimuodossa ArrayListiin. Tekoaly luokka arpoo ohjaamilleen nappuloille seuraavan siirron.

Toteutuksessa käytetään Ruutu rajapintaa, jonka toteuttavat kaikki nappulat ja Tyhja luokka. Pelilauta on toteutettu 8*8 Ruutu taulukkona, jossa on jokaisessa taulukon paikkassa joko nappula tai Tyhja otus. Pelin eri nappulat on toteutettu niin etä ne perivät Nappula luokan ja niille annetaan väri luokasta Vari. Lisäksi nappula saa siirrot Suunta rajapinnan toteuttavien enum luokkien kautta. Esim. Kuningatar saa konstruktorissa siirtonsa Vino ja Vaaka enum luokan muuttujilta.




