package git.snowk.simplegui.paginated.layout

import git.snowk.simplegui.button.Button
import git.snowk.simplegui.button.type.DecorationButton
import git.snowk.simplegui.button.type.NextButton
import git.snowk.simplegui.button.type.PrevButton
import git.snowk.simplegui.paginated.GUIPaginated


enum class NavigationLayout {

    TOP{
        override fun getSlot(slot: Int, maxButtons: Int, page: Int): Int {
            return slot - ((maxButtons) * (page - 1)) + 9
        }

        override fun getBar(gui: GUIPaginated): MutableMap<Int, Button> {
            return createBar(gui, 0, 8)
        }

    },

    BOTTOM{
        override fun getSlot(slot: Int, maxButtons: Int, page: Int): Int {
            return slot - ((maxButtons) * (page - 1))
        }

        override fun getBar(gui: GUIPaginated): MutableMap<Int, Button> {
            var nextSlot = gui.getSize() - 1;
            var prevSlot = nextSlot - 8;
            return createBar(gui, prevSlot, nextSlot)
        }

    };

    protected fun createBar(gui: GUIPaginated, prevSlot: Int, nextSlot: Int): MutableMap<Int, Button> {
        val buttons: MutableMap<Int, Button> = mutableMapOf()
        buttons.put(prevSlot, PrevButton(gui))
        buttons.put(nextSlot, NextButton(gui))

        for (i in prevSlot + 1..<nextSlot) {
            buttons.put(i, DecorationButton())
        }

        return buttons
    }

    abstract fun getSlot(slot: Int, maxButtons: Int, page: Int): Int
    abstract fun getBar(gui: GUIPaginated): MutableMap<Int, Button>
}