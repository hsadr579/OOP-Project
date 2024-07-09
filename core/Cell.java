package core;

public class Cell {
    private int damage;
    private int defence;
    private String id;
    private boolean active;
    private boolean isSpell = false;
    private Spells type = null;

    public Cell(Spells type) {
        this.type = type;
        isSpell = true;
    }

    public Cell(int damage, int defence, String id, boolean active) {
        this.damage = damage;
        this.defence = defence;
        this.id = id;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSpell() {
        return isSpell;
    }

    public Spells spellType() {
        return type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSpell(boolean spell) {
        isSpell = spell;
    }

    public void setType(Spells type) {
        this.type = type;
    }
}
