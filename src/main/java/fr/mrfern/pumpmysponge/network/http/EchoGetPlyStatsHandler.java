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

        
    }

}
