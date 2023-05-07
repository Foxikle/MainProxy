package me.foxikle.mainproxy;

import me.foxikle.mainproxy.commands.ChatToggleCommand;
import me.foxikle.mainproxy.commands.Lobby;
import me.foxikle.mainproxy.commands.Lobby2;
import me.foxikle.mainproxy.database.DataFetchures;
import me.foxikle.mainproxy.database.DatabaseCore;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.SQLException;
import java.util.logging.Level;

public final class MainServer extends Plugin {
    private DatabaseCore core;
    private DataFetchures dataFetchures = new DataFetchures(core);
    private static MainServer instance;

    @Override
    public void onEnable() {
        instance = this;
        getInstance().core = new DatabaseCore();
        try {
            core.connect();
        } catch (ClassNotFoundException | SQLException ignored) {
            this.getLogger().log(Level.SEVERE, "Invalid database credentials! Disabling plugin.");
            this.onDisable();
            return;
        }
        this.getLogger().log(Level.INFO, "Database connected.");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby2());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ChatToggleCommand());
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().unregisterListeners(this);
        getProxy().getPluginManager().unregisterCommands(this);
    }

    public static MainServer getInstance() {
        return instance;
    }

    public DatabaseCore getCore() {
        return core;
    }

    public DataFetchures getDataFetchures() {
        return dataFetchures;
    }
}
