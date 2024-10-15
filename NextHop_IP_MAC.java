//package Networks_Lab;
import java.io.*;
public class NextHop_IP_MAC {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception{
        //1. NextHop IP:
        Process p1 = Runtime.getRuntime().exec("ipconfig");
        InputStreamReader read = new InputStreamReader(p1.getInputStream());
        BufferedReader in = new BufferedReader(read);
        String line;
        String ip="",mac="";
        while((line= in.readLine()) != null){
            String[] IP = line.trim().split("\\.");
            if(IP.length==4){ //.contains("Default Gateway")
                ip = line.trim();
                break;
            }
        }
        //2. NextHop - MAC:
        Process p2 = Runtime.getRuntime().exec("arp -a");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        while((line = in2.readLine())!=null){
            if(line.contains(ip)){
                String[] MAC = line.trim().split("\\s+");
                if(MAC.length == 3){
                    mac = MAC[1];
                    break;
                }
            }
        }
        System.out.println("NextHop IP: "+ip);
        System.out.println("NextHop MAC: "+mac);
    }
}
