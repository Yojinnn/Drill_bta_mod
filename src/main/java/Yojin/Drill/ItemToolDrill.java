package Yojin.Drill;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemTool;

import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ItemToolDrill extends ItemTool {

    public static Map<Block<?>, Integer> miningLevels = new HashMap();
    private static final int DAMAGE_PER_COAL = 5;

    public ItemToolDrill(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
        super(name, namespaceId, id, 2, enumtoolmaterial, BlockTags.MINEABLE_BY_PICKAXE);
        this.setMaxStackSize(1);
        this.setMaxDamage(192);

    }
    public void consumeCoal(@NotNull ItemStack itemstack, @Nullable Player player) {
        if (itemstack.getMetadata() >= itemstack.getMaxDamage()) {
            itemstack.setMetadata(this.getMaxDamage());
        } else {
            itemstack.damageItem(1, player);
        }

    }

    public boolean hasInventoryInteraction() {
        return true;
    }

    public ItemStack onInventoryInteract(Player player, Slot slot, ItemStack stackInSlot, boolean isItemGrabbed) {
        ItemStack yDrill;
        if (isItemGrabbed) {
            yDrill = player.inventory.getHeldItemStack();
        } else {
            yDrill = stackInSlot;
        }
        int totalSpace = this.getMaxDamageForStack(stackInSlot);
        int arrowCount = this.getArrowCount(yDrill);
        int freeSpace = totalSpace - arrowCount;
        if (isItemGrabbed) {
            if (stackInSlot == null) {
                int amount = Math.min(64, arrowCount);
                if (amount > 0) {
                    ItemStack arrowStack = new ItemStack(Items.NETHERCOAL, amount, 0);
                    if (slot.mayPlace(arrowStack)) {
                        this.setArrowCount(yDrill, arrowCount - amount);
                        stackInSlot = arrowStack;
                    }
                }
            } else if (stackInSlot != null && stackInSlot.itemID == Items.NETHERCOAL.id) {
                int amount = Math.min(freeSpace, stackInSlot.stackSize);
                if (amount > 0) {
                    this.setArrowCount(yDrill, arrowCount + amount);
                    stackInSlot.stackSize -= amount;
                }
            }
        } else {
            ItemStack grabbedItem = player.inventory.getHeldItemStack();
            if (grabbedItem != null && grabbedItem.itemID == Items.NETHERCOAL.id) {
                int amount = Math.min(grabbedItem.stackSize, freeSpace);
                if (amount > 0) {
                    grabbedItem.stackSize -= amount;
                    this.setArrowCount(yDrill, arrowCount + amount);
                    if (grabbedItem.stackSize <= 0) {
                        player.inventory.setHeldItemStack((ItemStack)null);
                    }
                }
            } else if (grabbedItem == null) {
                int amount = Math.min(64, arrowCount);
                if (amount > 0) {
                    this.setArrowCount(yDrill, arrowCount - amount);
                    player.inventory.setHeldItemStack(new ItemStack(Items.NETHERCOAL, amount, 0));
                }
            }
        }

        return stackInSlot;
    }

    private int getArrowCount(ItemStack stack) {
        return stack.getMaxDamage() - stack.getMetadata();
    }

    private void setArrowCount(ItemStack stack, int count) {
        stack.setMetadata(stack.getMaxDamage() - count);
    }

    public boolean showFullDurability() {
        return false;
    }





    public boolean canHarvestBlock(Mob mob, ItemStack itemStack, Block<?> block) {
        Integer miningLevel = (Integer)miningLevels.get(block);
        if (miningLevel != null) {
            return this.material.getMiningLevel() >= miningLevel;
        } else {
            return block.hasTag(BlockTags.MINEABLE_BY_PICKAXE);
        }
    }

    static {
        miningLevels.put(Blocks.OBSIDIAN, 3);
        miningLevels.put(Blocks.BLOCK_DIAMOND, 2);
        miningLevels.put(Blocks.ORE_DIAMOND_STONE, 2);
        miningLevels.put(Blocks.ORE_DIAMOND_BASALT, 2);
        miningLevels.put(Blocks.ORE_DIAMOND_GRANITE, 2);
        miningLevels.put(Blocks.ORE_DIAMOND_LIMESTONE, 2);
        miningLevels.put(Blocks.BLOCK_GOLD, 2);
        miningLevels.put(Blocks.ORE_GOLD_STONE, 2);
        miningLevels.put(Blocks.ORE_GOLD_BASALT, 2);
        miningLevels.put(Blocks.ORE_GOLD_GRANITE, 2);
        miningLevels.put(Blocks.ORE_GOLD_LIMESTONE, 2);
        miningLevels.put(Blocks.BLOCK_IRON, 1);
        miningLevels.put(Blocks.ORE_IRON_STONE, 1);
        miningLevels.put(Blocks.ORE_IRON_BASALT, 1);
        miningLevels.put(Blocks.ORE_IRON_GRANITE, 1);
        miningLevels.put(Blocks.ORE_IRON_LIMESTONE, 1);
        miningLevels.put(Blocks.BLOCK_STEEL, 2);
        miningLevels.put(Blocks.ORE_NETHERCOAL_NETHERRACK, 2);
        miningLevels.put(Blocks.BLOCK_LAPIS, 1);
        miningLevels.put(Blocks.ORE_LAPIS_STONE, 1);
        miningLevels.put(Blocks.ORE_LAPIS_BASALT, 1);
        miningLevels.put(Blocks.ORE_LAPIS_GRANITE, 1);
        miningLevels.put(Blocks.ORE_LAPIS_LIMESTONE, 1);
        miningLevels.put(Blocks.BLOCK_REDSTONE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_STONE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_BASALT, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_GRANITE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_LIMESTONE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_GLOWING_STONE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_GLOWING_BASALT, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_GLOWING_GRANITE, 2);
        miningLevels.put(Blocks.ORE_REDSTONE_GLOWING_LIMESTONE, 2);

    }
    @Override
    public boolean onBlockDestroyed(World world, ItemStack itemstack, int i, int x, int y, int z, Side side, Mob mob)

    {
        if (itemstack.getMetadata() >= itemstack.getMaxDamage()) {
            itemstack.setMetadata(this.getMaxDamage());
        } else {
            itemstack.damageItem(1, mob);
        }
        return super.onBlockDestroyed(world, itemstack, i, x, y, z, side, mob);


    }



}
