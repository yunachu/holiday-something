package com.holidaysomething.holidaysomething.repository.custom;

import com.holidaysomething.holidaysomething.domain.Member;
import com.holidaysomething.holidaysomething.dto.MemberSearchDto;
import com.holidaysomething.holidaysomething.dto.SearchOrderMemberDto;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

  // test
  Member getMemberByDsl(Long id);

  Page<Tuple> findMembersByLoginIdInOrdersByDsl(String loginId, Pageable pageable);

  List findMembersByNameInOrdersByDsl(String name);

  List<Tuple> findMembersByProductNameInOrdersByDsl(String productName);

  List<Tuple> findMembersByProductPeriodInOrdersByDsl(LocalDateTime startDate,
      LocalDateTime endDate);

  List<Tuple> findMembersByProductCodeInOrdersByDsl(String code);

  Page<Tuple> getMembersByDsl(SearchOrderMemberDto searchOrderMemberDto, Pageable pageable);

  Page<Member> searchMembers(MemberSearchDto memberSearchDto, Pageable pageable);

//  List<CurrentMemberDto> findCurrentMember(long userId);
//  List<Tuple> findCurrentMemberTuple(Long userId);
}
