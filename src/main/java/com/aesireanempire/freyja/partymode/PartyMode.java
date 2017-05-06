package com.aesireanempire.freyja.partymode;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Created by freyja
 */
@Mod(name = PartyMode.MODNAME, modid = PartyMode.MODID)
public class PartyMode {
    public static final String MODNAME = "PartyMode";
    public static final String MODID = "PartyMode";

    @Mod.Instance
    public static PartyMode instance;

    @SidedProxy(clientSide = "com.aesireanempire.freyja.partymode.ClientProxy", serverSide = "com.aesireanempire.freyja.partymode.CommonProxy")
    public static CommonProxy proxy;

    private static InviteRegistry inviteRegistry = new InviteRegistry();
    private static PartyRegistry partyRegistry = new PartyRegistry();

    public static InviteRegistry getInviteRegistry() {
        return inviteRegistry;
    }

    public static void setInviteRegistry(InviteRegistry inviteRegistry) {
        PartyMode.inviteRegistry = inviteRegistry;
    }

    public static PartyRegistry getPartyRegistry() {
        return partyRegistry;
    }

    public static void setPartyRegistry(PartyRegistry partyRegistry) {
        PartyMode.partyRegistry = partyRegistry;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //TODO LogHandler
        //TODO ConfigHandler

        FMLCommonHandler.instance().bus().register(new PlayerEvents(partyRegistry));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        //TODO GuiHandler
        //TODO TickHandlers
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new PartyCommand());
    }
}
