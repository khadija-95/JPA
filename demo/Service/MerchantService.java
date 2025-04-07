package com.example.demo.Service;

import com.example.demo.Model.Category;
import com.example.demo.Model.Merchant;
import com.example.demo.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants(){
        return merchantRepository.findAll();
    }

    public void addAllMerchants(Merchant merchant){
        merchantRepository.save(merchant);
    }
    public Boolean updateMerchant(Integer id,Merchant merchant){
        Merchant oldMerchant=merchantRepository.getById(id);
        if (oldMerchant==null){
            return false;
        }
        oldMerchant.setName(merchant.getName());
        merchantRepository.save(oldMerchant);
        return true;
    }

    public Boolean deleteMerchant(Integer id){
        Merchant merchant= merchantRepository.getById(id);
        if (merchant==null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }


    public Merchant getMerchantById(Integer id) {
        Merchant merchant = merchantRepository.getById(id);
        if (merchant.getId().equals(id)) {
            return merchant;

        }
        return null;
    }
}
