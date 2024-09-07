import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomStack;
    private Player player;
    private ArrayList<String> usedHints;
    private Random gen;
    private String[] hints = {
            "Try exploring all the different areas.",
            "Use the look command after every time you take or drop an item!",
            "Be sure to talk to every God/Goddess, they could give helpful clues!",
            "Use the 'look' command to get details about your surroundings.",
            "Pay attention to the descriptions of certain objects.",
            "You can give certain Gods/Goddesses objects that you find and they will \n"
            + "tell you helpful information!",
            "There is a special passage way that will be unlocked when you give a certain \n"
            + "creature a special object!",
            "There is a special object in the map that can increase your weight limit \n"
            + "when eaten. It's the only object that can be eaten!!",
            "Not all objects in the game are useful towards winning.",
            "If the talk feature isn't working with a specific object, try dropping \n"
            + "and taking the object back into your inventory. Make sure to use the \n"
            + "'look' command afterwards."
        };

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        roomStack = new Stack<>();
        usedHints = new ArrayList<>();
        gen = new Random();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room throne, hera, hermes, hades, poseidon, courtyard, aphrodite, apollo, ares,
        artemis, athena, demeter, dionysus, hephaestus, hestia, persephone, Hthrone;
        throne = new Room("in the throne room, where mighty Zeus is sitting on \n"
            + "the throne, seemingly upset at something..." );
        hera = new Room("in Queen Hera's chambers, where she's sitting in front \n"
            + "of her mirror, looking displeased as well.");
        hermes = new Room("in Hermes' room where the messenger is nowhere to be \n"
            + "found...");
        hades = new Room("in Hades' realm where Hades' guard dog, Cerberus is \n"
            + "sleeping soundly at the gate.");
        poseidon = new Room("in Poseidon's palace where Poseidon is tending to \n"
            + "Pegasus.");
        courtyard = new Room("in the courtyard where the other demigods are hanging \n"
            + "out with Heremes?!");
        aphrodite = new Room("in Aphrodite's garden, where she's loudly arguing with \n"
            + "someone..");
        apollo = new Room("at a grassy clearing, where Apollo is playing his Lyre \n"
            + "beautifully..");
        ares = new Room("in Ares' training area, where he is cleaning his chariot \n"
            + "quite aggressively..");
        artemis = new Room("in the shooting range, where Artemis is hitting bullseye \n"
            + "with her bow.");
        athena = new Room("in Athena's chambers, where she's peacefully spinning and \n"
            + "weaving..");
        demeter = new Room("in the farm, where Demeter is tending to her crops...");
        dionysus = new Room("in the dining room, where Dionysus is sipping on wine...");
        hephaestus = new Room("in Hephaestus' palace, where he's tinkering with some metal..");
        hestia = new Room("in Hestia's chambers, where she's sitting peacefully by \n"
            + "the Hearth..");
        persephone = new Room("in Persephone's chambers, where she's trimming some flowers...");
        Hthrone = new Room("inside the throne room of the ruler of the Underworld, Hades, who \n"
            + "looks extremely upset..");

        player.setRoom(throne);

        Object pomegranate, arrow, trident, ambrosia, apple, cake, mirror, bow,
        spear, helmet, bread, grape, hammer, caduceus;

        pomegranate = new Object("Pomegranate", "a fruit that a certain goddess may like...", 1);
        arrow = new Object("Arrow", "an arrow from a huntress..", 1);
        trident = new Object("Trident", "a mighty weapon belonging to the ruler of the seas..", 10);
        ambrosia = new Object("Ambrosia", "the special fruit of the gods..", 0.5);
        apple = new Object("Apple", "an enticing golden apple, suitable for the queen..", 0.3);
        cake = new Object("Cake", "a delicious, honey cake for the best pet of all..", 5);
        mirror = new Object("Mirror", "a shiny, golden mirror that reflects the beauty of the beholder..", 0.5);
        bow = new Object("Bow", "a powerful yet graceful weapon suitable only for the skilled..", 3);
        spear = new Object("Spear", "a mighty weapon that can cause catastrophic damage..", 6);
        helmet = new Object("Helmet", "a protective piece of armor..", 2);
        bread = new Object("Bread", "a delicious piece of bread..", 0.2);
        grape = new Object("Grape", "a perfectly ripe and juicy grape..", 0.1);
        hammer = new Object("Hammer", "a tinkering tool only for the best blacksmiths..", 6);
        caduceus = new Object("Caduceus", "a golden staff belonging to the messenger of the gods..", 7);

        apollo.addObject(arrow);
        apollo.addObject(trident);
        demeter.addObject(pomegranate);
        aphrodite.addObject(ambrosia);
        poseidon.addObject(apple);
        persephone.addObject(cake);
        hestia.addObject(mirror);
        ares.addObject(bow);
        artemis.addObject(helmet);
        athena.addObject(spear);
        dionysus.addObject(bread);
        demeter.addObject(grape);
        courtyard.addObject(hammer);
        throne.addObject(caduceus);

        throne.setExits("up", courtyard);
        throne.setExits("right", hera);
        throne.setExits("down", hades);
        throne.setExits("left", poseidon);
        hera.setExits("up", hermes);
        hera.setExits("left", throne);
        hera.setExits("down", demeter);
        hermes.setExits("down", hera);
        hermes.setExits("left", courtyard);
        hermes.setExits("up", aphrodite);
        hermes.setExits("right", ares);
        hades.setExits("up", throne);
        hades.setExits("down", persephone);
        hades.setExits("inside", Hthrone);
        poseidon.setExits("right", throne);
        poseidon.setExits("down", hestia);
        poseidon.setExits("up", athena);
        courtyard.setExits("right", hermes);
        courtyard.setExits("down", throne);
        courtyard.setExits("left", athena);
        courtyard.setExits("up", apollo);
        aphrodite.setExits("right", hephaestus);
        aphrodite.setExits("down", hermes);
        aphrodite.setExits("left", apollo);
        apollo.setExits("right", aphrodite);
        apollo.setExits("down", courtyard);
        apollo.setExits("left", artemis);
        ares.setExits("up", hephaestus);
        ares.setExits("left", hermes);
        artemis.setExits("right", apollo);
        artemis.setExits("down", athena);
        athena.setExits("right", courtyard);
        athena.setExits("down", poseidon);
        athena.setExits("up", artemis);
        demeter.setExits("up", hera);
        demeter.setExits("right", dionysus);
        demeter.setExits("down", persephone);
        dionysus.setExits("left", demeter);
        hephaestus.setExits("down", ares);
        hephaestus.setExits("left", aphrodite);
        hestia.setExits("up", poseidon);
        persephone.setExits("up-left", hades);
        persephone.setExits("up-right", demeter);
        Hthrone.setExits("outside", hades);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        println();
        println("Welcome to the magical World of Olympus!");
        println("Type 'help' if you need help or 'hint' if you want some clues!");
        println();
        printLocation();
    }

    private void printLocation()
    {
        this.currentRoom = player.getRoom();
        println(this.currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("talk")) {
            talk(command);   
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            quit(command);
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("items")) {
            items();
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("hint")) {
            hint();   
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        println("You are a demigod, an outcasted child of Zeus, born out of wedlock");
        println("from one of the many affairs he had with a mortal. You are looked");
        println("down upon by the other Gods and Goddesses, as well as their demigod");
        println("children. Zeus' thunderbolt has gone missing and you have been tasked");
        println("to find it in order to prove you're worthy of the status of a demigod.");
        println();
        println("Your command words are:");
        println(parser.getCommands());
    }

    /** 
     * Try to talk to a god?!
     */
    private void talk(Command command) 
    {
        if(!command.hasSecondWord()) {
            println("Talk to whom?");
            return;
        }

        String who = command.getSecondWord();
        if(who.contains("Zeus")) {
            if(currentRoom.getDescription().contains("mighty Zeus")) {
                Object thunderbolt = currentRoom.getObject("Thunderbolt");
                if(thunderbolt != null) {
                    println("Congratulations!! You have successfully retrieved \n"
                        + "Zeus' Thunderbolt and have earned your respect as a demigod \n"
                        + "and child of Zeus!" +
                        "Zeus: Thank you my child! You have done a good deed \n"
                        + "and you are worthy.");
                } else {
                    println(" Zeus: I can't believe the swine who would dare steal my thunderbolt!");
                }
            }
        } else if(who.contains("Hera")) {
            if(currentRoom.getDescription().contains("Queen Hera's chambers")) {
                Object apple = currentRoom.getObject("Apple");
                if(apple != null) {
                    println("Hera: I admit it! I stole his thunderbolt, it was \n"
                        + "of anger and jealousy for the women who created you. I can't \n"
                        + "believe you actually figured out that it was me..");
                }
            } else {
                println("Hera: If you're looking for Zeus' thunderbolt, I don't have it. Maybe \n"
                    + "you should check if Hades does, he's been acting suspicious, per \n"
                    + "usual.");
            }
        } else if(who.contains("Hermes")) {
            println("Heremes: I just got back from delivering a message, I'm not sure about \n"
                + "any of this stolen thunderbolt situation. I did overhear that \n"
                + "a shadowed figure was seen in the throne room yesterday...");
        } else if(who.contains("Hades")) {
            if(currentRoom.getDescription().contains("ruler of the Underworld")){
                Object apple = player.getObject("Apple");
                if(apple != null) {
                    println("Hades: Ah, the Golden Apple! Take it to the Queen in her \n"
                        + "chambers. The true theif will be revealed.");
                } 
            } else {
                println("Error: You can't talk to Hades yet! Come back another time..");
            }
        } else if(who.contains("Poseidon")) {
            if(currentRoom.getDescription().contains("Poseidon's palace")) {
                Object trident = currentRoom.getObject("Trident");
                Object trident1 = player.getObject("Trident");
                if(trident != null) {
                    println("Poseidon: A trident! Thank you for this and in return \n"
                        + "I shall allow you to take this golden apple.");
                } else if(trident1 != null) {
                    println("Poseidon: I don't have much to say to you right now. Find(drop) me \n"
                        + "something I desire, and then we can talk.");
                } 
            }
            println("Poseidon: Stolen thunderbolt? Hah, that sounds like a prank \n"
                + "Hades would pull.");
        } else if(who.contains("Aphrodite")) {
            println("Aphrodite: Zeus' thunderbolt? Have you asked Hera, I think \n"
                + "she's been acting a little weird lately..");
        } else if(who.contains("Apollo")) {
            println("Apollo: When the lost has been found, a true traitor emerges..");
        } else if(who.contains("Artemis")) {
            println("Artemis: Stolen thunderbolt? That sounds like something \n"
                + "only someone who's close to him would dare do..");
        } else if(who.contains("Ares")) {
            println("Ares: I'm training. This fiasco might cause a war to breaout.");
        } else if(who.contains("Athena")) {
            println("Athena: Faces can be decieving, child..");
        } else if(who.contains("Demeter")) {
            println("Demeter: Oh dear.. I sure hope the thief is caught..");
        }else if(who.contains("Dionysus")) {
            println("Dionysus: IT HAS TO BE HADES!!");
        }else if(who.contains("Hephaestus")) {
            println("Hephaestus: I'm surprised you wanted to talk to me. Since \n"
                + "you're here, I can say that it's probably not who you think it is.");
        }else if(who.contains("Hestia")) {
            println("Hestia: I believe you may have already connected the pieces..");
        }else if(who.contains("Persephone")) {
            if(currentRoom.getDescription().contains("Persephone's chambers")) {
                Object pomegranate = currentRoom.getObject("Pomegranate");
                if(pomegranate != null) {
                    println("Persephone: Thank you! You can take a honey cake and \n"
                        + "give it to Cerberus, it's his favorite snack! You'll be able \n"
                        + "to go inside and talk to Hades afterwards.");
                } else {
                    println("Persephone: If you can bring me a pomegranete, I may be able \n"
                        + "to help you talk to Hades."); 
                }
            }
        }
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        println(player.goRoom(direction));
    }

    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            println("Take what?");
            return;
        }

        String objectName = command.getSecondWord();
        println(player.take(objectName));
    }

    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            println("Drop what?");
            return;
        }

        String objectName = command.getSecondWord();
        println(player.drop(objectName));
    }

    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            println("Eat what?");
            return;
        }

        String objectName = command.getSecondWord();
        println(player.eat(objectName));
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            println("Quit what?");
            return;
        }

        wantToQuit = true;  // signal that we want to quit
    }

    private void back()
    {
        println(player.back());
    }

    private void look()
    {
        printLocation();
    }

    private void items()
    {
        println(player.items());
    }

    private String randomHint()
    {
        if (usedHints.size() == hints.length) {
            usedHints.clear();   
        }
        int index;
        while(true) {
            index = gen.nextInt(hints.length);
            if(!usedHints.contains(hints[index])) {
                break;
            }
        }
        usedHints.add(hints[index]);
        return hints[index];
    }

    private void hint()
    {
        String hint = randomHint();
        println("Hint: " + hint);
    }

    /****************************************************************
     * If you want to launch an Applet
     ****************************************************************/

    /**
     * @return an Image from the current room
     * @see Image
     */
    public String getImage()
    {
        Room currentRoom = player.getRoom();
        return currentRoom.getImage();
    }

    /**
     * @return an audio clip from the current room
     * @see AudioClip
     */
    public String getAudio()
    {
        Room currentRoom = player.getRoom();
        return currentRoom.getAudio();
    }

    /****************************************************************
     * Variables & Methods added 2018-04-16 by William H. Hooper
     ****************************************************************/

    private String messages;
    private boolean wantToQuit;

    /**
     * Initialize the new variables and begin the game.
     */
    private void start()
    {
        messages = "";
        wantToQuit = false;
        printWelcome();
    }

    /**
     * process commands or queries to the game
     * @param input user-supplied input
     */
    public void processInput(String input)
    {
        if(finished()) {
            println("This game is over.  Please go away.");
            return;
        }

        Command command = parser.getCommand(input);
        processCommand(command);
    }

    /**
     * clear and return the output messages
     * @return current contents of the messages.
     */
    public String readMessages()
    {
        if(messages == null) {
            start();
        }
        String oldMessages = messages;
        messages = "";
        return oldMessages;
    }

    /**
     * @return true when the game is over.
     */
    public boolean finished()
    {
        return wantToQuit;
    }

    /**
     * add a message to the output list.
     * @param message the string to be displayed
     */
    private void print(String message)
    {
        messages += message;
    }

    /**
     * add a message to the output list, 
     * followed by newline.
     * @param message the string to be displayed
     * @see readMessages
     */
    private void println(String message)
    {
        print(message + "\n");
    }

    /**
     * add a blank line to the output list.
     */
    private void println()
    {
        println("");
    }
}
