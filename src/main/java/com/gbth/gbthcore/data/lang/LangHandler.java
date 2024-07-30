package com.gbth.gbthcore.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class LangHandler {
	public static void init(RegistrateLangProvider provider) {
		ConfigurationLang.init(provider);

		provider.add("gbthcore.bloomery", "Bloomery");
	}
}
