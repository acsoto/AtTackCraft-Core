package cc.mcac.attackcraftcore;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class miraiMC implements Listener {
    private Attackcraftcore plugin;

    miraiMC(Attackcraftcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGroupMessageReceive(MiraiGroupMessageEvent e) {
        plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
                if (e.getMessage().startsWith("##")) {
                    plugin.getProxy().broadcast(new TextComponent(
                            "§7[§6§l群消息§7]" + "§7[§e" + e.getSenderName() + "§7]: " + e.getMessage().substring(2)));
                }
            }
        });
    }

    @EventHandler
    public void onBungeeCordMessageReceive(ChatEvent e) {
        plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
                if (e.getMessage().startsWith("##")) {
                    ProxiedPlayer player = (ProxiedPlayer) e.getSender();
                    MiraiBot.getBot(plugin.getConfiguration().getLong("mirai.qq")).getGroup(plugin.getConfiguration().getLong("mirai.group"))
                            .sendMessage("[" + player.getServer().getInfo().getName() + "]" + "[" + e.getSender() + "]: " + e.getMessage().substring(2));
                }
            }
        });

    }
}
