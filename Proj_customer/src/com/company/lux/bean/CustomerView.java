package com.company.lux.bean;

import com.company.lux.service.CustomerList;
import com.company.lux.util.CMUtility;


public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView() {
        Customer customer = new Customer("tao", 'm', 22, "+1-1564532", "lin.lux@northeastern.edu");
        customerList.addCustomer(customer);
    }

    public void enterMainMenu() {

        boolean isFlag = true;
        while (isFlag) {
            System.out.println("\n--------customers' information management software-----\n");
            System.out.println("       1. add new customer");
            System.out.println("       2. revise the customer");
            System.out.println("       3. delete the customer");
            System.out.println("       4. show the lists of customers");
            System.out.println("       5.exit");
            System.out.println("       please choose(1-5):");

            char key = CMUtility.readMenuSelection();
            System.out.println();
            switch (key) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.println("exit");
                    System.out.println("confirm the exit(Y/N)");

                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isFlag = false;
                    }
            }


        }
    }

    private void addNewCustomer() {
        System.out.println("add the customer implement");
        System.out.println("name:");
        String name = CMUtility.readString(10);
        System.out.println("gender:");
        char gender = CMUtility.readChar();
        System.out.println("age:");
        int age = CMUtility.readInt();
        System.out.println("phone:");
        String phone = CMUtility.readString(13);
        System.out.println("email:");
        String email = CMUtility.readString(30);

        Customer customer = new Customer(name, gender, age, phone, email);

        boolean isSuccess = customerList.addCustomer(customer);
        if (isSuccess) {
            System.out.println("----finish the add process----");
        } else {
            System.out.println("---the customerList is full, add fail");
        }
    }

    private void modifyCustomer() {
        System.out.println("modify the customer info");
        Customer cust;
        int number;
        for (; ; ) {
            System.out.println("please choose the customer needed to change(-1 exit)");
            number = CMUtility.readInt();

            if (number == -1) {
                return;
            }

            cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("cannot find the specified customer");
            } else {
                break;
            }
        }
        // modify the customer info

        System.out.println("name(" + cust.getName() + "):");
        String name = CMUtility.readString(10, cust.getName());
        System.out.println("gender(" + cust.getGender() + "):");
        char gender = CMUtility.readChar();
        System.out.println("age(" + cust.getAge() + "):");
        int age = CMUtility.readInt(cust.getAge());
        System.out.println("phone(" + cust.getPhone() + ");");
        String phone = CMUtility.readString(13, cust.getPhone());
        System.out.println("email(" + cust.getEmail() + ");");
        String email = CMUtility.readString(30, cust.getEmail());

        Customer newCust = new Customer(name, gender, age, phone, email);

        boolean isReplaced = customerList.replaceCustomer(number - 1, newCust);

        if (isReplaced) {
            System.out.println("----finish the modification----");
        } else {
            System.out.println("----modification fail---");
        }

    }

    private void deleteCustomer() {
        System.out.println("---delete the customer---");
        int number;
        for (; ; ) {
            System.out.println("please choose the number needed to delete(-1 exit)");
            number = CMUtility.readInt();

            if (number == -1) {
                return;
            }

            Customer customer = customerList.getCustomer(number - 1);
            if (customer == null) {
                System.out.println("cannot find the specified customer!");
            } else {
                break;
            }
        }
        System.out.println("confirm the delete");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y') {

            boolean deleteSuccess = customerList.deleteCustomer(number - 1);

            if (deleteSuccess) {
                System.out.println("---finish the delete---");

            } else {
                System.out.println("---fail the delete--");
            }
        } else {
            return;
        }
    }


    private void listAllCustomers() {
        System.out.println("customerlists\n");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("no record!");
        } else {
            System.out.println("no\tname\tgender\tage\tphone\temail\t");
            Customer[] custs = customerList.getAllCustomers();
            for (int i = 0; i < custs.length; i++) {
                Customer cust = custs[i];
                System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() +
                        "\t" + cust.getAge() + "\t" + cust.getPhone() + "\t" + cust.getEmail());
            }
        }
        System.out.println("---finish the customerslist");


    }

    public static void main(String[] args) {
        // write your code here
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}