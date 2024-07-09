package edu.escuelaing.arsw.ASE.app.Websockets;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

/**
 * WebSocket endpoint for the 'bbService' WebSocket service.
 * This endpoint handles communication with WebSocket clients,
 * managing client connections, message reception, and broadcasting messages.
 */
@Component
@ServerEndpoint("/bbService")
public class BBEndpoint {
    private static final Logger logger = Logger.getLogger(BBEndpoint.class.getName());

    // Queue to store all open WebSocket sessions
    private static final Queue<Session> sessionQueue = new ConcurrentLinkedQueue<>();

    // The session of the current instance
    private Session ownSession = null;

    /**
     * Sends a message to all connected WebSocket clients except the sender.
     * @param message The message to send.
     */
    public void send(String message) {
        try {
            for (Session session : sessionQueue) {
                if (!session.equals(this.ownSession)) {
                    session.getBasicRemote().sendText(message);
                }
                logger.log(Level.INFO, "Sent message: {0}", message);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error sending message: {0}", e.getMessage());
        }
    }

    /**
     * Handles incoming messages from WebSocket clients.
     * @param message The received message.
     * @param session The session originating the message.
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.log(Level.INFO, "Received message '{0}' from session {1}", new Object[]{message, session});
        this.send(message); // Broadcast the received message to all clients
    }

    /**
     * Handles the opening of a new WebSocket connection.
     * @param session The new session.
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionQueue.add(session); // Register the new session
        ownSession = session;
        logger.log(Level.INFO, "New connection opened.");
        try {
            session.getBasicRemote().sendText("Connection established.");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error establishing connection: {0}", ex.getMessage());
        }
    }

    /**
     * Handles the closure of a WebSocket connection.
     * @param session The session being closed.
     */
    @OnClose
    public void onClose(Session session) {
        sessionQueue.remove(session); // Remove the closed session from the queue
        logger.log(Level.INFO, "Connection closed.");
    }

    /**
     * Handles errors in WebSocket communication.
     * @param session The session where the error occurred.
     * @param throwable The encountered error.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        sessionQueue.remove(session); // Remove the session with the error
        logger.log(Level.SEVERE, "WebSocket error: {0}", throwable.getMessage());
    }
}
