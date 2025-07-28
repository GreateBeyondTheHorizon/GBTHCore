package com.gbth.gbthcore.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class LangHandler {
	public static void init(RegistrateLangProvider provider) {
		ConfigurationLang.register(provider);

		provider.add("gbthcore.bloomery", "Bloomery");
		provider.add("gbthcore.rock_blaster", "Rock Blaster");
		provider.add("tagprefix.bloom", "%s Bloom");
	}
}
