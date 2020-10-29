package Utility;

import Repository.CustomerRepository;
import Domain.Customer;

public class MortgageFormula {

    public MortgageFormula() {

    }

    public void count(CustomerRepository customers) {
        for (Customer customer : customers.getCustomers()) {
            countNumberOfPayments(customer);
            countFixedMonthlyPayment(customer);
        }
    }

    private void countNumberOfPayments(Customer customer) {
        int numberOfPayments = customer.getYears()*12;
        customer.setNumberOfPayments(numberOfPayments);
    }

    private void countFixedMonthlyPayment(Customer customer) {
        double convertInterest = customer.getInterest() / 100 / 12;
        double interestPotencyNumberOfPayments = (1 + convertInterest);
        for (int i = 0; i < customer.getNumberOfPayments() - 1; i++) {
            interestPotencyNumberOfPayments *= (1 + convertInterest);
        }
        double result = customer.getTotalLoan() * (convertInterest * interestPotencyNumberOfPayments) /
                (interestPotencyNumberOfPayments - 1);
        customer.setFixedMonthlyPayment(result);
    }
}
