//package Networks_Lab;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class PortScanning{
    @SuppressWarnings({ "resource", "unused" })
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Range of port: ");
        System.out.print("Start: "); int a = in.nextInt();
        System.out.print("End: "); int b = in.nextInt();
        for(int i=a;i<=b;i++){
            try{
                Socket s = new Socket("localhost",i);
                System.out.println("Port " + i +"-- Available");
            }catch(IOException e){
                System.out.println("Port "+ i + "-- buzy");
                }
        }
    }
}