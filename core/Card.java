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
    private int cost_buy;
    private int cost_upg;
    private int enough_level;
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
    
    public String toStringBuy() {
        if (group.equals("attacker")){
            return "ID:"+id+" | "+name+" | dur:"+duration+" | def:"+defence+" | dmg:"+damage+" | type:"+type+" | Price"+cost_buy;
        }
        else return "ID:"+id+" | "+name+" | dur:"+duration+" | def:"+defence+" | dmg:"+damage+" | type:"+type+" | "+explanation+" | Price"+cost_buy;
    }
    public String toStringUpgrade() {
        if (group.equals("attacker")){
            return "ID:"+id+" | "+name+"+"+level+" | dur:"+duration+" | def:"+defence+" | dmg:"+damage+" | type:"+type+" | Upgrade for"+cost_upg+" (Just for level+"+enough_level+" players!)";
        }
        else return "ID:"+id+" | "+name+"+"+level+" | dur:"+duration+" | def:"+defence+" | dmg:"+damage+" | type:"+type+" | "+explanation+" | Upgrade for"+cost_upg+" (Just for level+"+enough_level+" players!)";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCost_buy() {
        return cost_buy;
    }

    public void setCost_buy(int cost_buy) {
        this.cost_buy = cost_buy;
    }

    public int getCost_upg() {
        return cost_upg;
    }

    public void setCost_upg(int cost_upg) {
        this.cost_upg = cost_upg;
    }

    public int getEnough_level() {
        return enough_level;
    }

    public void setEnough_level(int enough_level) {
        this.enough_level = enough_level;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
