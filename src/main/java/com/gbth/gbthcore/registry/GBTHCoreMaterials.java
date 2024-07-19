package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.gtceu.material.BloomProperty;
import com.gbth.gbthcore.gtceu.material.PropertyKeys;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.resources.ResourceLocation;

public class GBTHCoreMaterials {

	public static final Material ZANITE = new Material.Builder(new ResourceLocation("gtceu", "zanite"))
			.gem(2)
			.ore(2, 3)
			.color(0x8e51e9)
			.iconSet(MaterialIconSet.GEM_VERTICAL)
			.flags(MaterialFlags.GENERATE_PLATE)
			.buildAndRegister();

	public static void register() {
		GTMaterials.Bronze.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.Tin.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.Copper.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.WroughtIron.setProperty(PropertyKeys.BLOOM, new BloomProperty());
	}
}
