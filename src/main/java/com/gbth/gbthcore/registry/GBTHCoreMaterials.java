package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.gtceu.material.BloomProperty;
import com.gbth.gbthcore.gtceu.material.PropertyKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;

public class GBTHCoreMaterials {

	public static void register() {
		GTMaterials.WroughtIron.setProperty(PropertyKeys.BLOOM, new BloomProperty());
	}
}
