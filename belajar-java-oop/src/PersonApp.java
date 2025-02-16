public class PersonApp {
    public static void main(String[] args) {
        Person person = new Person("Eko", "Klataen");
        person.sayHello("Budi");

        var person2 = new Person("Joko");
        person2.address = "Klaten";
        person2.sayHello("Eko");

        Person person3;
        person3 = new Person();
        person3.name = "Budi";
        person3.address = "Klaten";
        person3.sayHello("Joko");

        System.out.println(person.name + " " + person.address + " " + person.country);
        System.out.println(person2.name + " " + person2.address + " " + person2.country);
        System.out.println(person3.name + " " + person3.address + " " + person3.country);
    }
}
