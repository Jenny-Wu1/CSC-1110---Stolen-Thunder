import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    private String imageName;
    private String audioName;
    private HashMap<String, Object> objects;
    private HashMap<String, Room> rooms;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        rooms = new HashMap<>();
        objects = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction The direction of the exits
     * @param room The room you are setting the exits for
     */
    public void setExits(String direction, Room room) 
    {
        rooms.put(direction,room);
    }

    public Room getExit(String direction)
    {
        return rooms.get(direction);
    }

    private String getExitString()
    {
        String message = "Exits: ";
        for(String d : rooms.keySet()) {
            message += " " + d;
        }
        return message;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getObjectString();
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public void addObject(Object o)
    {
        String name = o.getName();
        objects.put(name,o);
    }
    
    public Object getObject(String objectName)
    {
        return objects.get(objectName);
    }
    
    public Object removeObject(String objectName)
    {
        return objects.remove(objectName);
    }
    
    public String getObjectString()
    {
        String ob = "Objects: ";
        for(Object o : objects.values()) {
            if(objects.values().size() == 1) {
                ob = ob + o.getName();
            } else if(objects.values().size() >= 2) {
                ob = ob + o.getName() + ", ";
            }
        }
        return ob;
    }
    
    /*************************************************************
     * added by William H. Hooper, 2006-11-28
     *************************************************************/
    /**
     * @return a String, which hopefully contains the file name
     * of an Image file.
     */
    public String getImage()
    {
        return imageName;
    }

    /**
     * associate an image with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format viewable in the Java AWT.
     * Readable formats include: 
     * PNG, JPG (RGB color scheme only), GIF
     */
    public void setImage(String filename)
    {
        imageName = "media/" + filename;
    }

    /**
     * @return a String, which hopefully contains the file name
     * of an audio file.
     */
    public String getAudio()
    {
        return audioName;
    }

    /**
     * associate an audio clip with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format palyable in the Java AWT.
     * Readable formats include: 
     * WAV, AU.
     */
    public void setAudio(String filename)
    {
        audioName = "media/" + filename;
    }
}
