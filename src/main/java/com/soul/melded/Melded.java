package com.soul.melded;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("melded")
public class Melded
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "melded";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> AURINITE_RAW = ITEMS.register("aurinite_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AURINITE = ITEMS.register("aurinite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RENDIUM_RAW = ITEMS.register("rendium_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RENDIUM = ITEMS.register("rendium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHRONIUM_RAW = ITEMS.register("chronium_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHRONIUM = ITEMS.register("chronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PYROTIN_RAW = ITEMS.register("pyrotin_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PYROTIN = ITEMS.register("pyrotin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AERILIUM_RAW = ITEMS.register("aerilium_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AERILIUM = ITEMS.register("aerilium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TERRACOR_RAW = ITEMS.register("terracor_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TERRACOR = ITEMS.register("terracor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNORIUM_RAW = ITEMS.register("magnorium_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGNORIUM = ITEMS.register("magnorium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AQUIUM_RAW = ITEMS.register("aquium_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUIUM = ITEMS.register("aquium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIMITE_RAW = ITEMS.register("lithimite_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LITHIMITE = ITEMS.register("lithimite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLARIEN_RAW = ITEMS.register("solarien_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOLARIEN = ITEMS.register("solarien", () -> new Item(new Item.Properties()));


    public static final RegistryObject<CreativeModeTab> MELDED_TAB = CREATIVE_MODE_TABS.register("melded_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.melded_tab"))
            .icon(() -> new ItemStack(AURINITE.get()))
            .displayItems((params, output) -> {
                output.accept(AURINITE.get());
                output.accept(AURINITE_RAW.get());
                output.accept(RENDIUM.get());
                output.accept(RENDIUM_RAW.get());
                output.accept(CHRONIUM.get());
                output.accept(CHRONIUM_RAW.get());
                output.accept(PYROTIN.get());
                output.accept(PYROTIN_RAW.get());
                output.accept(AERILIUM.get());
                output.accept(AERILIUM_RAW.get());
                output.accept(TERRACOR.get());
                output.accept(TERRACOR_RAW.get());
                output.accept(MAGNORIUM.get());
                output.accept(MAGNORIUM_RAW.get());
                output.accept(AQUIUM.get());
                output.accept(AQUIUM_RAW.get());
                output.accept(LITHIMITE.get());
                output.accept(LITHIMITE_RAW.get());
                output.accept(SOLARIEN.get());
                output.accept(SOLARIEN_RAW.get());
            })
            .build()
    );

    public Melded()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("Successfully Melded into server!");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("Melded Client");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
