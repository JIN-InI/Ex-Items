package tokyo.ini.exitems;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CommandClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return false;
        }
        if (! args[1].startsWith("/")) {
            sender.sendMessage("Error:コマンド部分に/を入れてください");
            return true;
        }
        //アイテムネーム設定
        String itemname = args[0];
        //ロールの設定
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("Command:");
        StringJoiner sj = new StringJoiner(" ");
        int d=0;
        for(String i : args) {
            d++;
            if(d>1) {
                sj.add(i);
            }
        }
        lores.add(sj.toString());
        //インベントリの入手
        PlayerInventory inv = ((Player) sender).getInventory();
        //アイテムの生成と設定
        ItemStack giveitem = new ItemStack(Material.BOOK);
        ItemMeta im = giveitem.getItemMeta();
        im.setDisplayName(itemname);
        im.setLore(lores);
        giveitem.setItemMeta(im);
        //アイテムの付与
        inv.addItem(giveitem);
        return true;
    }
}
