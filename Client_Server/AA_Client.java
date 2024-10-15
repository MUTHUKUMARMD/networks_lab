//package Networks_Lab.Client_Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class AA_Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",8000);
        System.out.println("Server Connected...");
        Scanner in = new Scanner(System.in);

        Scanner input = new Scanner(s.getInputStream());
        PrintStream out = new PrintStream(s.getOutputStream());
        while (true){
            System.out.print("Client: " );
            String text = in.nextLine();
            out.println(text);
            if (text.equals("-1")) break;

            out.println(text);

            if (input.hasNextLine()){
                String server_response = input.nextLine();
                System.out.println("Server Response: "+ server_response );
            }

        }
    }
}
