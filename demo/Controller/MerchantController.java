package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Merchant;
import com.example.demo.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchants(){
        return ResponseEntity.status(200).body(merchantService.getAllMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addAllMerchants(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addAllMerchants(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id ,@RequestBody @Valid Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }
        boolean isUpdateMerchant =merchantService.updateMerchant(id, merchant);
        if (isUpdateMerchant){
            return ResponseEntity.status(200).body(new ApiResponse("merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){
        boolean isDelete = merchantService.deleteMerchant(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("merchant deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getMerchantById(@PathVariable Integer id) {
        Merchant merchant = merchantService.getMerchantById(id);
        if (merchant == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
        }
        return ResponseEntity.status(200).body(merchant);
    }
}
