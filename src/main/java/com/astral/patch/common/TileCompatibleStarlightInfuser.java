package com.astral.patch.common;


import com.astral.patch.handler.AllSidedInvWrapper;
import hellfirepvp.astralsorcery.common.tile.TileStarlightInfuser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;

public class TileCompatibleStarlightInfuser extends TileStarlightInfuser implements IInventory {
    private IItemHandler itemHandler;

    protected IItemHandler createUnSidedHandler()
    {
        return new AllSidedInvWrapper(this) {
            @Override
            public boolean canInsertItem(int index) {
                return index == 0;
            }

            @Override
            public boolean canExtractItem(int index) {
                return index == 0;
            }
        };
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return getInputStack().isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return getInputStack();
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return removeStackFromSlot(i);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        if(i != 0 || isEmpty())  return ItemStack.EMPTY;
//        Minecraft.getMinecraft().player.sendChatMessage("=============== before removeStackFromSlot");
        ItemStack oldstack = getInputStack().copy();
//        Minecraft.getMinecraft().player.sendChatMessage("remove " + oldstack.getCount());
        setStack(ItemStack.EMPTY);
        updateState();
//        Minecraft.getMinecraft().player.sendChatMessage("remain " + getInputStack().getCount());
//        Minecraft.getMinecraft().player.sendChatMessage("=============== after removeStackFromSlot ");
        return oldstack;
    }

    public void updateState(){
        BlockPos abovePos = getPos();
        abovePos = new BlockPos(abovePos.getX(), abovePos.getY() + 1, abovePos.getZ());
//        BlockState aboveState = world.getBlockState(abovePos);
        // 获取上方的方块状态
        IBlockState aboveState = world.getBlockState(abovePos);

        // 通知更新，包括重渲染和邻近方块的更新
        world.setBlockState(abovePos, aboveState);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        if (!isEmpty() || itemStack.getCount() > 1) return;
        setStack(itemStack.copy());
        updateState();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        if(i == 0)  return isEmpty();
        return false;
    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void setField(int i, int i1) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        setStack(ItemStack.EMPTY);
        updateState();
    }

    @Override
    public void update() {
        super.func_73660_a();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) (itemHandler == null ? (itemHandler = createUnSidedHandler()) : itemHandler);
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        return capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }
}
