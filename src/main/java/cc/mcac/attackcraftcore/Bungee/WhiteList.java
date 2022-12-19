package cc.mcac.attackcraftcore.Bungee;

import cc.mcac.attackcraftcore.ACBungee;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class WhiteList implements Listener {

    private final ACBungee plugin;
    private final HashSet<String> whiteList;

    public WhiteList(ACBungee plugin) {
        this.plugin = plugin;
        whiteList = new HashSet<>();
        loadWhitelist();
    }

    @EventHandler
    public void onPlayerJoin(PreLoginEvent e) {
        if (plugin.getConfiguration().getBoolean("whitelist")) {
            if (!whiteList.contains(e.getConnection().getName())) {
                e.setCancelReason(new TextComponent("§c您不在白名单中, 请在群14603699申请白名单"));
                e.setCancelled(true);
            }
        }
    }

    private void loadWhitelist() {
        Connection connection = plugin.getSqlManager().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM `whitelist`"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                whiteList.add(rs.getString("player_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
