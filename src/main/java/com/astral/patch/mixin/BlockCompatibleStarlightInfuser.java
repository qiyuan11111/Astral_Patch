package com.astral.patch.mixin;

import com.astral.patch.common.TileCompatibleStarlightInfuser;
import hellfirepvp.astralsorcery.common.block.BlockStarlightInfuser;
import hellfirepvp.astralsorcery.common.block.network.BlockStarlightNetwork;
import hellfirepvp.astralsorcery.common.structure.BlockStructureObserver;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockStarlightInfuser.class)
public abstract class BlockCompatibleStarlightInfuser extends BlockStarlightNetwork implements BlockStructureObserver {
    public BlockCompatibleStarlightInfuser(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    /**
     * @author
     * @reason
     */

    @Overwrite(remap = false)
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileCompatibleStarlightInfuser();
    }

//    IMixinConfigPlugin
}
