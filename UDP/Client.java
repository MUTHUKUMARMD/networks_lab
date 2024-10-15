package Networks_Lab.UDP;

import java.io.*;
import java.net.*;
import java.util.*;

class Client {
    public static void main(String[] args) {
        // Set up the DatagramSocket
        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.println("Connected to the server.");

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the file name you want from the server:");
            String requestedFileName = sc.nextLine();

            // Send the file request to the server
            byte[] sendData = requestedFileName.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 5000);
            socket.send(sendPacket);

            // Receive the server's response
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if ("File found".equals(serverResponse)) {
                System.out.println("File found on server. Enter the name to save as:");
                String saveAsFilename = sc.nextLine();

                // Prepare to receive the file
                FileOutputStream fos = new FileOutputStream(saveAsFilename);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                // Receiving the file data in chunks
                boolean receiving = true;
                while (receiving) {
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    int bytesRead = receivePacket.getLength();

                    if (bytesRead == 0) {
                        // End of file transfer
                        receiving = false;
                    } else {
                        bos.write(receivePacket.getData(), 0, bytesRead);
                    }
                }

                bos.close();
                System.out.println("File received and saved as: " + saveAsFilename);
            } else {
                System.out.println("The requested file was not found on the server.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
