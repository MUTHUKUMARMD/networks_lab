import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FibonacciImpl extends UnicastRemoteObject implements Fibonacci {

    // Constructor to handle RemoteException
    protected FibonacciImpl() throws RemoteException {
        super();
    }

    // Method to compute Fibonacci recursively
    @Override
    public int computeFibonacci(int n) throws RemoteException {
        if (n <= 1) return n;
        return computeFibonacci(n - 1) + computeFibonacci(n - 2);
    }

    public static void main(String[] args) {
        try {
            // Create the server object and bind it to the RMI registry
            FibonacciImpl server = new FibonacciImpl();
            Naming.rebind("rmi://localhost:5000/FibonacciService", server);
            System.out.println("Fibonacci RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
