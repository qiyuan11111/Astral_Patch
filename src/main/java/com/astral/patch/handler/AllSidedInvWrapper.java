package com.astral.patch.handler;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public abstract class AllSidedInvWrapper extends InvWrapper {

    public AllSidedInvWrapper(IInventory inv) {
        super(inv);
    }

    public abstract boolean canInsertItem(int index);

    public abstract boolean canExtractItem(int index);

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if(!canInsertItem(slot))
            return stack;
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if(!canExtractItem(slot))
            return ItemStack.EMPTY;
        return super.extractItem(slot, amount, simulate);
    }
}
