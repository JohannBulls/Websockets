# Interactive Drawing Board with WebSocket Integration

Interactive Drawing Board is a web application that allows users to draw collaboratively in real-time using WebSocket communication. It combines React for frontend rendering and WebSocket technology for seamless, synchronized drawing across multiple clients.

## Features

- **Real-time Drawing**: Users can draw on a shared canvas, and changes are immediately propagated to all connected clients.
- **WebSocket Integration**: Utilizes WebSocket for bi-directional communication between the frontend and backend server.
- **Canvas Interaction**: Supports drawing with mouse interactions and sends coordinates to the server for synchronization.
- **Session Management**: Basic session management to handle multiple clients and maintain drawing consistency.

## Project Structure

### Backend (Spring Boot)

1. **BBAppStarter.java**:
   - Main class that initializes the Spring Boot application.
   - Starts the server on the default port configured in `application.properties`.

2. **BBConfigurator.java**:
   - Configures WebSocket endpoint (`/bbService`) using Spring's `ServerEndpointExporter`.

3. **BBEndpoint.java**:
   - WebSocket endpoint (`/bbService`) that manages WebSocket sessions and broadcasts drawing updates to clients.
   - Handles WebSocket lifecycle events (`onOpen`, `onMessage`, `onClose`, `onError`) and maintains a queue of active sessions.

4. **DrawingServiceController.java**:
   - REST controller that provides status information (`/status`) about the server's health and operation.

### Frontend (React)

1. **BBCanvas.jsx**:
   - React component that renders the drawing canvas using the P5.js library.
   - Handles mouse events for drawing and communicates with the WebSocket server for real-time updates.

2. **Editor.jsx**:
   - Main React component that integrates the canvas (`BBCanvas`) and manages the user interface.
   - Displays user information, status messages, and the drawing canvas.

## Usage

### Running the Application

To run the Interactive Drawing Board application, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/JohannBulls/Websockets
   cd Websockets
   ```

2. Start the backend (Spring Boot) server:
   - Ensure MongoDB is running and configured correctly in `application.properties`.
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
### Example Usage

- Open the application in multiple browser tabs or devices.
- Draw on one canvas, and observe real-time updates on other connected clients.
- Experiment with different drawing tools and interact with the collaborative drawing experience.

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests with improvements or additional features.

## Author

- Johann Amaya Lopez - [GitHub](https://github.com/JohannBulls)

## License

This project is licensed under the MIT License. See the LICENSE file for details.