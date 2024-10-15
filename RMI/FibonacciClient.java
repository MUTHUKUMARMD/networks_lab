import java.rmi.Naming;

public class FibonacciClient{
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the RMI registry
            Fibonacci fib = (Fibonacci) Naming.lookup("rmi://localhost:5000/FibonacciService");

            // Test the Fibonacci method
            int n = 10; // Compute the 10th Fibonacci number
            int result = fib.computeFibonacci(n);
            System.out.println("Fibonacci of " + n + " is: " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
