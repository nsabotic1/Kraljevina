package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class IspitGeografijaDAOTest {
    GeografijaDAO dao = GeografijaDAO.getInstance();

    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }

    @Test
    void testDodajKraljevskuDrzavu() {
        Grad london = dao.nadjiGrad("London");
        assertFalse(london.isKraljevski());

        Drzava d = new Drzava(1, "Bosna i Hercegovina", london);
        d.setKraljevska(true);
        dao.dodajDrzavu(d);

        // Uzimam drugu verziju za BiH
        Drzava bih = dao.nadjiDrzavu("Bosna i Hercegovina");
        assertTrue(bih.isKraljevska());
    }

    @Test
    void testDodajKraljevskiGrad() {
        Drzava francuska = dao.nadjiDrzavu("Francuska");
        assertFalse(francuska.isKraljevska());
        francuska.setKraljevska(true);
        dao.izmijeniDrzavu(francuska);

        Grad chamonix = new Grad(0, "Chamonix", 8611, francuska);
        chamonix.setKraljevski(true);
        dao.dodajGrad(chamonix);

        // Uzimam drugu verziju države i grada
        Drzava francuska2 = dao.nadjiDrzavu("Francuska");
        assertTrue(francuska2.isKraljevska());
        Grad chamonix2 = dao.nadjiGrad("Chamonix");
        assertTrue(chamonix2.isKraljevski());
    }

    @Test
    void testIzmijeniKraljevskiGrad() {
        Drzava vbr = dao.nadjiDrzavu("Velika Britanija");
        assertFalse(vbr.isKraljevska());
        vbr.setKraljevska(true);
        dao.izmijeniDrzavu(vbr);

        Grad london = dao.nadjiGrad("London");
        assertFalse(london.isKraljevski());
        london.setKraljevski(true);
        dao.izmijeniGrad(london);

        // Uzimam drugu verziju države i grada
        Drzava vbr2 = dao.nadjiDrzavu("Velika Britanija");
        assertTrue(vbr2.isKraljevska());
        Grad london2 = dao.nadjiGrad("London");
        assertTrue(london2.isKraljevski());
    }

}