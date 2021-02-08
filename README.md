# Totem

![alt text](https://user-images.githubusercontent.com/48879237/107246019-18ae9080-6a30-11eb-858c-5baecbac2cca.png)

What is it ?

Totem is a plugin that brings a PVP event addition to the Faction plugin. Totem introduces a point system. Factions can win points by controlling totems.
To control a totem, all the blocks of the totem must be broken by players from the same faction using a diamond sword (or any item you set in the configuration file).
If a player from another faction breaks a block from the totem, it is then reset and the breaking chain starts again for the other faction.
The points can be used to rank factions in a server, making totems a point of interest amongst pvp players.

Dependencies :
- Factions
- LegacyFaction
- FactionsUUID

Metrics :
https://bstats.org/plugin/bukkit/Totem/10245

Permissions :
- totem.set
- totem.spawn
- totem.change.size
- totem.list
- totem.info
- totem.change.block.material
- totem.unset
- totem.change.interact.item
- totem.reset
- totem.help
- totem.stop

Commands :
/totem help [page] - help
/totem set [name] - create and set totem
/totem unset [name] - delete and unset totem
/totem spawn [name] - spawn totem
/totem stop [name] - stop totem
/totem item [name] [material] - set item material
/totem block [name] [material] - set block material
/totem reset [name] - reset totem
/totem info [name] - show totem info
/totem list - show list of totems
/totem size [name] [size] - set totem size
