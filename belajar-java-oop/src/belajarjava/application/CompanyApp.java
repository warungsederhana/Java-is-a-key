package belajarjava.application;

import belajarjava.data.Company;

public class CompanyApp {
    public static void main(String[] args) {
        Company company = new Company();
        company.setName("PT. Sejahtera");

        Company.Employee employee = company.new Employee();
        employee.setName("Eko");

        Company company2 = new Company();
        company2.setName("PT. Sentosa");

        Company.Employee employee2 = company2.new Employee();
        employee2.setName("Budi");

        System.out.println(employee.getCompany());
        System.out.println(employee2.getCompany());
    }
}
