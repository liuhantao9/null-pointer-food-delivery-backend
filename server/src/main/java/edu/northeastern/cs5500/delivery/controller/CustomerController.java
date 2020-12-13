package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.App;
import edu.northeastern.cs5500.delivery.model.Customer;
import edu.northeastern.cs5500.delivery.model.Payment;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.utils.PasswordUtils;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class CustomerController {

    private final GenericRepository<Customer> customerRepository;

    @Inject
    CustomerController(GenericRepository<Customer> customeRepository) {
        this.customerRepository = customeRepository;
    }

    @Nonnull
    public Customer addCustomer(Customer customer) throws Exception {

        if (!customer.isValid()) {
            throw new Exception("InvalidCustomerException");
        }

        String id = UUID.nameUUIDFromBytes(customer.getEmail().getBytes()).toString();
        customer.setId(id);

        if (id != null && this.customerRepository.get(id) != null) {
            return this.getCustomer(id);
        }

        String hashedPwd = PasswordUtils.generateSecurePassword(customer.getPassword(), App.SALT);
        customer.setPassword(hashedPwd);

        this.customerRepository.add(customer);

        return this.getCustomer(id);
    }

    @Nullable
    public Customer getCustomer(String id) throws Exception {
        Customer customer = this.customerRepository.get(id);
        if (customer == null) return null;

        return customer;
    }

    @Nullable
    public Customer signUp(String email, String password) throws Exception {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);

        return this.addCustomer(customer);
    }

    @Nullable
    public Customer login(String email, String password) throws Exception {
        String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
        Customer customer = this.customerRepository.get(id);

        if (customer != null
                && PasswordUtils.verifyUserPassword(password, customer.getPassword(), App.SALT)) {
            return getCustomer(id);
        }
        return null;
    }

    @Nullable
    public Customer editProfile(String id, String address, String avatar, String phone, String name)
            throws Exception {
        Customer customer = this.getCustomer(id);
        if (customer == null) return null;
        if (!address.isEmpty()) customer.setAddress(address);
        if (!avatar.isEmpty()) customer.setAvatar(avatar);
        if (!phone.isEmpty()) customer.setPhone(phone);
        if (!name.isEmpty()) customer.setName(name);

        this.customerRepository.update(customer);

        return getCustomer(id);
    }

    @Nullable
    public Customer editPayment(String id, Payment payment) throws Exception {
        Customer customer = this.getCustomer(id);
        if (customer == null) return null;

        customer.setPayment(payment);
        this.customerRepository.update(customer);

        return getCustomer(id);
    }

    @Nullable
    public boolean resetPassword(String id, String oldPswd, String newPswd) {
        Customer customer = this.customerRepository.get(id);
        if (customer == null) return false;

        if (PasswordUtils.verifyUserPassword(oldPswd, customer.getPassword(), App.SALT)) {
            String hashedNewPswd = PasswordUtils.generateSecurePassword(newPswd, App.SALT);
            customer.setPassword(hashedNewPswd);
            customer = this.customerRepository.update(customer);
            return true;
        }
        return false;
    }
}
