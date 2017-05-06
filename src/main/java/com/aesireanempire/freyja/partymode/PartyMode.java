package com.aesireanempire.freyja.partymode;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by freyja
 */
@Mod(name = PartyMode.MODNAME, modid = PartyMode.MODID)
public class PartyMode {
    public static final String MODNAME = "partymode";
    public static final String MODID = "partymode";

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
