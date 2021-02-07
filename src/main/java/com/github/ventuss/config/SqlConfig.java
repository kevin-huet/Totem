package com.github.ventuss.config;

import com.github.ventuss.App;
import com.massivecraft.factions.entity.Faction;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;

public class SqlConfig {

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private final FileConfiguration config;
    private static Connection connection;

    public SqlConfig() {
        config = App.getInstance().getConfig();
        initSqlFromConfig();
    }

    public void initSqlFromConfig() {
        if (this.config.get("Totem.sql.enable") == null)
            return;
        if (!Boolean.parseBoolean(this.config.get("Totem.sql.enable").toString()))
            return;
        this.host = this.config.get("Totem.sql.host").toString();
        this.port = this.config.get("Totem.sql.port").toString();
        this.database = this.config.get("Totem.sql.database").toString();
        this.username = this.config.get("Totem.sql.username").toString();
        this.password = this.config.get("Totem.sql.password").toString();
        try {
            openConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE totem (" +
                    "id INT(11) NOT NULL auto_increment," +
                    "faction VARCHAR (50) NOT NULL," +
                    "win INT(5) DEFAULT 0," +
                    "points INT(5) DEFAULT 0," +
                    "PRIMARY KEY (id)" +
                    ")");
        } catch (SQLException e) {
            App.getInstance().getLogger().info(e.getMessage());
        }
    }

    public void addWinner(String faction, int pts) {
        if (!Boolean.parseBoolean(this.config.get("Totem.sql.enable").toString()))
            return;
        try {
            openConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM totem WHERE faction = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE totem SET win = win + 1, points = points + ? WHERE faction = ?");
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO totem (faction, win, points) VALUES (?, ?, ?)");
            statement.setString(1, faction);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.setInt(1, pts);
                preparedStatement.setString(2, faction);
                preparedStatement.execute();
            } else {
                preparedStatement1.setString(1, faction);
                preparedStatement1.setInt(2, 1);
                preparedStatement1.setInt(3, pts);
                preparedStatement1.execute();
            }
        } catch (SQLException e) {
            App.getInstance().getLogger().info(e.getMessage());
        }
    }

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + this.host+ ":" + this.port + "/" + this.database,
                this.username, this.password);
    }


}
