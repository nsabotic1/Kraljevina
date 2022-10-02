package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IspitGradTest {

    @Test
    void kraljevskiTest() {
        Drzava d = new Drzava(1, "Bosna i Hercegovina", null);
        d.setKraljevska(true);
        Grad g = new Grad(100, "Sarajevo", 350000, d);
        assertFalse(g.isKraljevski());
        g.setKraljevski(true);
        assertTrue(g.isKraljevski());
    }

    @Test
    void kraljevskiIzuzetakTest() {
        // Koristimo konstruktor sa pet parametar
        Drzava d = new Drzava(1, "Austrija", null, false);
        Grad g = new Grad(100, "Beč", 1899055, d);
        assertFalse(g.isKraljevski());
        assertThrows(NotAKingdomException.class, () -> g.setKraljevski(true), "Država Austrija nije kraljevska država");
        assertFalse(g.isKraljevski());
    }

    @Test
    void kraljevskiNullTest() {
        Grad g = new Grad(100, "Beč", 1899055, null);
        assertFalse(g.isKraljevski());
        // Šta će se desiti kada je država null? Opet treba baciti izuzetak jer null nije kraljevska država
        assertThrows(NotAKingdomException.class, () -> g.setKraljevski(true));
        assertFalse(g.isKraljevski());
    }

    @Test
    void kraljevskiCtorTest() {
        // I konstruktor klase Grad treba bacati izuzetak
        Drzava d = new Drzava(1, "Mađarska", null, false);
        assertThrows(NotAKingdomException.class,
                () -> new Grad(100, "Budimpešta", 1752000, d, true),
                "Država Mađarska nije kraljevska država"
        );
    }
}