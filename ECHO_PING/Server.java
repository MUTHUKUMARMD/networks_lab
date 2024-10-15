//package Networks_Lab.ECHO_PING;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
class Server {
    public static void main(String[] args) {
        final int PORT = 12345;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new
                             InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("Connected to client: " +
                            clientSocket.getInetAddress().getHostAddress());
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        String command = inputLine.split(" ")[0].toUpperCase();
                        String message = inputLine.length() > 5 ? inputLine.substring(5).trim() : "";
                        switch (command) {
                            case "ECHO":
                                out.println(message);
                                break;
                            case "PING":
                                String targetHost = clientSocket.getInetAddress().getHostAddress();
                                InetAddress inetAddress = InetAddress.getByName(targetHost);
                                boolean isReachable = inetAddress.isReachable(2000);
                                if (isReachable) {
                                    out.println("Host is reachable");
                                } else {
                                    out.println("Host is not reachable");
                                }
                                break;
                            case "TIME":
                                out.println(LocalDateTime.now().toString());
                                break;
                            default:
                                out.println("Unknown command");
                                break;
                        }
                    }
                    System.out.println("Client disconnected.");
                } catch (IOException e) {
                    System.out.println("Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
