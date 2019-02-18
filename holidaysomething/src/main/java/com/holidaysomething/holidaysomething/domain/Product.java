package com.holidaysomething.holidaysomething.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // String은 form 태그 안에 input 에서 아무것도 입력하지 않으면 null 로 처리되는게 안리ㅏ
  // "" 로 처리된다.
  @Column(length = 100, nullable = false)
  private String name;

  //할인 적용전 원가
  @Column(nullable = false)
  private Integer originalPrice;

  //할인 및 마일리지 적용 후 판매가
  @Column(nullable = false)
  private Integer sellingPrice;

  //제조가
  @Column(nullable = false)
  private Integer manufacturingPrice;

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String manufacturer;

  @Column(nullable = false, columnDefinition = "integer default 0")
  private Integer shippingPrice;

  @Column(columnDefinition = "integer default 0")
  private Integer sellingQuantity;

  @Column(columnDefinition = "integer default 0")
  private Integer mileage;

  private Boolean display;
  private String optionalPriceText;
  private LocalDateTime regDate;
  private LocalDateTime manufactureDate;
  private LocalDateTime releaseDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private ProductCategory productCategory;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_detail_id")
  @JsonIgnore
  private ProductDetail productDetail;

  @OneToMany(mappedBy = "product")
  private Set<ProductImage> productImages;

  @OneToMany(mappedBy = "product")
  @JsonIgnore
  private Set<ProductOption> productOptions;
}