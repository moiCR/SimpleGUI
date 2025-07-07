package git.snowk.simplegui.paginated

import git.snowk.simplegui.GUI
import git.snowk.simplegui.GUIManager
import git.snowk.simplegui.button.Button
import git.snowk.simplegui.paginated.layout.NavigationLayout
import org.bukkit.entity.Player
import kotlin.math.ceil

abstract class GUIPaginated(manager: GUIManager, player: Player) : GUI(manager, player) {

    val navigateBar : MutableMap<Int, Button> = mutableMapOf()
    var page : Int = 0
    var layout : NavigationLayout = NavigationLayout.TOP


    init {
        navigateBar.putAll(layout.getBar(this))
    }


    fun getFinalButtons() : MutableMap<Int, Button>{
        val finalButtons = mutableMapOf<Int, Button>()
        val size = getSize()
        val maxItems = size - 9

        getPaginatedButtons().forEach { (slot, button) -> {
            finalButtons.put(layout.getSlot(slot, maxItems, page), button)
        } }

        finalButtons.putAll(navigateBar)

        return finalButtons
    }

    fun next(){
        if (page <= getMaxPages()) return
        page += 1
        this.update()
    }

    fun prev(){
        if (page == 0) return
        page -= 1
        this.update()
    }

    override fun getButtons(): MutableMap<Int, Button> {
        return getFinalButtons()
    }

    fun getMaxPages(): Int {
        val maxElements: Int = getSize()
        val totalElements = getPaginatedButtons().size
        return ceil(totalElements.toDouble() / maxElements).toInt()
    }

    abstract fun getPaginatedButtons() : MutableMap<Int, Button>
}