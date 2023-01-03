package com.outsource.bankappspringboot.api;

import com.outsource.bankappspringboot.dto.AccountDto;
import com.outsource.bankappspringboot.dto.CreateAccountRequest;
import com.outsource.bankappspringboot.dto.UpdateAccountRequest;
import com.outsource.bankappspringboot.repository.AccountRepository;
import com.outsource.bankappspringboot.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    private final AccountService  accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

     @PutMapping ("/{id}")
     public ResponseEntity<AccountDto> updateAccount(@PathVariable String id,@RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id,updateAccountRequest));
     }
     @GetMapping
    public ResponseEntity<List<AccountDto>> getAllCustomer(){
        return ResponseEntity.ok(accountService.getAllCustomer());
}

     @GetMapping("/{id}")
     public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));

     }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
     }
}
