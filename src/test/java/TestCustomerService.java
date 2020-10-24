import dao.Customer;
import dao.CustomerActionsDao;
import dao.CustomerActionsDaoImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.*;
import services.CustomerService;


@Test
public class TestCustomerService {
    //private CustomerActionsDaoImpl customerDaoMock = null;
    private CustomerService customerMock = null;

    @BeforeClass
    public void init() {
        //customerDaoMock = Mockito.mock(CustomerActionsDaoImpl.class);
        customerMock = Mockito.mock(CustomerService.class);
    }

    @Test
    public void testCreateNewCustomer() {
        CustomerService customerService = new CustomerService();
        int result = customerService.createNewCustomer("firstname", "lastname", "phone");
        Assert.assertEquals(1, result);
    }

    @Test
    public void testGetCustomerById() {
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer(1, "Ivan", "Ivanov", "123");
        Mockito.when(customerMock.getCustomerById(1)).thenReturn(customer);
        //Mockito.doReturn(customer).when(customerDaoMock).getCustomerById(1);
        Customer customer2 = customerService.getCustomerById(1);
        Assert.assertEquals(customer, customerService.getCustomerById(1));
    }
}
