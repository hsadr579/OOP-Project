package core;

public enum Spells {
    STAR_DESTROYER("16"),
    SHIELD("17"),
    HEAL("18"),
    CLOVER("19"),
    POISON("20"),
    FIXER("21"),
    ALARM("22"),
    HOLE("23"),
    THIEF("24"),
    SWAMP("25"),
    CLONE("26"),
    HIDDEN("27");

    private final String pattern;

    Spells(String pattern) {
        this.pattern = pattern;
    }

    public String get() {
        return pattern;
    }
}
