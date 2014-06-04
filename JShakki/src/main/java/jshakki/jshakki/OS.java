
package jshakki.jshakki;

/**
 * OS luokka tekee käyttöjärjestelmä sidonnaisia muutoksia GUI:hin.
 */
public final class OS {
   private static final String NAME = System.getProperty("os.name");
   public static final int X = onLinux() ? 0 : -10;
   public static final int Y = onLinux() ? 0 : -30;
   
   /**
    * onLinux metodi tarkistaa ollaanko linux käyttöjärjestelmässä.
    * @return true jos käytössä on linux, muutoin false.
    */
   private static boolean onLinux() {
      return NAME.startsWith("Linux");
   }
}
