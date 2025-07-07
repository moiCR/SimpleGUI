package git.snowk.simplegui.button.type

import git.snowk.simplegui.button.Button
import git.snowk.simplegui.item.ItemBuilder
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class DecorationButton : Button {
    override fun getIcon(): ItemStack {
        return ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
        .setDisplayName(" ")
        .build()
    }

    override fun onClick(event: InventoryClickEvent) {

    }

    override fun isInteractable(): Boolean {
        return false
    }
}