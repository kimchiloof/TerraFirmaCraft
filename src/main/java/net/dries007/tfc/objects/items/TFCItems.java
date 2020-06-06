/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.util.Util;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.dries007.tfc.api.Metal;
import net.dries007.tfc.api.RockCategory;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public final class TFCItems
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    public static final Map<Metal.Default, Map<Metal.ItemType, RegistryObject<Item>>> METAL_ITEMS = Util.make(new EnumMap<>(Metal.Default.class), map -> {
        for (Metal.Default metal : Metal.Default.values())
        {
            Map<Metal.ItemType, RegistryObject<Item>> inner = new EnumMap<>(Metal.ItemType.class);
            for (Metal.ItemType type : Metal.ItemType.values())
            {
                if (type.hasType(metal))
                {
                    String name = ("metal/" + type.name() + "/" + metal.name()).toLowerCase();
                    RegistryObject<Item> item = ITEMS.register(name, () -> type.create(metal));
                    inner.put(type, item);
                }
            }
            map.put(metal, inner);
        }
    });

    public static final Map<RockCategory, Map<RockCategory.ItemType, RegistryObject<Item>>> ROCK_TOOLS = Util.make(new EnumMap<>(RockCategory.class), map -> {
        for (RockCategory rockCategory : RockCategory.values())
        {
            Map<RockCategory.ItemType, RegistryObject<Item>> inner = new EnumMap<>(RockCategory.ItemType.class);
            for (RockCategory.ItemType type : RockCategory.ItemType.values())
            {
                String name = ("stone/" + type.name() + "/" + rockCategory.name()).toLowerCase();
                RegistryObject<Item> item = ITEMS.register(name, () -> type.create(rockCategory));
                inner.put(type, item);
            }
            map.put(rockCategory, inner);
        }
    });
}
