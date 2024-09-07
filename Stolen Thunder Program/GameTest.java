import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * GameTest - a class to test the zuul game engine.
 *
 * @author  William H. Hooper
 * @version 2018-11-19
 */
public class GameTest
{
    private Game game1;
    private Console console1; 

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        System.out.println("\f");
        game1 = new Game();
        console1 = new Console(game1);
        String message = game1.readMessages();
        System.out.print(message + "> ");
    }

    @Test
    public void start()
    {
        assertEquals(false, game1.finished());
    }

    @Test
    public void talk2Zeus()
    {
        assertEquals(true, console1.testCommand("talk Zeus", 
                "Zeus: I can't believe the swine who would dare steal my thunderbolt!"));
    }

    @Test
    public void map()
    {
        assertEquals(true, console1.testCommand("go down", "Hades"));
        assertEquals(true, console1.testCommand("go down", "Persephone"));
        assertEquals(true, console1.testCommand("go up-right", "Demeter"));
        assertEquals(true, console1.testCommand("go right", "Dionysus"));
        assertEquals(true, console1.testCommand("go left", "Demeter"));
        assertEquals(true, console1.testCommand("go up", "Hera"));
        assertEquals(true, console1.testCommand("go up", "Hermes"));
        assertEquals(true, console1.testCommand("go right", "Ares"));
        assertEquals(true, console1.testCommand("go up", "Hephaestus"));
        assertEquals(true, console1.testCommand("go left", "Aphrodite"));
        assertEquals(true, console1.testCommand("go left", "Apollo"));
        assertEquals(true, console1.testCommand("go down", "courtyard"));
        assertEquals(true, console1.testCommand("go left", "Athena"));
        assertEquals(true, console1.testCommand("go up", "Artemis"));
        assertEquals(true, console1.testCommand("go down", "Athena"));
        assertEquals(true, console1.testCommand("go down", "Poseidon"));
        assertEquals(true, console1.testCommand("go down", "Hestia"));
        assertEquals(true, console1.testCommand("go up", "Poseidon"));
        assertEquals(true, console1.testCommand("go right", "throne"));
    }

    @Test
    public void noEntry()
    {
        assertEquals(true, console1.testCommand("go down", "Hades"));
        assertEquals(true, console1.testCommand("go left", "There's no where to go!"));
    }

    @Test
    public void quit()
    {
        console1.testCommand("quit");
        assertEquals(true, game1.finished());
        assertEquals(false, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("anything", "game is over"));
    }

    @Test
    public void testmap()
    {
        assertEquals(true, console1.testCommand("go right", "Hera"));
    }

    @Test
    public void back()
    {
        assertEquals(true, console1.testCommand("go down", "Hades"));
        assertEquals(true, console1.testCommand("go down", "Persephone"));
        assertEquals(true, console1.testCommand("go up-right", "Demeter"));
        assertEquals(true, console1.testCommand("back", "Persephone"));
        assertEquals(true, console1.testCommand("back", "Hades"));
        assertEquals(true, console1.testCommand("back", "throne"));
    }

    @Test
    public void help()
    {
        assertEquals(true, console1.testCommand("help", 
                "You are a demigod, an outcasted child of Zeus, born out of wedlock\n"
                + "from one of the many affairs he had with a mortal. You are looked\n"
                + "down upon by the other Gods and Goddesses, as well as their demigod\n"
                + "children. Zeus' thunderbolt has gone missing and you have been tasked\n"
                + "to find it in order to prove you're worthy of the status of a demigod.\n"
                + "\n"
                + "Your command words are:\n"
                + "Commands:  go quit talk help back take drop look items eat hint"));
    }

    @Test
    public void takeArrow()
    {
        assertEquals(true, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("go up", "Apollo"));
        assertEquals(true, console1.testCommand("take Arrow", "You have acquired a(n) Arrow! \n"
        + "Arrow: an arrow from a huntress.."));
    }
    
    @Test
    public void dropTrident()
    {
        assertEquals(true, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("go up", "Apollo"));
        assertEquals(true, console1.testCommand("take Trident", "You have acquired a(n) Trident! \n"
        + "Trident: a mighty weapon belonging to the ruler of the seas.."));
        assertEquals(true, console1.testCommand("drop Trident", "You have dropped a(n) Trident."));
    }
    
    @Test
    public void look()
    {
        assertEquals(true, console1.testCommand("go right", "Hera"));
        assertEquals(true, console1.testCommand("go down", "Demeter"));
        assertEquals(true, console1.testCommand("look", "You are in the farm, where Demeter is tending to her crops....\n"
        + "Exits:  up right down\n" + "Objects: Pomegranate"));
    }
    
    @Test
    public void items()
    {
        assertEquals(true, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("go up", "Apollo"));
        assertEquals(true, console1.testCommand("take Trident", "You have acquired a(n) Trident! \n"
        + "Trident: a mighty weapon belonging to the ruler of the seas.."));
        assertEquals(true, console1.testCommand("items", "Object(s) carried: Trident\n" 
        + "Total weight: 10.0 pounds"));
    }
    
    @Test
    public void eatWrongThing()
    {
        assertEquals(true, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("go up", "Apollo"));
        assertEquals(true, console1.testCommand("take Arrow", "You have acquired a(n) Arrow! \n"
        + "Arrow: an arrow from a huntress.."));
        assertEquals(true, console1.testCommand("eat Arrow", "You can't eat that!"));
    }
    
    @Test
    public void eatRightThing()
    {
        assertEquals(true, console1.testCommand("go up", "courtyard"));
        assertEquals(true, console1.testCommand("go up", "Apollo"));
        assertEquals(true, console1.testCommand("go right", "Aphrodite"));
        assertEquals(true, console1.testCommand("take Ambrosia", "You have acquired a(n) Ambrosia! \n"
        + "Ambrosia: the special fruit of the gods.."));
        assertEquals(true, console1.testCommand("eat Ambrosia", "You have eaten the special fruit of the gods..: Ambrosia! \n"
        + "Your weight limit has now increased to: 20.0 pounds"));
    }
}

