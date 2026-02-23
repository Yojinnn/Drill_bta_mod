package Yojin.Drill;

import static Yojin.Drill.ModMain.MOD_ID;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {
    //private static int startingID = ModConfig.CFG.getInt("IDs.startingBlockID");
    //private static int nextID() {return startingID++;}


    public static Item yDrill;


    public static ToolMaterial DrillMaterial = new ToolMaterial().setDurability(1).setEfficiency(50.0f, 90.0f).setMiningLevel(0).setDamage(3);
    public static void initItems() {
        yDrill = new ItemBuilder(MOD_ID)
                .build(new ItemToolDrill("item.yDrill", MOD_ID + ":item/yDrill", 17006, DrillMaterial));



    }
}
