package core;  
// this class saves all properties of a card 
public class Card {
    private String id;
    private String name;
    private int duration;
    private int defence;
    private int damage;
    private String explanation;
    private String type;
    private int level;
    private String group;
    //private String img;


    public Card(String id, String name, int duration, int defence, int damage, String explanation, String type,
            int level, String group) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.defence = defence;
        this.damage = damage;
        this.explanation = explanation;
        this.type = type;
        this.level = level;
        this.group = group;
    }
    
    public String toString() {
        return "";
    }
}
