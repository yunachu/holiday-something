package com.holidaysomething.holidaysomething.service.product;

import com.holidaysomething.holidaysomething.dto.UserCartProductDto;
import java.util.List;

public interface CartProductService {

  List<UserCartProductDto> getUserCartProduct(Long userId);

  void removeCartProductById(Long cartProductId);
}
