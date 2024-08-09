package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.integration.gtceu.material.MaterialIconTypes;
import com.gbth.gbthcore.integration.gtceu.material.PropertyKeys;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import java.util.function.Predicate;

import static com.gbth.gbthcore.registry.GBTHCoreTagPrefixes.Conditions.hasBloomProperty;

public class GBTHCoreTagPrefixes {
	public static TagPrefix bloom;

	public static void register() {
		bloom = new TagPrefix("bloom")
				.langValue("%s Bloom")
				.defaultTagPath("blooms/%s")
				.unformattedTagPath("blooms")
				.materialAmount(GTValues.M)
				.materialIconType(MaterialIconTypes.bloom)
				.unificationEnabled(true)
				.generateItem(true)
				.generationCondition(hasBloomProperty);
	}

	public static class Conditions {
		public static final Predicate<Material> hasBloomProperty = mat -> mat.hasProperty(PropertyKeys.BLOOM);
	}
}
