package tokyo.ini.exitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static org.bukkit.Bukkit.getLogger;

public class EventClass implements Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if(e.getAction().equals(Action.PHYSICAL)) return;
        Player player = e.getPlayer();
        ItemStack item = null;

        if(e.getHand().equals(EquipmentSlot.HAND)){
            item = player.getInventory().getItemInMainHand();
        }else{
            item = player.getInventory().getItemInOffHand();
        }
        if(item != null && item.getType() != null && item.getType() != Material.AIR && item.hasItemMeta() && item.getType() == Material.BOOK){
            ItemMeta im = item.getItemMeta();
            String cm = im.getLore().get(2);
            getLogger().info(player.getDisplayName() + " issued book command: " + cm);
            cm = cm.replace("/","");
            player.performCommand(cm);
        }
    }
}