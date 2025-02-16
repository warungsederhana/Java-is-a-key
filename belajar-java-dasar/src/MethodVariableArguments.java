public class MethodVariableArguments {
    public static void main(String[] args) {
        var name = "Budi";
        int[] values = {80, 90, 100, 50, 30};

        sayCongrats(name, values);
        sayCongrats2(name, 80, 90, 100, 50, 30);

    }

//    tanpa menggunakan varargs
    static void sayCongrats(String name, int[] values) {
        int total = 0;

        for (int val: values) {
            total += val;
        }
        var finalValue = total / values.length;

        if (finalValue > 75) {
            System.out.println("Congrats " + name + ", you passed the exam with a score of " + finalValue);
        } else {
            System.out.println("Sorry " + name + ", you failed the exam with a score of " + finalValue);
        }
    }

//    menggunakan varargs
    static void sayCongrats2(String name, int... values) {
        int total = 0;

        for (int val: values) {
            total += val;
        }
        var finalValue = total / values.length;

        if (finalValue > 75) {
            System.out.println("Congrats " + name + ", you passed the exam with a score of " + finalValue);
        } else {
            System.out.println("Sorry " + name + ", you failed the exam with a score of " + finalValue);
        }
    }
}
