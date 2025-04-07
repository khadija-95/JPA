package com.example.demo.Service;

import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void addAllProducts(Product product){
        productRepository.save(product);
    }
    public Boolean updateProduct(Integer id,Product product){
        Product oldProduct=productRepository.getById(id);
        if (oldProduct==null){
            return false;
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategoryID(product.getCategoryID());
        productRepository.save(oldProduct);
        return true;
    }

    public Boolean deleteProduct(Integer id){
        Product product= productRepository.getById(id);
        if (product==null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }
    public String applyDiscount(Integer id,User user, double discountPercentage, double priceThreshold) {
        if (!user.getRole().equalsIgnoreCase("Admin")) {
            return "Access denied! Only Admin can apply discounts.";
        }

        Product product=productRepository.getById(id);
            if (product.getPrice() >= priceThreshold) {
                double newPrice = product.getPrice() - (product.getPrice() * (discountPercentage / 100));
                product.setPrice(newPrice);
            }


        return "Discount applied to eligible products!";
    }

    public String compareProductPrices(Integer id,Integer productId1, Integer productId2) {
        Product product1 = null;
        Product product2 = null;

        Product product=productRepository.getById(id);
            if (product.getId().equals(productId1)) product1 = product;
            if (product.getId().equals(productId2)) product2 = product;


        if (product1 == null || product2 == null) {
            return "One or both products not found!";
        }

        if (product1.getPrice() < product2.getPrice()) {
            return product1.getName() + " is cheaper than " + product2.getName();
        } else if (product1.getPrice() > product2.getPrice()) {
            return product2.getName() + " is cheaper than " + product1.getName();
        } else {
            return "Both products have the same price.";
        }
    }

    public Product getProductById(Integer id) {
        Product product=productRepository.getById(id);
            if (product.getId().equals(id)) {
                return product;
            }
        return null;
        }


}
