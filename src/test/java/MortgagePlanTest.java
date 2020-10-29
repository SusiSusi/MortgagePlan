import Controller.CustomerController;
import Domain.Customer;
import Utility.MortgageFormula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MortgagePlanTest {
    private CustomerController instance;

    @Before
    public void before() {
        this.instance = new CustomerController();
        this.instance.setCustomers("/prospectsForTest.txt");
    }

    @Test
    public void setCustomersTest() {
        assertEquals(instance.getCustomerService().getCustomerRepository().getCustomers().size(), 5);
    }

    @Test
    public void addCustomerTest() {
        Customer customer = new Customer("Test", 20000.2,3.21,7);
        instance.getCustomerService().getCustomerRepository().addCustomer(customer);
        assertEquals(instance.getCustomerService().getCustomerRepository().getCustomers().size(), 6);
    }

    @Test
    public void printDataWithoutFixedMonthlyPaymentTest() {
        String test = "Karvinen wants to borrow 4356,00 € for a period of 6 years and pay 0,00 € each month.\n" +
                "Claes Månsson wants to borrow 1300,55 € for a period of 2 years and pay 0,00 € each month.\n" +
                "Clarencé,Andersson wants to borrow 2000,00 € for a period of 4 years and pay 0,00 € each month.\n" +
                "Jon wants to borrow 200000000,00 € for a period of 40 years and pay 0,00 € each month.\n" +
                "Bob wants to borrow 40000,00 € for a period of 20 years and pay 0,00 € each month.\n";
        assertEquals(instance.print(), test);
    }

    @Test
    public void printDataWithFixedMonthlyPaymentTest() {
        MortgageFormula mortgage = new MortgageFormula();
        mortgage.count(this.instance.getCustomerService().getCustomerRepository());
        String test = "Karvinen wants to borrow 4356,00 € for a period of 6 years and pay 62,87 € each month.\n" +
                "Claes Månsson wants to borrow 1300,55 € for a period of 2 years and pay 59,22 € each month.\n" +
                "Clarencé,Andersson wants to borrow 2000,00 € for a period of 4 years and pay 46,97 € each month.\n" +
                "Jon wants to borrow 200000000,00 € for a period of 40 years and pay 425072,91 € each month.\n" +
                "Bob wants to borrow 40000,00 € for a period of 20 years and pay 166,67 € each month.\n";
        assertEquals(instance.print(), test);
    }

    @Test
    public void countFixedMonthlyPaymentTest() {
        Customer customer = this.instance.getCustomerService().getCustomerRepository().getCustomers().get(0);
        assertEquals(customer.getFixedMonthlyPayment(), 0,0);
        this.instance.countMortgageForCustomers();
        assertEquals(customer.getFixedMonthlyPayment(), 62.86631476623255,0);
    }

    @Test
    public void countNumberOfPaymentsTest() {
        Customer customer = this.instance.getCustomerService().getCustomerRepository().getCustomers().get(1);
        assertEquals(customer.getNumberOfPayments(), 0);
        this.instance.countMortgageForCustomers();
        assertEquals(customer.getNumberOfPayments(), 24);
    }
}
