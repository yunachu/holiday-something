package com.holidaysomething.holidaysomething.repository;

import com.holidaysomething.holidaysomething.domain.CartProduct;
import com.holidaysomething.holidaysomething.dto.UserCartProductDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

  @Query(value = "SELECT new com.holidaysomething.holidaysomething.dto.UserCartProductDto"
      + "(cp.id, p.id, p.name, cp.quantity, p.originalPrice, p.sellingPrice, p.shippingPrice, "
      + "pi.path, po.id, po.name, po.price, po.description) "
      + "FROM CartProduct as cp "
      + "INNER JOIN Member as m ON cp.member.id = m.id "
      + "INNER JOIN Product as p ON cp.product.id = p.id "
      + "INNER JOIN ProductImage as pi ON p.id = pi.product.id "
      + "INNER JOIN ProductOption as po ON cp.productOption.id = po.id "
      + "WHERE m.id = (:userId)")
  List<UserCartProductDto> findCartProductById(@Param("userId") Long userId);

  @Query(value = "SELECT cp FROM CartProduct cp WHERE cp.product.id=(:productId) and cp.member.id=(:userId) and cp.productOption.id=(:optionId)")
  CartProduct findCartProductByIds(@Param("userId") Long userId, @Param("productId") Long productId, @Param("optionId") Long optionId);
}
