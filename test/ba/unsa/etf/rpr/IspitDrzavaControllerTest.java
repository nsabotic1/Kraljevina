package ba.unsa.etf.rpr;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class IspitDrzavaControllerTest {
    Stage theStage;
    DrzavaController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        dao.vratiBazuNaDefault();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
        ctrl = new DrzavaController(null, dao.gradovi());
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Država");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }


    @Test
    public void testPoljaPostoje(FxRobot robot) {
        CheckBox cbKraljevska = robot.lookup("#cbKraljevska").queryAs(CheckBox.class);
        assertFalse(cbKraljevska.isSelected());
    }

    @Test
    public void testKraljevska(FxRobot robot) {
        // Upisujemo državu, postavljamo za kraljevsku državu
        robot.clickOn("#fieldNaziv");
        robot.write("Bosna i Hercegovina");
        robot.clickOn("#choiceGrad");
        robot.clickOn("Pariz");
        robot.clickOn("#cbKraljevska");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Drzava bih = ctrl.getDrzava();
        assertEquals("Bosna i Hercegovina", bih.getNaziv());
        assertEquals("Pariz", bih.getGlavniGrad().getNaziv());
        assertTrue(bih.isKraljevska());
    }

    @Test
    public void testNijeKraljevska(FxRobot robot) {
        // Upisujemo državu koja nije kraljevska
        // (Austrija ustvari jeste kraljevska ali su i ostale, tako da nemamo izbora)
        robot.clickOn("#fieldNaziv");
        robot.write("Austrija");
        robot.clickOn("#choiceGrad");
        robot.clickOn("Beč");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Drzava austrija = ctrl.getDrzava();
        assertEquals("Austrija", austrija.getNaziv());
        assertEquals("Beč", austrija.getGlavniGrad().getNaziv());
        assertFalse(austrija.isKraljevska());
    }
}