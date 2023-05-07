package me.foxikle.mainproxy.enums;

import net.md_5.bungee.api.ChatColor;

public enum ChatType {
    ALL("", 1),
    PARTY(ChatColor.DARK_GREEN + "Party> ", 1),
    PRIVATE(ChatColor.AQUA + "PM> ",1 ),
    GUILD(ChatColor.YELLOW + "Guild> ", 1),
    STAFF(ChatColor.GOLD + "Staff> ", 80),
    ADMIN(ChatColor.DARK_RED + "Admin> ", 90);

    private final String prefix;
    private final int powerLevel;

    ChatType(String prefix, int powerLevel){
        this.prefix = prefix;
        this.powerLevel = powerLevel;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPowerLevel() {
        return powerLevel;
    }
    public ChatType ofString(String str){
        if(str == null) return ALL;
        switch (str){
            case "ALL":
                return ALL;
            case "PARTY":
                return PARTY;
            case "PRIVATE":
                return PRIVATE;
            case "GUILD":
                return GUILD;
            case "STAFF":
                return STAFF;
            case "ADMIN":
                return ADMIN;
        }

        return ALL;
    }
}
