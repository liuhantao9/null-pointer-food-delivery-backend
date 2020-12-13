package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.App;
import edu.northeastern.cs5500.delivery.model.Customer;
import edu.northeastern.cs5500.delivery.model.Payment;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import edu.northeastern.cs5500.delivery.utils.PasswordUtils;
import java.util.UUID;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CustomerControllerTest {

    @Test
    public void testAddCustomer() throws Exception {
        CustomerController customerController =
                new CustomerController(new InMemoryRepository<Customer>());
        Customer testNull = new Customer();
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    customerController.addCustomer(testNull);
                });

        Customer testAdd = new Customer();
        testAdd.setEmail("email");
        testAdd.setPassword("password");
        Customer resultAdd = new Customer();
        resultAdd.setEmail("email");
        resultAdd.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        resultAdd.setId(UUID.nameUUIDFromBytes("email".getBytes()).toString());

        Assertions.assertEquals(resultAdd, customerController.addCustomer(testAdd));

        Customer testDuplicateAdd = customerController.addCustomer(testAdd);
        Assertions.assertEquals(resultAdd, testDuplicateAdd);
    }

    @Test
    public void testGetCustomer() throws Exception {
        CustomerController customerController =
                new CustomerController(new InMemoryRepository<Customer>());
        Assertions.assertNull(customerController.getCustomer("hello"));

        Customer temp = new Customer();
        temp.setEmail("email");
        temp.setPassword("password");
        Customer result = customerController.addCustomer(temp);
        Customer expected = new Customer();
        expected.setEmail("email");
        expected.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        expected.setId(UUID.nameUUIDFromBytes("email".getBytes()).toString());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSignup() throws Exception {
        GenericRepository repository = new InMemoryRepository<Customer>();
        CustomerController controller = new CustomerController(repository);

        Customer result = controller.signUp("email", "password");
        Assertions.assertTrue(repository.count() != 0);
        Customer expected = new Customer();
        expected.setEmail("email");
        expected.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        expected.setId(UUID.nameUUIDFromBytes("email".getBytes()).toString());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testLogin() throws Exception {
        GenericRepository repository = new InMemoryRepository<Customer>();
        CustomerController controller = new CustomerController(repository);
        Assertions.assertNull(controller.login("email123", "password123"));
        Customer expected = new Customer();
        expected.setEmail("email");
        expected.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        expected.setId(UUID.nameUUIDFromBytes("email".getBytes()).toString());
        controller.signUp("email", "password");
        Assertions.assertTrue(repository.count() != 0);
        Assertions.assertEquals(expected, controller.login("email", "password"));
    }

    @Test
    public void testEditProfile() throws Exception {
        CustomerController customerController =
                new CustomerController(new InMemoryRepository<Customer>());

        Customer testEditAddress = new Customer();
        testEditAddress.setEmail("email1");
        testEditAddress.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        testEditAddress.setId(UUID.nameUUIDFromBytes("email1".getBytes()).toString());
        testEditAddress.setAddress("address");

        Customer testEditAvatar = new Customer();
        testEditAvatar.setEmail("email2");
        testEditAvatar.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        testEditAvatar.setId(UUID.nameUUIDFromBytes("email2".getBytes()).toString());
        testEditAvatar.setAvatar("avatar");

        Customer testEditPhone = new Customer();
        testEditPhone.setEmail("email3");
        testEditPhone.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        testEditPhone.setId(UUID.nameUUIDFromBytes("email3".getBytes()).toString());
        testEditPhone.setPhone("phone");

        Customer testEditName = new Customer();
        testEditName.setEmail("email4");
        testEditName.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        testEditName.setId(UUID.nameUUIDFromBytes("email4".getBytes()).toString());
        testEditName.setName("name");

        Assertions.assertNull(
                customerController.editProfile("id", "address", "avatar", "phone", "name"));

        customerController.signUp("email1", "password");
        String id = UUID.nameUUIDFromBytes("email1".getBytes()).toString();
        Assertions.assertEquals(
                testEditAddress, customerController.editProfile(id, "address", "", "", ""));

        customerController.signUp("email2", "password");
        id = UUID.nameUUIDFromBytes("email2".getBytes()).toString();
        Assertions.assertEquals(
                testEditAvatar, customerController.editProfile(id, "", "avatar", "", ""));

        customerController.signUp("email3", "password");
        id = UUID.nameUUIDFromBytes("email3".getBytes()).toString();
        Assertions.assertEquals(
                testEditPhone, customerController.editProfile(id, "", "", "phone", ""));

        customerController.signUp("email4", "password");
        id = UUID.nameUUIDFromBytes("email4".getBytes()).toString();
        Assertions.assertEquals(
                testEditName, customerController.editProfile(id, "", "", "", "name"));
    }

    @Test
    public void testEditPayment() throws Exception {
        CustomerController customerController =
                new CustomerController(new InMemoryRepository<Customer>());
        Assertions.assertNull(customerController.editPayment("id", null));

        Payment payment = new Payment();
        payment.setCardNumber("cardNumber");
        payment.setCvc("cvc");
        payment.setExpDate("expDate");
        payment.setHolderName("holderName");

        Customer expected = new Customer();
        expected.setEmail("email");
        expected.setPassword(PasswordUtils.generateSecurePassword("password", App.SALT));
        expected.setId(UUID.nameUUIDFromBytes("email".getBytes()).toString());
        expected.setPayment(payment);

        customerController.signUp("email", "password");
        String id = UUID.nameUUIDFromBytes("email".getBytes()).toString();

        Assertions.assertEquals(expected, customerController.editPayment(id, payment));
    }

    @Test
    public void testResetPassword() throws Exception {
        CustomerController customerController =
                new CustomerController(new InMemoryRepository<Customer>());
        Assertions.assertFalse(customerController.resetPassword("id", "oldPswd", "newPswd"));

        Customer test = customerController.signUp("email", "password");
        String id = UUID.nameUUIDFromBytes("email".getBytes()).toString();
        Assertions.assertFalse(customerController.resetPassword(id, "oldPswd", "newPswd"));
        Assertions.assertTrue(customerController.resetPassword(id, "password", "newPswd"));
    }
}
