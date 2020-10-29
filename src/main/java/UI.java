import Controller.CustomerController;

public class UI {
    private CustomerController controller;

    public UI() {
        this.controller = new CustomerController();
    }

    public void start() {
        this.controller.setCustomers("/prospects.txt");
        this.controller.countMortgageForCustomers();
        System.out.println(this.controller.print());
    }
}
