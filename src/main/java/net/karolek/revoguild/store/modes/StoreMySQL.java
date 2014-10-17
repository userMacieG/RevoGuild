package net.karolek.revoguild.store.modes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.karolek.revoguild.store.Store;
import net.karolek.revoguild.store.StoreMode;
import net.karolek.revoguild.utils.Logger;

public class StoreMySQL implements Store {

	private final String					host, user, pass, name, prefix;
	private final int						port;
	private Connection					conn;
	private Thread							t;

	private final LinkedList<String>	queries	= new LinkedList<String>();

	public StoreMySQL(String host, int port, String user, String pass, String name, String prefix) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.prefix = prefix;

		this.t = new Thread(this, "MySQL Thread");
		this.t.start();

	}

	@Override
	public boolean connect() {
		long start = System.currentTimeMillis();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Logger.info("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.name, this.user, this.pass);
			this.conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.name, this.user, this.pass);
			Logger.info("Connected to the MySQL server!", "Connection ping " + (System.currentTimeMillis() - start) + "ms!");
			return true;
		} catch (ClassNotFoundException e) {
			Logger.warning("JDBC driver not found!", "Error: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.warning("Can not connect to a MySQL server!", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void disconnect() {
		if (this.conn != null)
			try {
				this.conn.close();
			} catch (SQLException e) {
				Logger.warning("Can not close the connection to the MySQL server!", "Error: " + e.getMessage());
				e.printStackTrace();
			}
	}

	@Override
	public void reconnect() {
		this.connect();
	}

	@Override
	public boolean isConnected() {
		try {
			return !(this.conn.isClosed()) || (this.conn == null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet query(String query) {
		try {
			return conn.createStatement().executeQuery(query.replace("{P}", this.prefix));

		} catch (SQLException e) {
			Logger.warning("An error occurred with given query '" + query.replace("{P}", this.prefix) + "'!", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String update) {
		synchronized (this.queries) {
			this.queries.add(update);
		}
	}

	@Override
	public ResultSet updateNow(String update) {
		try {
			Statement stmt = conn.createStatement();

			stmt.executeUpdate(update.replace("{P}", this.prefix), Statement.RETURN_GENERATED_KEYS);
			return stmt.getGeneratedKeys();
		} catch (SQLException e) {
			Logger.warning("An error occurred with given query '" + update.replace("{P}", this.prefix) + "'!", "Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Connection getConnection() {
		return this.conn;
	}

	@Override
	public StoreMode getStoreMode() {
		return StoreMode.MYSQL;
	}

	@Override
	public void run() {
		while (true) {
			try {

				Thread.sleep(30000);

				if (!this.isConnected())
					this.reconnect();

				if (this.queries.size() > 0) {
					List<String> list = new ArrayList<String>();
					synchronized (this.queries) {
						list.addAll(this.queries);
						this.queries.clear();
					}
					for (String query : list)
						updateNow(query);

				} else {

					Statement s = this.conn.createStatement();
					s.executeQuery("DO 1");

				}

			} catch (Exception e) {
				Logger.severe("An error in the main store loop!");
				e.printStackTrace();
			}
		}
	}

}
