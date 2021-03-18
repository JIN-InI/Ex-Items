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

        if (command.getName().equals("exitem")) {
            switch (args[0]) {
                case "get":
                    if (args.length < 3) {
                        return false;
                    }
                    if (!args[2].startsWith("/")) {
                        sender.sendMessage(Errorcolor("コマンド部分に/を入れてください"));
                        return true;
                    }
                    //アイテムネーム設定
                    String itemname = args[1];
                    //ロールの設定
                    List<String> lores = new ArrayList<>();
                    lores.add("");
                    lores.add("Command:");
                    StringJoiner sj = new StringJoiner(" ");
                    int d = 0;
                    for (String i : args) {
                        d++;
                        if (d > 1) sj.add(i);
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
                    sender.sendMessage(HeadDeco("本を付与しました"));
                    return true;

                case "give":
                    if (args.length != 2) {
                        return false;
                    }
                    String name = args[1];
                    if (name.startsWith("*")) {
                        name = name.replace("*", "");
                    }
                    ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                    if (!item.getType().equals(Material.BOOK) && !item.hasItemMeta()) {
                        sender.sendMessage(Errorcolor("Ex-Itemを持ってください"));
                        return true;
                    }
                    if (sender.getServer().getPlayer(name) == null) {
                        sender.sendMessage(Errorcolor("プレイヤーが存在しません"));
                        return true;
                    }
                    Player dear = sender.getServer().getPlayer(name);
                    PlayerInventory drinv = dear.getInventory();
                    drinv.addItem(item);
                    sender.sendMessage(HeadDeco("本を付与しました"));
                    return true;

                default:
                    sender.sendMessage(Errorcolor("存在しないコマンドです"));
                    return true;
            }
        }
        return false;
    }

    public String Errorcolor(String string){
        String red = "§c[Error] ";
        return red + string;
    }

    public String HeadDeco(String string){
        String head = "§b[§6Ex§b-§6Items§b]§e ";
        return head + string;
    }
}
