package com.github.ventuss.config;

import com.github.ventuss.App;
import com.github.ventuss.game.IGame;
import com.github.ventuss.game.factions.Totem;
import com.github.ventuss.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TotemConfiguration extends TotemDefaultValue implements IConfiguration {

    private final FileConfiguration config;

    public TotemConfiguration() {
        config = App.getInstance().getConfig();
        save();
    }

    @Override
    public void save() {
        App.getInstance().saveConfig();
    }

    @Override
    public void setMessageConfig(MessageType messageType, String message) {

    }

    @Override
    public String getMessageConfig(MessageType messageType, IGame game, Player player) {
        return null;
    }

    @Override
    public void setMessageConfig(MessageType messageType, String message, IGame totem) {
        switch (messageType) {
            case TOTEM_WIN:
                this.config.set(".Totem.totems"+totem.getName()+".winMessage", message);
                break;
            case TOTEM_TIMER:
                this.config.set(".Totem.totems"+totem.getName()+".timerMessage", message);
                break;
            case TOTEM_STOP:
                this.config.set(".Totem.totems"+totem.getName()+".stopMessage", message);
                break;
            case TOTEM_SPAWN:
                this.config.set(".Totem.totems"+totem.getName()+".startMessage", message);
                break;
            case TOTEM_FIRST_BREAK_BLOCK:
                this.config.set(".Totem.totems"+totem.getName()+".firstBreakMessage", message);
                break;
            case TOTEM_BREAK_CANCEL:
                this.config.set(".Totem.totems"+totem.getName()+".resetBreakMessage", message);
                break;
            case TOTEM_BREAK_BLOCK:
                this.config.set(".Totem.totems."+totem.getName()+".breakMessage", message);
                break;
            default:
                break;
        }
        save();
    }


    public Location getLocationConfig(IGame totem) {
        String worldName;

        if (config.get(".Totem.totems."+totem.getName()+".location") == null) {
            App.getInstance().getLogger().info("location problem.");
            return null;
        }
        if (config.get(".Totem.totems."+totem.getName()+".world") == null) {
            App.getInstance().getLogger().info("world name problem");
            return null;
        }
        worldName = config.get(".Totem.totems."+totem.getName()+".world").toString();
        if (App.getInstance().getServer().getWorld(worldName) == null) {
            App.getInstance().getLogger().info("world not exist.");
            return null;
        }

        if (App.utilities == null) {
            App.getInstance().getLogger().info("utilities null.");
            return null;
        }
        if (App.utilities.conversionUtilities == null) {
            App.getInstance().getLogger().info("utilities convertion null.");
            return null;
        }
        return App.utilities.conversionUtilities.stringToLocation(
                App.getInstance().getServer().getWorld(worldName),
                config.get(".Totem.totems."+totem.getName()+".location").toString()
        );
    }

    public List<IGame> getAllTotem() {
        if (config == null)
            return null;
        App.getInstance().getLogger().info("GET ALL TOTEM...");
        ConfigurationSection sec = this.config.getConfigurationSection(".Totem.totems");
        List<IGame> totems = new ArrayList<IGame>();
        List<String> totemName = new ArrayList<String>();
        for(String key : sec.getKeys(false)){
            totemName.add(key);
            App.getInstance().getLogger().info(key);
        }
        for (String s : totemName) {
            IGame totem = App.getInstance().newGame(s);
            if (totem == null)
                return null;
            totem.setLocation(getLocationConfig(totem));
            totem.setSize(getSizeToConfig(totem.getName()));
            totem.setActualSize(totem.getSize());
            //totem.setBlockMaterial(getBlockMaterialToConfig(totem.getName()));
            //totem.setItemInteract(getItemInteractionConfig(totem.getName()));
            totem.setPtsForWin(getPtsToConfig(totem.getName()));
            totem.generateTotem();
            totems.add(totem);
        }
        return totems;
    }

    public Material getBlockMaterialToConfig(String name) {
        if (config.get(".Totem.totems."+name+".material") == null && Material.getMaterial(
                config.get(".Totem.totems."+name+".material").toString()) == null)
            return blockMaterial;
        return Material.getMaterial(
                config.get(".Totem.totems."+name+".material").toString()
        );
    }

    public int getPtsToConfig(String name) {
        if (config.get(".Totem.totems."+name+".ptsForWin") == null)
            return ptsForWin;
        return Integer.parseInt(config.get(".Totem.totems."+name+".ptsForWin").toString());

    }

    public int getSizeToConfig(String name) {
        if (config.get(".Totem.totems."+name+".size") == null)
            return size;
        return Integer.parseInt(config.get(".Totem.totems."+name+".size").toString());
    }

    public void setLocationConfig(IGame totem) {
        config.set(".Totem.totems."+totem.getName()+".location", App.utilities.conversionUtilities.locationToString(totem.getLocation()));
        config.set(".Totem.totems."+totem.getName()+".world", totem.getLocation().getWorld().getName());
        save();
    }

    public Material getItemInteractionConfig(String name) {
        if (config.get(".Totem.totems."+name+".itemInteract") == null &&
                Material.getMaterial(config.get(".Totem.totems."+name+".itemInteract").toString()) == null)
            return itemInteract;
        return Material.getMaterial(
                config.get(".Totem.totems."+name+".itemInteract").toString()
        );
    }

    public void setItemInteractionConfig(IGame totem) {
        config.set(".Totem.totems."+totem.getName()+".itemInteract", totem.getItemInteract().toString());
        save();
    }

}
