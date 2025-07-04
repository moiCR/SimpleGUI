# SimpleGUI

This project is an example implementation of the SimpleGUI API for creating graphical interfaces in Minecraft plugins.

## Features

- Interactive menu system
- Automatic decoration
- Button system
- Automatic menu updates

## Basic Usage

To implement SimpleGUI in your plugin:

1. Initialize `GUIManager` in your plugin's main class:

```kotlin
class YourPlugin : JavaPlugin() {
    private lateinit var guiManager: GUIManager

    override fun onEnable() {
        guiManager = GUIManager(this)
        // Rest of your code
    }
}
```

2. Create your own menus by extending the `GUI` class:

```kotlin
class ExampleMenu(manager: GUIManager, player: Player, autoUpdate: Boolean = false) : GUI(manager, player, autoUpdate) {

    override fun getTitle(): String {
        return "Example Menu"
    }

    override fun getSize(): Int {
        return 9;
    }

    override fun getButtons(): MutableMap<Int, Button> {
        val buttons : MutableMap<Int, Button> = mutableMapOf();

        buttons[4] = object : Button{

            override fun getIcon(): ItemStack {
                val icon : ItemStack = ItemStack(Material.DIAMOND_SWORD)
                val meta = icon.itemMeta
                meta?.setDisplayName("${ChatColor.GREEN}Example Item")
                icon.itemMeta = meta
                return icon;
            }

            override fun onClick(event: InventoryClickEvent) {
                player.sendMessage("${ChatColor.GREEN}This is an example button.")
                CompatibleSound.NOTE_PIANO.play(player)
            }

            override fun isInteractable(): Boolean {
                return false
            }

        }
        return buttons;
    }


}
```

3. Customize decoration using `DecorationType`:

```kotlin
init {
    this.decorationType = DecorationType.BORDER
    this.decorationItem = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
}
```