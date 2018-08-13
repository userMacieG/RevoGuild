package net.karolek.revoguild.store;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Store {

    Connection getConnection();

    boolean connect();

    void disconnect();

    void reconnect();

    boolean isConnected();

    ResultSet query(String query);

    void update(boolean now, String update);

    StoreMode getStoreMode();

}
