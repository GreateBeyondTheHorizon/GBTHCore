package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.integration.gtceu.material.BloomProperty;
import com.gbth.gbthcore.integration.gtceu.material.PropertyKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;

public class GBTHCoreMaterials {

	public static void register() {
		GTMaterials.Bronze.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.Tin.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.Copper.setProperty(PropertyKeys.BLOOM, new BloomProperty());
		GTMaterials.WroughtIron.setProperty(PropertyKeys.BLOOM, new BloomProperty());
	}
}
