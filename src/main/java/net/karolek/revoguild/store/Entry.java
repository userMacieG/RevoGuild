package net.karolek.revoguild.store;

public interface Entry {

    void insert();

    void update(boolean now);

    void delete();

}
