package me.foxikle.mainproxy.commands;

import me.foxikle.mainproxy.MainServer;
import me.foxikle.mainproxy.enums.ChatType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ChatToggleCommand extends Command {
    public ChatToggleCommand() {
        super("chat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(args.length == 0){
                ComponentBuilder builder = new ComponentBuilder("Invalid chat provided. (ALL, PARTY, GUILD)").color(ChatColor.RED);
                p.sendMessage(builder.create());
            } else if(args[0].equalsIgnoreCase("ALL")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.ALL);
            } else if(args[0].equalsIgnoreCase("PARTY")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.PARTY);
            } else if(args[0].equalsIgnoreCase("GUILD")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.GUILD);
            } else if(args[0].equalsIgnoreCase("PRIVATE")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.PRIVATE);
            } else if(args[0].equalsIgnoreCase("STAFF")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.STAFF);
            } else if(args[0].equalsIgnoreCase("ADMIN")) {
                MainServer.getInstance().getDataFetchures().setChatTypeAsync(p.getUniqueId(), ChatType.ADMIN);
            }
        }
    }
}
