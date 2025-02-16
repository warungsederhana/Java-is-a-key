class Parent {
    String name;
    void doIt() {
        System.out.println("Do it from Parent");
    }
}

class Child extends Parent { ;
    String name;
    void doIt() {
        System.out.println("Do it from Child");
        System.out.println("Parent name: " + super.name);
    }
}
