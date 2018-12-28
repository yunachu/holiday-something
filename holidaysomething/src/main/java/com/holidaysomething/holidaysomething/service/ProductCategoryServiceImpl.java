package com.holidaysomething.holidaysomething.service;

import com.holidaysomething.holidaysomething.domain.ProductCategory;
import com.holidaysomething.holidaysomething.repository.ProductCategoryRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

  private ProductCategoryRepository productCategoryRepository;

  public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
    this.productCategoryRepository = productCategoryRepository;
  }

  @Override
  public List<ProductCategory> getCategory(Long parentId) {
    return productCategoryRepository.findByParentId(parentId);
  }
}
