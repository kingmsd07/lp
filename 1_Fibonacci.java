public class Fibonacci {

    // Iterative
    public static void printFibonacci(int terms) {
        int a = 0, b = 1;

        System.out.print("Fibonacci Series: ");

        for (int i = 1; i <= terms; i++) {
            System.out.print(a + " ");
            int nextTerm = a + b;
            a = b;
            b = nextTerm;
        }

        System.out.println();
    }

    // Recursive
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Method to print Fibonacci series up to n terms
    public static void printFibonacciSeries(int terms) {
        System.out.print("Fibonacci Series: ");
        for (int i = 0; i < terms; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("By iteration: ");
        printFibonacci(3);
        System.out.println("By recursion: ");
        printFibonacciSeries(3);
    }
}