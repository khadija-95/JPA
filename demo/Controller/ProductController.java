package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addAllProducts(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.addAllProducts(product);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id ,@RequestBody @Valid Product product,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }
        boolean isUpdateProduct =productService.updateProduct(id, product);
        if (isUpdateProduct){
            return ResponseEntity.status(200).body(new ApiResponse("product updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        boolean isDelete = productService.deleteProduct(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("product deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @PostMapping("/applyDiscount/{id}")
    public ResponseEntity<String> applyDiscount(@PathVariable Integer id,@RequestParam String role,@RequestParam double discountPercentage,@RequestParam double priceThreshold) {

        User user = userService.getUserByRole(role,id);

        if (!user.getRole().equalsIgnoreCase("Admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied! Only Admin can apply discounts.");
        }

        String result = productService.applyDiscount(id,user,discountPercentage, priceThreshold);
        return ResponseEntity.ok(result);
    }
    // Compare the prices of two different products
    @GetMapping("/compare/{id}")
    public ResponseEntity<ApiResponse> compareProducts(@PathVariable Integer id,@RequestParam Integer productId1, @RequestParam Integer productId2) {
        String response = productService.compareProductPrices(id,productId1, productId2);
        return ResponseEntity.ok(new ApiResponse(response));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
        return ResponseEntity.status(200).body(product);
    }
}
