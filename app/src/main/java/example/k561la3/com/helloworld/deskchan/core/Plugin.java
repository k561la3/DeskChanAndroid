package example.k561la3.com.helloworld.deskchan.core;

public interface Plugin {
	
	default boolean initialize(PluginProxy proxy) {
		return true;
	}
	
	default void unload() {
	}
	
}
