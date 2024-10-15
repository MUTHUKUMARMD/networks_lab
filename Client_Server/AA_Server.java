//package Networks_Lab.Client_Server;
import java.io.*;
import java.net.*;
import java.util.*;

public class AA_Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8000);
        System.out.println("Waiting for Client to get Connected!....");
        while(true){
            try (Socket client = server.accept()) {
                System.out.println("Client Connected!");
                Scanner in = new Scanner(client.getInputStream());
                PrintStream out = new PrintStream(client.getOutputStream());
                String line;
                while((line = in.nextLine())!=null){
                    System.out.println("Received from Client! " + line);
                    if (line.equals("-1")){
                        out.println("Client Closed the conn.");
                        break;
                    }
                    // String sdf = new SimpleDateFormat("HH:mm:hh").format(new Date());
                    String sdf=new Date().toString();
                    out.println("Server response timing @ " + sdf + ": "+line);
                }
            }
            System.out.println("Client Disconnected!");
        }
    }
}
