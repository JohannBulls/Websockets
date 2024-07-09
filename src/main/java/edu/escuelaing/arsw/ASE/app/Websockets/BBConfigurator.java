package edu.escuelaing.arsw.ASE.app.Websockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Configuration class for WebSocket endpoints.
 */
@Configuration
@EnableScheduling
public class BBConfigurator {
    
    /**
     * Enables WebSocket endpoints in the Spring application.
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
