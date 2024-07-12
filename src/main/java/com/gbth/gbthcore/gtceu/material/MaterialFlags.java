package com.gbth.gbthcore.gtceu.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class MaterialFlags {

	public static final MaterialFlag GENERATE_BLOOM = new MaterialFlag.Builder("generate_bloom")
			.requireProps(PropertyKeys.BLOOM)
			.build();
}
