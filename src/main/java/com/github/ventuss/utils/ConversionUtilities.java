package com.github.ventuss.utils;

import com.github.ventuss.App;
import com.github.ventuss.game.Status;
import org.bukkit.Location;
import org.bukkit.World;

public class ConversionUtilities {

    public String locationToString(Location location) {
        return (int) location.getX()+";"+
                (int) location.getY()+";"+
                (int) location.getZ();
    }

    public Location stringToLocation(World world, String string) {
        String[] tab = string.split(";");
        if (world == null || tab.length < 3 || !tabIsNumeric(tab))
            return null;
        return new Location(
                world,
                Double.parseDouble(tab[0]),
                Double.parseDouble(tab[1]),
                Double.parseDouble(tab[2])
        );
    }

    public boolean tabIsNumeric(String[] strings) {
        for (String s : strings) {
            if (!isNumeric(s))
                return false;
        }
        return true;
    }


    public String statusToString(Status status) {
        switch (status) {
            case IN_WAITING: return "en attente";
            case STARTED: return "commencé";
            case FINISHED: return "fini";
            default: break;
        }
        return " ";
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
