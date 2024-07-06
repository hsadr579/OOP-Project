public class Cell {
    private int damage;
    private int defence;
    private String id;

    public Cell(int damage, int defence, String id) {
        this.damage = damage;
        this.defence = defence;
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefence() {
        return defence;
    }

    public String getId() {
        return id;
    }
}
