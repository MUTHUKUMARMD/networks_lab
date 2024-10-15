//package Networks_Lab.ECHO_PING;

import java.io.*;
import java.net.*;
class Client {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PORT = 12345;
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Connected to server at " + HOST + ":" + PORT);
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equalsIgnoreCase("EXIT")) {
                    break;
                }
                out.println(userInput);
                System.out.println("Server response: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O error with host: " + HOST);
            System.exit(1);
        }
    }
}