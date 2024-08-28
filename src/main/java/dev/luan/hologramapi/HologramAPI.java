package dev.luan.hologramapi;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.manager.player.PlayerManager;
import dev.luan.hologramapi.hologram.HologramManager;
import dev.luan.hologramapi.utils.ReplaceText;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import me.tofaa.entitylib.APIConfig;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.spigot.SpigotEntityLibPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public final class HologramAPI extends JavaPlugin {

    @Getter
    private static HologramAPI instance;

    @Getter
    private static ReplaceText replaceText;

    @Getter
    private static PlayerManager playerManager;

    @Getter
    private static HologramManager hologram;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().reEncodeByDefault(false)
                .checkForUpdates(false)
                .bStats(true);
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;
        PacketEvents.getAPI().init();


        SpigotEntityLibPlatform platform = new SpigotEntityLibPlatform(this);
        APIConfig settings = new APIConfig(PacketEvents.getAPI())
                .usePlatformLogger();

        EntityLib.init(platform, settings);

        playerManager = PacketEvents.getAPI().getPlayerManager();

        hologram = new HologramManager(this);


    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
