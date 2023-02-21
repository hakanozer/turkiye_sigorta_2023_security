package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDetailService {

    final CustomerRepository customerRepository;
    final PasswordEncoder encoder;

    public ResponseEntity register(Customer customer) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(customer.getUsername());
        if(optionalCustomer.isPresent() ) {
            hm.put("status", false);
            hm.put("message", "This usrename all ready in use");
        }else {
            customer.setPassword( encoder.encode( customer.getPassword() ) );
            customerRepository.save(customer);
            hm.put("status", true);
            hm.put("result", customer);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }



}
