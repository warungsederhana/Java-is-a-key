package belajarjava.data;

public class Country {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class City {
        private String name;

//        ERROR : Cannot access Country.name because City is a static class
//        public String getCountry() {
//            return Country.this.name;
//        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
