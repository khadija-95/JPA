package com.example.demo.Service;

import com.example.demo.Model.Merchant;
import com.example.demo.Model.Product;
import com.example.demo.Model.Stock;
import com.example.demo.Model.User;
import com.example.demo.Repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final UserService userService;
    private final ProductService productService;
    private final MerchantService merchantService;

    private final StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public void addAllStocks(Stock stock){
        stockRepository.save(stock);
    }
    public Boolean updateStock(Integer id,Stock stock ){
        Stock oldStock=stockRepository.getById(id);
        if (oldStock==null){
            return false;
        }
        oldStock.setMerchantid(stock.getMerchantid());
        oldStock.setProductid(stock.getProductid());
        oldStock.setStock(stock.getStock());
        stockRepository.save(oldStock);
        return true;
    }

    public Boolean deleteStock(Integer id){
        Stock stock= stockRepository.getById(id);
        if (stock==null){
            return false;
        }
        stockRepository.delete(stock);
        return true;
    }

}
