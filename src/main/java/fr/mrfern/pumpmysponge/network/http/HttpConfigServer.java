package fr.mrfern.pumpmysponge.network.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.config.Config;

public class HttpConfigServer {

	static private HttpConfigServer httpConfigServer = new HttpConfigServer();
	static private int port;
	private static Main main;
	private static HttpServer httpS;
	
	public HttpConfigServer AddMain(Main main) {
		HttpConfigServer.main = main;		
		return httpConfigServer;
	}
	
	static public HttpConfigServer builder() {
		
		boolean enable = Config.getInstance().getEnableHttpServer();
		
		if(!enable) {
			
			main.getLogger().warn(" Le serveur HTTP n'a pas démaré , il n'a pas été activé dans config.json");
			
		}else {
			
			try {
				httpS = HttpServer.create(new InetSocketAddress(Config.getInstance().getPortHttpServer()), 0);
				main.getLogger().warn(" Le serveur HTTP a démaré avec succès");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return httpConfigServer;
	}
	
	static public HttpConfigServer builder(int port) {
		
		boolean enable = Config.getInstance().getEnableHttpServer();
		
		/*if(!enable) {
			
			main.getLogger().warn(" Le serveur HTTP n'a pas démaré , il n'a pas été activé dans config.json");
			
		}else {*/
			
			Config.getInstance().setPortHttpServer(port);
			Config.getInstance().save();
			
			try {
				httpS = HttpServer.create(new InetSocketAddress(port), 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		//}
		
		
		return httpConfigServer;
	}
	
	public HttpConfigServer addRoot(HttpHandler classHandler) {
		
		httpS.createContext("/", classHandler);
		
		return httpConfigServer;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		HttpConfigServer.port = port;
	}

	public static Main getMain() {
		return main;
	}

	public static HttpServer getHttpS() {
		return httpS;
	}

	public HttpConfigServer addContext(String url,HttpHandler echoHandler) {
		
		httpS.createContext(url, echoHandler);		
		return httpConfigServer;
	}
	
	public void start() {
		httpS.start();
	}

	public HttpConfigServer setExecutor(Executor object) {
		httpS.setExecutor(object);	
		return httpConfigServer;
	}
}
