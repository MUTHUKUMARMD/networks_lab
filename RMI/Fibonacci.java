import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Fibonacci extends Remote {
    
    int computeFibonacci(int n) throws RemoteException;
}
