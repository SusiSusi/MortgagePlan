package Controller;

import Service.CustomerService;
import Utility.FileReader;
import Utility.MortgageFormula;

import java.util.List;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomers(String fileName) {
        FileReader fileReader = new FileReader();
        fileReader.read(fileName);
        List<String> fileData = fileReader.getData();
        this.customerService.createCustomers(fileData);
    }

    public void countMortgageForCustomers() {
        MortgageFormula mortgage = new MortgageFormula();
        mortgage.count(this.customerService.getCustomerRepository());
    }

    public String print() {
        return this.customerService.printData();
    }
}
