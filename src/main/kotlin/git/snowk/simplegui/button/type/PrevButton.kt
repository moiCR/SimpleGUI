package git.snowk.simplegui.button.type

import git.snowk.simplegui.button.Button
import git.snowk.simplegui.paginated.GUIPaginated
import git.snowk.simplegui.sound.CompatibleSound
import git.snowk.simplegui.item.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class PrevButton(val guiPaginated: GUIPaginated) : Button {

    override fun getIcon(): ItemStack {
        return ItemBuilder(Material.ARROW)
            .setDisplayName("&cPrevious Page")
            .build()
    }

    override fun onClick(event: InventoryClickEvent) {
        guiPaginated.prev()
        CompatibleSound.CLICK.play(event.whoClicked as Player)
    }

    override fun isInteractable(): Boolean {
        return false
    }
}