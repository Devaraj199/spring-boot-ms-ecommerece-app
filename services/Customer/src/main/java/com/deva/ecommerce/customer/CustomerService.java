package com.deva.ecommerce.customer;

import com.deva.ecommerce.customer.exceptions.CustomerNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;
    public Long createCustomer(CustomerRequest request) {
        var customer = this.repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(
                ()-> new CustomerNotFoundException(String.format("Cannot update customer: No customer found with provide by ID:%s",request.id()))
        );
        mergeCustomer(customer,request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public boolean isCustomerExist(Long id) {
       return  this.repository.findById(id).isPresent();
    }

    public Customer getCustomerById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("No customer found with the provided  ID",id)));
    }


    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.repository.findAll());
    }

    public void deleteCustomerById(Long id) {
     boolean isCustomerExist= this.repository.findById(id).isPresent();
     if(isCustomerExist){
         this.repository.deleteById(id);
     }
    }
}
