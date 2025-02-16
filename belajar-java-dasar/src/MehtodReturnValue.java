public class MehtodReturnValue {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        String operasi = "-";
        int result = sum(a, b);
        int hasil = hitung(a, operasi, b);

        System.out.println(result);
        System.out.println(hasil);
    }

    static int sum(int val1, int val2) {
        return val1 + val2;
    }

    static int hitung(int val1, String operasi, int val2) {
        return switch (operasi) {
            case "+" -> val1 + val2;
            case "-" -> val1 - val2;
            default -> 0;
        };
    }
}
