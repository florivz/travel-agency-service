package travel.travelagency.database;

import travel.travelagency.entities.Customer;

public interface CustomerManager {

  /**
   * This method returns the customer with the corresponding customer id from the database
   * If no customer with such id is found null will be returned
   * @param customerID unique customer identification number
   * @return customer belonging to the identification number
   */
  public Customer getCustomer(int customerID);

  /**
   * This method adds the customer specified to the database
   * @param customer customer that should be added to the database
   */
  public void addCustomer(Customer customer);

  /**
   * This method deletes the customer specified from the database
   * @param customer customer that should be deleted from the database
   */
  public void deleteCustomer(Customer customer);

}
