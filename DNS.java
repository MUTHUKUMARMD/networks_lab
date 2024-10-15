//package Networks_Lab.DNS;

import java.io.*;
import java.net.*;

public class DNS {
    @SuppressWarnings("deprecation")
    public static void main(String[] args)throws Exception {
        InetAddress dns=InetAddress.getLocalHost();

                System.out.println("DNS HostName: "+dns.getHostName());

                System.out.println("DNS Server IP: "+dns.getHostAddress());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a hostname to resolve: ");
        String hostname = reader.readLine();
        InetAddress host_name = InetAddress.getByName(hostname);
        System.out.println(host_name.getHostAddress());
        System.out.println(host_name.getHostName());
        /*InetAddress[] host_name = InetAddress.getAllByName(hostname);
        for(InetAddress host : host_name){
            System.out.println(host.getHostAddress());
        }*/
       /* Process pr = Runtime.getRuntime().exec("nslookup "+hostname);
        BufferedReader r = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        while ((line = r.readLine())!=null){
            System.out.println(line.trim());
        }*/
    }
}
