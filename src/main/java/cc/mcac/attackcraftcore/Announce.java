package cc.mcac.attackcraftcore;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Announce extends Command {

    public Announce() {
        super("a");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN +
                    "正确用法：/a <消息>"));
//            sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN+
//                    "这将消耗你一个喇叭");
        } else {
            TextComponent broadcast;
            if (sender instanceof ProxiedPlayer) {
                String prefix = "§7[§6跨服§7]§r ";
                broadcast = new TextComponent(
                        prefix + " §e" + sender + " §7> §r" + sewString(args)
                );
            } else {
                broadcast = new TextComponent(
                        "§7[§6Server§7]§r §7> §r" + sewString(args)
                );
            }
            ProxyServer.getInstance().broadcast(broadcast);
        }
    }

    private String sewString(String[] args) {
        StringBuilder msg = new StringBuilder();
        for (String str : args) {
            msg.append(str).append(" ");
        }
        return msg.toString();
    }
}


