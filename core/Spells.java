package core;

public enum Spells {
    SHIELD("17");
    
    private final String pattern;

    Spells(String pattern) {
        this.pattern = pattern;
    }

    public String get() {
        return pattern;
    }
}
