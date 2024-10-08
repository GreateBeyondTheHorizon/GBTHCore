package com.gbth.gbthcore.data.lang;

import com.gbth.gbthcore.GBTHConfig;
import com.gbth.gbthcore.GBTHCore;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationLang {

	public static void register(RegistrateLangProvider provider) {
		provider.add("config.screen.gbthcore", "GBTHCore Config");
		dfs(provider, new HashSet<>(),
				Configuration.registerConfig(GBTHConfig.class, ConfigFormats.yaml()).getValueMap());
	}

	private static void dfs(RegistrateLangProvider provider, Set<String> added, Map<String, ConfigValue<?>> map) {
		for (var entry : map.entrySet()) {
			var id = entry.getValue().getId();
			if (added.add(id)) {
				provider.add(String.format("config.%s.option.%s", GBTHCore.MODID, id), id);
			}
			if (entry.getValue() instanceof ObjectValue objectValue) {
				dfs(provider, added, objectValue.get());
			}
		}
	}
}
