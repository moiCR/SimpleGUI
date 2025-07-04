package git.snowk.simplegui.decoration

import git.snowk.simplegui.GUI

enum class DecorationType {

    FILL{
        override fun decorate(menu: GUI) {
            val size = menu.getSize();
            val item = menu.decorationItem;
            val inventory = menu.inventory!!;

            if (item == null) return;
            
            for (i in 0 until size){
                inventory.setItem(i, item)
            }
        }


    },

    BORDER{
        override fun decorate(menu: GUI) {
            val size = menu.getSize()
            val item = menu.decorationItem
            val inventory = menu.inventory!!

            if (item == null) return;

            for (i in 0 until 9) {
                if (i < size) inventory.setItem(i, item)
            }

            val lastRowStart = size - (size % 9)
            for (i in lastRowStart until size) {
                inventory.setItem(i, item)
            }

            for (i in 0 until size / 9) {
                inventory.setItem(i * 9, item)
                if (size > 9) {
                    inventory.setItem(i * 9 + 8, item)
                }
            }
        }
        
    };
    
    
    abstract fun decorate(menu : GUI)
}