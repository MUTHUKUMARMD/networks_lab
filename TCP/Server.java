package Networks_Lab.TCP;

import java.io.*;
import java.net.*;

class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String requestedFileName = reader.readLine();
            File file = new File(requestedFileName);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            if (file.exists() && !file.isDirectory()) {

                writer.println("File found");


                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                OutputStream os = socket.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }

                bis.close();
                System.out.println("File sent successfully from server.");
            } else {

                writer.println("File not found");
                System.out.println("File not found on server.");
            }


            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
