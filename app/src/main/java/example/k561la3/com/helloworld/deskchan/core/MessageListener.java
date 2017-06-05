package example.k561la3.com.helloworld.deskchan.core;

public interface MessageListener {
	
	void handleMessage(String sender, String tag, Object data);
	
}
