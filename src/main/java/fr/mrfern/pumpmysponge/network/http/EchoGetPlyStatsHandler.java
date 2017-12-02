package fr.mrfern.pumpmysponge.network.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Optional;
import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.config.PlayerConfig;

public class EchoGetPlyStatsHandler implements HttpHandler {

	private String pluginName = Main.getPluginName();
	protected String defaultPath = "mods/plugins/"+ pluginName + "/";
	protected String playerPath = defaultPath + "player/";
	private String extensionFile = ".json";
	
	@Override
    public void handle(HttpExchange he) throws IOException {
		
		// parse request
		
		String key = new String("");
		String value = new String("");
		
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        
        if (query != null) {
            String pairs[] = query.split("[&]");
            
            // récupères seulement le premier 
            String pair = pairs[0];
            
            String param[] = pair.split("[=]");
            
                     
            if (param.length > 0) {
            	key = URLDecoder.decode(param[0],System.getProperty("file.encoding"));
            }

            if (param.length > 1) {
                value = URLDecoder.decode(param[1],System.getProperty("file.encoding"));
            }
            
        }
        
        String response = "";
        OutputStream os;
        
        
        switch(key.toString()) {
        
    		case "uuid":
    	
    			UUID uid = UUID.fromString(value);            	
            	
    			File plyFile = PlayerConfig.getInstance().getPlayerConfigFile(uid);			
    		
    			if(plyFile != null) {
    				
    				response = parseFile(new FileReader(plyFile));
        		
    			}else {
    				response = "<h1> UUID inconnu, ou joueur inexistant  !</h1>";        
        		
    			}
    			
    			//response = " test ";
    		
    			he.sendResponseHeaders(200, response.length());
    			os = he.getResponseBody();
    			os.write(response.toString().getBytes());
    			os.close();

    			break;
    	
    		case "name":
    			
    			System.out.println("optionnal player");
    			Optional<Player> ply = Sponge.getServer().getPlayer(value);
    			
    			System.out.println("check ply present");
    			if(!ply.isPresent()) {
    				System.out.println("optionnal player non present");
    				response = "<h1> UUID inconnu, ou joueur inexistant  !</h1>";
    			}else {
    				System.out.println("optionnal player present");
    				UUID uuid = ply.get().getUniqueId();
    				System.out.println("optionnal player get UUID");
    				File plyNameFile = PlayerConfig.getInstance().getPlayerConfigFile(uuid);
    				System.out.println("optionnal player non present");
    	    		
        			if(plyNameFile != null) {
        				
        				response = parseFile(new FileReader(plyNameFile));
            		
        			}else {
        				response = "<h1> UUID inconnu, ou joueur inexistant  !</h1>";        
            		
        			}
    				
    			}
    			
    			
    		
    			he.sendResponseHeaders(200, response.length());
    			os = he.getResponseBody();
    			os.write(response.toString().getBytes());
    			os.close();
    	
    			break;
    			
        }

        
    }
	
	public String parseFile(FileReader reader) {
		
		String parsed = "";
	    BufferedReader br = null;
	    try {
	        String sCurrentLine;
	        br = new BufferedReader(reader);
	        while ((sCurrentLine = br.readLine()) != null) {
	            parsed += sCurrentLine;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null) br.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return parsed;
		
	}
	
	public File getPlayerConfigFile(String uuid) {
		System.out.println("check file exist");
		if(playerConfigExist(uuid)) {	// check si un fichier du nom de l'UUID existe
			System.out.println("file exist");
			System.out.println("return new file");
			return new File(playerPath + uuid + extensionFile);		// Return la file du nom de l'UUID
		}
		System.out.println("return null");
		return null;
	}
	
	public boolean playerConfigExist(String uuid) {
		System.out.println("file exist or not");
		File playerFile = new File(playerPath + uuid + extensionFile);	// Instancie new file
		System.out.println("file new");
		if(playerFile.exists()){	// check si file exist
			System.out.println("return true");
			return true;
		}
		System.out.println("return false");
		return false;
	}

}
