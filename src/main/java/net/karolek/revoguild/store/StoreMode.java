package net.karolek.revoguild.store;

public enum StoreMode {

    MYSQL("mysql"),
    SQLITE("sqlite");

    private final String name;

    StoreMode(String name) {
        this.name = name;
    }

    public static StoreMode getByName(String name) {
        for (StoreMode sm : values()) {
            if (sm.getName().equalsIgnoreCase(name)) {
                return sm;
            }
        }
        return SQLITE;
    }

    private String getName() {
        return name;
    }

}
