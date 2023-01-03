package com.outsource.bankappspringboot.api;

import com.outsource.bankappspringboot.dto.CreateCustomerRequest;
import com.outsource.bankappspringboot.dto.CustomerDto;
import com.outsource.bankappspringboot.dto.UpdateCustomerRequest;
import com.outsource.bankappspringboot.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }
   @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
   }
   @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerDtoById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerDtoById(id));
   }
   @PutMapping("/{id}")
   public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerRequest updateCustomerRequest){
   return ResponseEntity.ok(customerService.updateCustomer(id, updateCustomerRequest));
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(String id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
   }


}