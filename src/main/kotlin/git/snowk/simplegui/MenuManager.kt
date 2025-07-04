package git.snowk.simplegui

import git.snowk.simplegui.listener.MenuListener
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.Optional
import java.util.UUID

class MenuManager(val plugin: JavaPlugin) {

    val menus : MutableMap<UUID, Menu> = mutableMapOf();

    init{
        MenuListener(this)
    }

    fun getOpenedMenu(player : Player) : Optional<Menu>{
        return Optional.ofNullable(menus[player.uniqueId])
    }

    fun register(player : Player , menu : Menu){
        menus[player.uniqueId] = menu;
    }

    fun unregister(player : Player){
        menus.remove(player.uniqueId);
    }
}