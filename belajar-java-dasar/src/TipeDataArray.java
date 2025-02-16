public class TipeDataArray {
    public static void main(String[] args) {
        String[] arrayString;
        arrayString = new String[3];
        arrayString[0] = "Budi";
        arrayString[1] = "Susanto";
        arrayString[2] = "Santoso";

        System.out.println(arrayString[0]);
        System.out.println(arrayString[1]);
        System.out.println(arrayString[2]);

        String[] arrayNama = new String[]{
                "Budi", "Susanto", "Santoso"
        };

        int[] arrayInt = {1, 2, 3};

        System.out.println(arrayString.length);

        String[][] members = {
                {"Budi", "Susanto", "Santoso"},
                {"Joko", "Susilo", "Widodo"},
                {"Bambang", "Santoso", "Santoso"}
        };

        System.out.println(members[0][1]);
    }
}
