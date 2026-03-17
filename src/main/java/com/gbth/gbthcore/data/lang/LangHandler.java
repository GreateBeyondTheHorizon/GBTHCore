package com.gbth.gbthcore.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class LangHandler {
	public static void init(RegistrateLangProvider provider) {
		ConfigurationLang.register(provider);

		provider.add("gbthcore.bloomery", "Bloomery");
		provider.add("gbthcore.rock_blaster", "Rock Blaster");
		provider.add("tagprefix.bloom", "%s Bloom");

		provider.add("task.gbthcore.fluid_task", "Acquire Fluid");
		provider.add("task.gbthcore.fluid.title", "Acquire: %s");
		provider.add("task.gbthcore.fluid.desc", "Acquire: %s %s");

		provider.add("setting.gbthcore.fluid_task.title", "Title Override:");
		provider.add("setting.gbthcore.fluid_task.icon", "Icon Override:");
		provider.add("setting.gbthcore.fluid_task.fluid", "Fluid:");
		provider.add("setting.gbthcore.fluid_task.amount", "Amount:");
	}
}
