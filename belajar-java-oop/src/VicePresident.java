class VicePresident extends Manager {

    VicePresident(String name) {
        super(name);
    }

    void sayHello(String name) {
        System.out.println("Hello " + name + ", my name is Vice President " + this.name);
    }
}
