//package Networks_Lab;
import java.net.*;

class HostName {
    @SuppressWarnings("deprecation")
    public static void main(String[] args)throws Exception{
        InetAddress localhost = InetAddress.getLocalHost();
        String hostname = localhost.getHostName();
        String hostAddress = localhost.getHostAddress();
        System.out.println("HostName: "+hostname);
        System.out.println("Host Address: "+hostAddress);
        //URL:
        try{
        String url = "https://www.google.com";
        URL ur = new URL(url);
        String name = ur.getHost();

        InetAddress _name = InetAddress.getByName(name);
        String ip = _name.getHostAddress();

        System.out.println("URL: "+url);
        System.out.println("URL Hostname: " + name);
        System.out.println("URL IP Address: " + ip);}
        catch(MalformedURLException | UnknownHostException e){
            System.out.print("Exception");
        }
        
    }
}
