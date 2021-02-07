package com.github.ventuss.utils;

import org.bukkit.Location;

public class WorldUtilities {

    public WorldUtilities() {

    }

    public Location copyLocation(Location loc1) {
        return new Location(
                loc1.getWorld(),
                loc1.getX(),
                loc1.getY(),
                loc1.getZ()
        );
    }

    public boolean locationEqual(Location location, Location location1) {
        return location.getX() == location1.getX() &&
                location.getY() == location1.getY() &&
                location.getZ() == location1.getZ();
    }
}
