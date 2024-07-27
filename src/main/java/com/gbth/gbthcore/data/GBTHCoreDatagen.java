package com.gbth.gbthcore.data;

import com.gbth.gbthcore.data.lang.LangHandler;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.tterrag.registrate.providers.ProviderType;

public class GBTHCoreDatagen {
	public static void init() {
		GBTHCoreRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
	}
}
