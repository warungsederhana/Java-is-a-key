public class MethodParameter {
    public static void main(String[] args) {
        String firstName = "Budi";
        String lastName = "Susanto";
        sayHello(firstName, lastName);
    }

    static void sayHello(String firstName, String lastName) {
        System.out.println("Hello, " + firstName + " " + lastName);
    }
}
