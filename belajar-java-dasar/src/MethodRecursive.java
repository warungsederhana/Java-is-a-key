public class MethodRecursive {
    public static void main(String[] args) {
        System.out.println("Ini faktorial recursive: " + factorial(5));
        System.out.println("Ini faktorial loop: " + factorialLoop(5));
    }

    static int factorial(int n){
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    static int factorialLoop(int n) {
        int result = 1;
        for (int i = n; i > 0; i--) {
            result *= i;
        }
        return result;
    }
}
