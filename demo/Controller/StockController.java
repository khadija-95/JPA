package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Stock;
import com.example.demo.Service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/get")
    public ResponseEntity getAllStocks(){
        return ResponseEntity.status(200).body(stockService.getAllStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addAllStocks(@Valid @RequestBody Stock stock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        stockService.addAllStocks(stock);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStockt(@PathVariable Integer id ,@RequestBody @Valid Stock stock,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }
        boolean isUpdateMerchant =stockService.updateStock(id, stock);
        if (isUpdateMerchant){
            return ResponseEntity.status(200).body(new ApiResponse("stock updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id){
        boolean isDelete = stockService.deleteStock(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("stock deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
}
