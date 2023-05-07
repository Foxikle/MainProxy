package me.foxikle.mainproxy.database;

import me.foxikle.mainproxy.MainServer;
import me.foxikle.mainproxy.enums.ChatType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataFetchures {
    private final DatabaseCore core;
    private Map<ProxiedPlayer, ChatType> chatTypes = new HashMap<>();
    public DataFetchures(DatabaseCore core){
        this.core = core;
    }


    public void loadChatTypeAsync(UUID uuid) {
        MainServer.getInstance().getProxy().getScheduler().runAsync(MainServer.getInstance(), () -> {
            try {
                PreparedStatement ps = core.getConnection().prepareStatement("SELECT chat FROM chattable WHERE uuid = ?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String str = rs.getString("data");
                    if (str == null || str.equals("")) chatTypes.put(ProxyServer.getInstance().getPlayer(uuid), ChatType.ALL);
                    chatTypes.put(ProxyServer.getInstance().getPlayer(uuid), ChatType.valueOf(str));
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void setChatTypeAsync(UUID uuid, ChatType type) {
        chatTypes.put(ProxyServer.getInstance().getPlayer(uuid), type);
        MainServer.getInstance().getProxy().getScheduler().runAsync(MainServer.getInstance(), () -> {
            try {
                PreparedStatement ps = core.getConnection().prepareStatement("UPDATE chat SET chattable = ? WHERE uuid = ?");
                ps.setString(1, type.toString());
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
