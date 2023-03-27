package travel.travelagency.database;

import travel.travelagency.entities.Customer;

import javax.persistence.EntityManager;

public class CustomerManagerImplementation implements CustomerManager{

  private EntityManager entityManager;

  public CustomerManagerImplementation(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Customer getCustomer(int customerID) {
    return entityManager.find(Customer.class, customerID);
  }

  @Override
  public void addCustomer(Customer customer) {
    entityManager.persist(customer);
  }

  @Override
  public void deleteCustomer(Customer customer) {
    entityManager.remove(customer);
  }

}
