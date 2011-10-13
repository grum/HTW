package HuntTheWumpus;

import junit.framework.TestCase;
import static HuntTheWumpus.Game.*;
import HuntTheWumpus.fixtures.MockConsole;

public class GameDutchPresenterTest extends TestCase {
    private GameDutchPresenter p;
    private MockConsole mc;
    private Game g;

    protected void setUp() throws Exception {
        mc = new MockConsole();
        p = new GameDutchPresenter(mc);
        p.setLanguageFile("dutch.properties");
        g = p.getGame();
    }

    public void testDirectionErrorMessages() throws Exception {
        g.putPlayerInCavern(1);
        p.execute(EAST);
        assertTrue(mc.check("Jij kan van hier niet naar het oosten gaan."));
        p.execute(WEST);
        assertTrue(mc.check("Jij kan van hier niet naar het westen gaan."));
        p.execute(SOUTH);
        assertTrue(mc.check("Jij kan van hier niet naar het zuiden gaan."));
        p.execute(NORTH);
        assertTrue(mc.check("Jij kan van hier niet naar het noorden gaan."));
    }

    public void testAvailableDirectionsWithNoPlaceToGo() {
        g.putPlayerInCavern(1);
        assertEquals("Er zijn geen uitgangen.", p.getAvailableDirections());
    }

    public void testSouthIsAvailable() throws Exception {
        g.addPath(1, 2, SOUTH);
        g.putPlayerInCavern(1);
        assertEquals("Jij kan van hier naar het zuiden gaan.", p.getAvailableDirections());
    }

    public void testNortAndSouthAvailable() throws Exception {
        g.addPath(1, 2, SOUTH);
        g.addPath(1, 3, NORTH);
        g.putPlayerInCavern(1);
        assertEquals("Jij kan van hier naar het noorden en zuiden gaan.", p.getAvailableDirections());
    }

    public void testFourDirections() throws Exception {
        g.addPath(1, 2, EAST);
        g.addPath(1, 3, WEST);
        g.addPath(1, 4, SOUTH);
        g.addPath(1, 5, NORTH);
        g.putPlayerInCavern(1);
        assertEquals("Jij kan van hier naar het noorden, zuiden, oosten en westen gaan.", p.getAvailableDirections());
    }
}
