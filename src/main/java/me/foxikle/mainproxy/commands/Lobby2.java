package me.foxikle.mainproxy.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Lobby2 extends Command {
    public Lobby2() {
        super("Lobby2");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            p.sendMessage(new ComponentBuilder("Connecting you to the lobby!").color(ChatColor.RED).create());
            p.connect(ProxyServer.getInstance().getServerInfo("lobby2"));

        }
    }
}
