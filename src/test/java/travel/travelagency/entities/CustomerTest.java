package travel.travelagency.entities;

import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

  private static Customer nullCustomer, emptyCustomer, customer, copyCustomer, differentCustomer;

  @BeforeAll
  public static void initializeCustomers() {
    nullCustomer = null;
    emptyCustomer = new Customer();
    customer = new Customer(
        123,
        "DE19582919875254589658745236512589",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            new Date(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        ),
        new Address(175,"Street","101a","19824","My Town","Disneyland" )
    );
    copyCustomer = new Customer(
        123,
        "DE19582919875254589658745236512589",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            new Date(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        ),
        new Address(175,"Street","101a","19824","My Town","Disneyland" )
    );
    differentCustomer = new Customer(
        497,
        "DE12457886135615487659132658132548",
        new PersonalData(
            98,
            "Scholz",
            "Olaf",
            "",
            new Date(1987, 11, 17),
            new Address(7, "Way", "9", "65958", "Stadt", "Osmanien")
        ),
        new Address(7, "Dwy", "18b", "HKL21", "Town", "Ivy")
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyCustomer.equals(nullCustomer));
    assertFalse(emptyCustomer.equals(nullCustomer));
  }

  @Test
  public void testEqualsMethodWithEmptyCustomer() {
    Customer copyEmptyCustomer = new Customer();
    assertDoesNotThrow(() -> emptyCustomer.equals(copyEmptyCustomer));
    assertTrue(emptyCustomer.equals(copyEmptyCustomer));
  }

  @Test
  public void testEqualsMethodWithIdenticalCustomer() {
    assertDoesNotThrow(() -> customer.equals(copyCustomer));
    assertTrue(customer.equals(copyCustomer));
  }

  @Test
  public void testEqualsMethodWithDifferentCustomer() {
    assertDoesNotThrow(() -> customer.equals(differentCustomer));
    assertFalse(customer.equals(differentCustomer));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> customer.equals(obj));
    assertFalse(customer.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Customer peterLustig = new Customer(
        123,
        "DE19582919875254589658745236512589",
        new PersonalData(
            43,
            "Lustig",
            "Peter",
            "Fritz Willi",
            new Date(1937, 10, 20),
            new Address(19, "Street", "101a", "19824", "My Town", "Disneyland")
        ),
        new Address(98,"Billing Street","69b","98425","Cash Town","Cashhausen" )
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 20.10.1937
        Street 101a, 19824 My Town, Disneyland
        DE19582919875254589658745236512589
        Customer Number: 123
        Billing Address: Billing Street 69b, 98425 Cash Town, Cashhausen""",
        peterLustig.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalCustomer() {
    int expectedHashCode  = customer.hashCode();
    int actualHashCode    = copyCustomer.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentCustomer() {
    int unexpectedHashCode  = customer.hashCode();
    int actualHashCode      = differentCustomer.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
