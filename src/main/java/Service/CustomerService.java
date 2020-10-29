package Service;

import Repository.CustomerRepository;
import Domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void createCustomers(List<String> fileData) {
        for (String line : fileData) {
            List<String> customer = split(line);
            if (customer.size() == 4) {
                String name = customer.get(0);
                double totalLoan = Double.parseDouble(customer.get(1));
                double interest = Double.parseDouble(customer.get(2));
                int years = Integer.parseInt(customer.get(3));
                this.customerRepository.addCustomer(new Customer(name,totalLoan,interest,years));;
            }
        }
    }

    public String printData() {
        StringBuilder builder = new StringBuilder();
        for (Customer customer : this.customerRepository.getCustomers()) {
            String totalLoanFormat = String.format("%.2f", customer.getTotalLoan());
            String fixedPaymentFormat = String.format("%.2f", customer.getFixedMonthlyPayment());
            builder.append(customer.getName() + " wants to borrow " + totalLoanFormat + " € for a period " +
                    "of " + customer.getYears() + " years and pay " + fixedPaymentFormat + " € each month.\n");
        }
        return builder.toString();
    }

    private List<String> split(String line) {
        int nestingLevel = 0;
        StringBuilder result = new StringBuilder();
        List<String> split = new ArrayList<>();
        for (char currentChar : line.toCharArray()) {
            if (currentChar == ',' && nestingLevel == 0) {
                split.add(result.toString());
                result.setLength(0);
            } else {
                if (currentChar == '"' && nestingLevel == 0) {
                    nestingLevel++;
                } else if (currentChar == '"' && nestingLevel == 1){
                    nestingLevel--;
                } else {
                    result.append(currentChar);
                }
            }
        }
        split.add(result.toString());
        return split;
    }
}
