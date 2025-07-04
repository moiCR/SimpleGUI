package git.snowk.simplegui

import git.snowk.simplegui.listener.GUIListener
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.Optional
import java.util.UUID

class GUIManager(val plugin: JavaPlugin) {

    val menus : MutableMap<UUID, GUI> = mutableMapOf();

    init{
        GUIListener(this)
    }

    fun getOpenedMenu(player : Player) : Optional<GUI>{
        return Optional.ofNullable(menus[player.uniqueId])
    }

    fun register(player : Player , menu : GUI){
        menus[player.uniqueId] = menu;
    }

    fun unregister(player : Player){
        menus.remove(player.uniqueId);
    }
}