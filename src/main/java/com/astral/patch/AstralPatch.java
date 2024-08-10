package com.astral.patch;

import com.astral.patch.common.TileCompatibleStarlightInfuser;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = AstralPatch.MOD_ID)
public class AstralPatch {
    public static final String MOD_ID = "examplemod";


    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(TileCompatibleStarlightInfuser.class, MOD_ID + ":tile_entity_compatible_starlight_infuser");
    }
}
