package fr.mrfern.pumpmysponge.network.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.mrfern.pumpmysponge.config.PlayerConfig;
import fr.mrfern.pumpmysponge.config.PlayerNode;

public class EchoGetPlyStatsHandler implements HttpHandler {

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
        
        switch(key) {
        
        	case "UUID":
        	
        		PlayerNode plyNode = PlayerConfig.getInstance().getPlayerConfigNode(UUID.fromString(value));
        		
        		if(plyNode == null) {
        			
            		response = "<h3> UUID inconnu, ou joueur inexistant  !</h3>";            		
            		
        		}else {
        			
            		response = key + " = " + value + "\n";
            		
        		}
        		
        		he.sendResponseHeaders(200, response.length());
        		os = he.getResponseBody();
        		os.write(response.toString().getBytes());
        		os.close();

        		break;
        	
        	case "name":
        	
        		// send response
        		response = key + " = " + value + "\n";
        		he.sendResponseHeaders(200, response.length());
        		os = he.getResponseBody();
        		os.write(response.toString().getBytes());
        		os.close();
        	
        		break;
        	
        	default:
        		
        		// send response
        		response = "204 (No Content) \n";
        		he.sendResponseHeaders(204, 0);
        		os = he.getResponseBody();
        		os.write(response.toString().getBytes());
        		os.close();
        		break;
        
        	
        
        }

        
    }

}
