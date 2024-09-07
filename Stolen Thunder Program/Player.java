import java.util.Stack;
import java.util.HashMap;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> roomStack;
    private HashMap<String, Object> objects;
    private double weightLimit;
    
    public Player()
    {
        roomStack = new Stack<>();
        objects = new HashMap<>();
        weightLimit = 10;
        currentRoom = null;
    }
    
    public void setRoom(Room r)
    {
        currentRoom = r;
    }
    
    public Room getRoom()
    {
        return currentRoom;
    }
    
    public String goRoom(String direction)
    {
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            return("There's no where to go!");
        }
        else {
            
            if(nextRoom.getDescription().contains("ruler of the Underworld")) {
                Object cake = currentRoom.getObject("Cake");
                if(cake == null) {
                    return "You can't enter Hades' throne room unless Cerberus \n"
                    + "is satisfied!";
                }
            }
            roomStack.push(currentRoom);
            currentRoom = nextRoom;
            return(currentRoom.getLongDescription());
        }
    }
    
    public String back()
    {
        if(roomStack.empty()) {
            return("There is nowhere to go back to...");
        } else {
            currentRoom = roomStack.pop();
            return(currentRoom.getLongDescription());
        }
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
    
    public String take(String objectName)
    {
        Object objects = currentRoom.getObject(objectName);
        if(objects == null) {
            return "There is no " + objectName + " in this room..";
        }
        
        double weightCurrently = getWeight();
        
        if(currentRoom.getDescription().contains("Persephone's chambers")) {
            Object pomegranate = currentRoom.getObject("Pomegranate");
            if(pomegranate == null) {
                return "You can't take that unless Persephone's condition has \n"
                + "been satisfied!";
            }
        }
        
        if(currentRoom.getDescription().contains("Poseidon's palace")) {
            Object trident = currentRoom.getObject("Trident");
            if(trident == null) {
                return "You can't take that unless Posidon's condition has \n"
                + "been satisfied!";
            }
        }
        
        if(weightCurrently >= weightLimit) {
            return "You can't carry that much! Your carried item(s) weigh: " 
            + weightCurrently + "\n" + "The weight limit is: " + weightLimit;
        }
        
        currentRoom.removeObject(objectName);
        addObject(objects);
        return "You have acquired a(n) " + objectName + "! \n"
            + objectName + ": " + objects.getDescription();
    }
    
    public String drop(String objectName)
    {
        Object objects = getObject(objectName);
        
        if(objects == null) {
            return "You don't have a(n) " + objectName + " to drop!";
        }
        
        if(currentRoom.getDescription().contains("Queen Hera's chambers") && objectName.equals("Apple")){
            Object thunderbolt = new Object("Thunderbolt", "a powerful weapon suitable for the king of Gods!", 15);
            currentRoom.addObject(thunderbolt);
            currentRoom.addObject(objects);
            removeObject(objectName);
            return "You have given Hera the Golden Apple, and a hidden object, 'Thunderbolt', appears!!";
        }
        
        if(currentRoom.getDescription().contains("mighty Zeus")) {
            Object Thunderbolt = currentRoom.getObject("Thunderbolt");
            if(Thunderbolt != null) {
                return "Congratulations!! You have successfully retrieved \n"
                + "Zeus' Thunderbolt and have earned your respect as a demigod \n"
                + "and child of Zeus!";
            }
        }
        
        currentRoom.addObject(objects);
        removeObject(objectName);
        return "You have dropped a(n) " + objectName + ".";
    }
    
    public String eat(String objectName)
    {
        Object food = getObject(objectName);
        if(food == null) {
            return "You don't have a(n) " + objectName + " to eat!";  
        }
        
        if(!food.getName().contains("Ambrosia")) {
            return "You can't eat that!";
        } else {
            setWeightLimit(20);
        }
        
        removeObject(objectName);
        return "You have eaten " + food.getDescription() + ": Ambrosia! \n"
            + "Your weight limit has now increased to: " + getWeightLimit() + " pounds";
    }
    
    private double getWeight()
    {
        double sum = 0;
        //loop to add all the weights to the sum
        for(Object o : objects.values()) {
            if(currentRoom.getObject(o.getName()) == null) {
            sum += o.getWeight();
            }
        }
        return sum;
    }
    
    public String items()
    {
        String ob = "Object(s) carried: ";
        double sum = 0;
        for(Object o : objects.values()) {
            if(objects.values().size() == 1) {
                ob = ob + o.getName();
            } else if(objects.values().size() >= 2) {
                ob = ob + o.getName() + ", ";
            }
            
            if(currentRoom.getObject(o.getName()) == null) {
                sum += o.getWeight();
            }
        }
        return ob + "\n" + "Total weight: " + sum + " pounds";
    }
    
    public double setWeightLimit(double weightLimit)
    {
        this.weightLimit = weightLimit;
        return weightLimit;
    }
    
    public double getWeightLimit()
    {
        return weightLimit;
    }
}
