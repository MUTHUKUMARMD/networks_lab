package Networks_Lab.UDP;

import java.io.*;
import java.net.*;

class Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(5000)) {
            System.out.println("Server is listening on port 5000...");
// Buffer to receive data from the client
            byte[] receiveBuffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                    receiveBuffer.length);
// Receive file request from the client
            serverSocket.receive(receivePacket);
            String requestedFileName = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            System.out.println("Client requested file: " + requestedFileName);
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
// Look for the file
            File file = new File(requestedFileName);
            if (file.exists() && !file.isDirectory()) {
// Notify the client that the file was found
                String fileFoundMessage = "File found";
                byte[] fileFoundMessageBytes = fileFoundMessage.getBytes();
                DatagramPacket foundPacket = new
                        DatagramPacket(fileFoundMessageBytes, fileFoundMessageBytes.length,
                        clientAddress, clientPort);
                serverSocket.send(foundPacket);
// Send the file data in chunks
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                byte[] fileBuffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(fileBuffer)) != -1) {
                    DatagramPacket filePacket = new DatagramPacket(fileBuffer, bytesRead,
                            clientAddress, clientPort);
                    serverSocket.send(filePacket);
                }
                bis.close();
                System.out.println("File sent successfully from server.");
            } else {
// Notify the client that the file was not found
                String fileNotFoundMessage = "File not found";
                byte[] fileNotFoundMessageBytes = fileNotFoundMessage.getBytes();
                DatagramPacket notFoundPacket = new
                        DatagramPacket(fileNotFoundMessageBytes, fileNotFoundMessageBytes.length,
                        clientAddress, clientPort);
                serverSocket.send(notFoundPacket);
                System.out.println("File not found on server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}