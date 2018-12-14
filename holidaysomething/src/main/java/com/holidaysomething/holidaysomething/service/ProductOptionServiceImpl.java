package com.holidaysomething.holidaysomething.service;

import com.holidaysomething.holidaysomething.domain.ProductOption;
import com.holidaysomething.holidaysomething.repository.ProductOptionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductOptionServiceImpl implements ProductOptionService {
    private ProductOptionRepository productOptionRepository;

    public ProductOptionServiceImpl(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    @Override
    public void addProductOption(ProductOption productOption){
        productOptionRepository.save(productOption);
    }
}
