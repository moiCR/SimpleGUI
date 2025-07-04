package git.snowk.simplegui.button

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

interface Button {

    fun getIcon() : ItemStack
    fun onClick(event : InventoryClickEvent)
    fun isInteractable() : Boolean

}