package com.holidaysomething.holidaysomething.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
// @JsonIdentityInfo: Bidirectional(양방향) 관계 무한 loop을 방지하기 위함. 반대편 Entity에도 써줘야 한다.
// https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false, unique = true)
  private String loginId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(length = 20, nullable = false)
  private String name;

  @Column(length = 50, nullable = false, unique = true)
  private String nickname;

  @Column(length = 50, nullable = false, unique = true)
  private String phone;

  @Column(columnDefinition = "integer default 0")
  private int mileage;

  @Column(nullable = false)
  private LocalDate birthday;

  @Column(length = 20, nullable = false)
  private String postcode;

  @Column(nullable = false)
  private String address1;

  @Column(nullable = false)
  private String address2;

  @JsonIgnore
  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime regDate;

  @JsonIgnore
  @Column(nullable = false)
  @UpdateTimestamp
  private LocalDateTime lastLogin;

  @JsonIgnore
  @Column(nullable = false)
  private boolean receiveEmail;

  @JsonIgnore
  @Column(nullable = false)
  private boolean receiveSms;

  @JsonIgnore
  @Column(nullable = false)
  private boolean marketing;

  @JsonIgnore
  @Column(nullable = false)
  private boolean personalInfo;

  @JsonIgnore
  @Column(length = 20)
  private String recommender;

  @Column(length = 10, nullable = false)
  private String sex;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private Set<CartProduct> cartProducts = new HashSet<>();

  // TODO: JsonIgnore 안하면 "Could not write JSON: failed to lazily initialize a collection of role..." 에러 발생
  // TODO: JsonIgnore 하면 위의 에러는 발생하지 않지만 orders에 데이터가 안들어감
  // Eager loading을 하면 member를 조회하는 순간 order 내부까지 다 조회해서 포함된다.
  @OneToMany(mappedBy = "member")
  private Set<Order> orders = new HashSet<>();

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "member_role",
      joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();
}
