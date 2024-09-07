/**
 * Write a description of class Object here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Object
{
    // instance variables - replace the example below with your own
    private String description;
    private String name;
    private double weight;

    /**
     * Constructor for objects of class Object
     * @param name The name of the object
     * @param description The description of the object
     * @param w The weight of the object
     */
    public Object(String name, String description, double w)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        weight = w;
    }
    
    public Object(String name)
    {
        // initialise instance variables
        this.name = name;
        description = name;
        weight = 5;
    }

    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;   
    }
    
    public double getWeight()
    {
        return weight;
    }
}
