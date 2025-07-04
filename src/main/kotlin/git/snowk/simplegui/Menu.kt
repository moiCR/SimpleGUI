package git.snowk.simplegui

import git.snowk.simplegui.button.Button
import git.snowk.simplegui.decoration.DecorationType
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitTask

abstract class Menu (val manager : MenuManager, val player : Player, val autoUpdate : Boolean = false) {

    var task : BukkitTask? = null
    var inventory : Inventory? = null
    var soundOnClick : Boolean = false
    var decorationType : DecorationType? = null
    var decorationItem : ItemStack? = null

    init {
        task = if (autoUpdate) Bukkit.getScheduler().runTaskTimerAsynchronously(manager.plugin, this::update, 0L, 100L) else null
    }

    fun open(){
        if (getTitle().length > 32){
            throw IllegalArgumentException("The title of the inventory cannot exceed 32 characters.")
        }

        if (getSize() > 54){
            throw IllegalArgumentException("The size of the inventory cannot exceed 54.")
        }

        this.inventory = Bukkit.createInventory(null, getSize(), getTitle())

        this.update();
        manager.register(player, this);
        player.openInventory(inventory!!);
    }

    fun update(){
        val buttons = getButtons();
        this.inventory!!.clear();

        decorationType?.decorate(this);

        buttons.forEach { (slot, button) -> {
            inventory!!.setItem(slot, button.getIcon())
        }}
    }

    fun clean(){
        getButtons().clear();
        this.inventory?.clear()
        this.task?.cancel()
        this.task = null
        this.manager.unregister(player)
    }

    fun onClose(){

    }

    abstract fun getTitle() : String
    abstract fun getSize() : Int
    abstract fun getButtons() : MutableMap<Int, Button>
}