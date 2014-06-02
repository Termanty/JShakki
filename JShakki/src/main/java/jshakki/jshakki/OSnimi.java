

package jshakki.jshakki;

/**
 *
 * @author termanty
 */
public final class OSnimi {
   private static final String OS = System.getProperty("os.name");
   
   public static boolean onLinux()
   {
      return OS.startsWith("Linux");
   }
   
   
}
