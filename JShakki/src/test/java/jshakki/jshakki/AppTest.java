
package jshakki.jshakki;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class AppTest {
    App testattava;
    
    public AppTest() {
        testattava = new App();
    }
    
    @Test
    public void ohjelmanTestaus() {
        assertNotNull(testattava);
    }
}
