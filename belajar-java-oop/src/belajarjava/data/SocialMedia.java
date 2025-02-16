package belajarjava.data;

class SocialMedia {
    String name;

    final void login(String username, String password) {
        System.out.println("Login...");
    }
}

final class Facebook extends SocialMedia {
    String category;

//    ERROR
//    void login(String username, String password) {
//        System.out.println("Login Facebook...");
//    }
}

// ERROR
//class FakeFacebook extends Facebook {
//    String fakeCategory;
//}
