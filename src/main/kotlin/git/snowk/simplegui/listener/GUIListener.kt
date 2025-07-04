package git.snowk.simplegui.listener

import git.snowk.simplegui.GUIManager
import git.snowk.simplegui.sound.CompatibleSound
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class GUIListener (val manager: GUIManager) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, manager.plugin)
    }

    @EventHandler
    fun onClick(event : InventoryClickEvent){
        val player = event.whoClicked as Player
        val optMenu = manager.getOpenedMenu(player)

        optMenu.ifPresent { menu ->{
            val buttons = menu.getButtons()
            event.isCancelled = true

            buttons.forEach { (slot, button) -> {
                if (slot == event.slot){
                    button.onClick(event)
                    event.isCancelled = !button.isInteractable()
                    if (menu.soundOnClick) CompatibleSound.CLICK.play(player)
                }
            } }

        } }
    }

    @EventHandler
    fun onClose(event : InventoryCloseEvent){
        val player = event.player as Player
        val optMenu = manager.getOpenedMenu(player)

        optMenu.ifPresent { menu ->{
            menu.onClose()
            menu.clean()
        } }

    }

}