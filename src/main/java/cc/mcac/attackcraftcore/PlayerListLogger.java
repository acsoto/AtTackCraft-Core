package cc.mcac.attackcraftcore;

import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class PlayerListLogger {

    private Attackcraftcore plugin;
    private Connection connection;

    public PlayerListLogger(Attackcraftcore plugin) {
        this.plugin = plugin;
        connectMySQL();
    }

    public void run() {
        plugin.getLogger().info("PlayerListLogger 开始工作");
        plugin.getProxy().getScheduler().schedule(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.getLogger().info("玩家列表更新");
                try {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO `server_player_list` (server_id, player_number, player_list) VALUES (?,?,?) " +
                                    "ON DUPLICATE KEY UPDATE player_number = ?, player_list = ?"
                    );
                    int playerNumber = plugin.getProxy().getOnlineCount();
                    String playerList = plugin.getProxy().getPlayers().toString();
                    ps.setString(1, plugin.getConfiguration().getString("server_id"));
                    ps.setInt(2, playerNumber);
                    ps.setString(3, playerList);
                    ps.setInt(4, playerNumber);
                    ps.setString(5, playerList);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    private void connectMySQL() {
        Configuration config = plugin.getConfiguration();
        String ip = config.getString("mysql.host");
        String databaseName = config.getString("mysql.database");
        String userName = config.getString("mysql.username");
        String userPassword = config.getString("mysql.password");
        int port = 3306;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":" + port + "/" + databaseName + "?autoReconnect=true&useSSL=false",
                    userName, userPassword
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private String getPlayersList(){
//        plugin.getProxy().
//    }


}
