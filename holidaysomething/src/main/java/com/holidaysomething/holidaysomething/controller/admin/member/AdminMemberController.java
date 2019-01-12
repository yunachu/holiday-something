package com.holidaysomething.holidaysomething.controller.admin.member;

import com.holidaysomething.holidaysomething.domain.Member;
import com.holidaysomething.holidaysomething.domain.Order;
import com.holidaysomething.holidaysomething.dto.MemberMileageDto;
import com.holidaysomething.holidaysomething.dto.OrderMemberDto;
import com.holidaysomething.holidaysomething.dto.SearchDto;
import com.holidaysomething.holidaysomething.dto.SearchOrderMemberDto;
import com.holidaysomething.holidaysomething.service.member.MemberService;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/member")
@Slf4j
@RequiredArgsConstructor
public class AdminMemberController {

  private final MemberService memberService;

//  @GetMapping("/order/search")
//  public String memberOrderSearch() {
//    return "admin/member/order";
//  }

  // 검색 조건 입력시 endDate가 더 미래여야 한다.  추가해야함.
//  @GetMapping("/order/search")
//  public String memberOrderSearch(
//      @ModelAttribute(value = "SearchOrderMemberDto") SearchOrderMemberDto searchOrderMemberDto,
//      @RequestParam(defaultValue = "", value = "date1", required = false) @DateTimeFormat(pattern = "MMddyyyy") String date1,
//      @RequestParam(defaultValue = "", value = "date2", required = false) @DateTimeFormat(iso = ISO.DATE) String date2,
//      ModelMap model, @PageableDefault(size = 1) Pageable pageable) {
//
//    /*
//      - date1 , date2 가 stringbuffer 이면 null 도 아니고 "" 도 아니고.처음부터 capacity가 16이다...
//      뭐지????
//      - ModelAttribute 객체에 아무런 값을 넣지 않아도 null이 아니다.
//     */
//    boolean is = searchOrderMemberDto == null ? true : false;
//    log.info("searchOrderMemberDto 가 있냐?? " + is);
//
//    if (searchOrderMemberDto.isEmpty() == false) {
//
//      log.info("**************** method=GET memberOrderSearch");
//      log.info(
//          "************** searchOrderMemberDto.getLoginId() :" + searchOrderMemberDto.getLoginId());
//      log.info("************** searchOrderMemberDto.getName() :" + searchOrderMemberDto.getName());
//      log.info("************** searchOrderMemberDto.getProductName() :" + searchOrderMemberDto
//          .getProductName());
//      log.info("************** searchOrderMemberDto.getOrderNumber() :" + searchOrderMemberDto
//          .getOrderNumber());
//
//      // searchOrderMemberDto 에 날짜들은 ... 아...
//      log.info("************** searchOrderMemberDto.getOrderStartDate() :" + searchOrderMemberDto
//          .getOrderStartDate());
//      log.info("************** searchOrderMemberDto.getOrderEndDate() :" + searchOrderMemberDto
//          .getOrderEndDate());
//      log.info("************** date1 :" + date1);
//      log.info("************** date1.length :" + date1.length());
//      log.info("************** date2 :" + date2);
//      if (!date1.equals("null") && !date2.equals("null")) {
////        searchOrderMemberDto.setOrderStartDate(LocalDateTime.parse(date1));
////        searchOrderMemberDto.setOrderEndDate(LocalDateTime.parse(date2));
////        searchOrderMemberDto.setOrderStartDate(date1);
////        searchOrderMemberDto.setOrderEndDate(date2);
//      }
//
//
//
//      log.info("************** searchOrderMemberDto.getOrderStartDate() :" + searchOrderMemberDto
//          .getOrderStartDate());
//      log.info("************** searchOrderMemberDto.getOrderEndDate() :" + searchOrderMemberDto
//          .getOrderEndDate());
//
//      log.info("****************END method=GET memberOrderSearch");
//
////      Page<OrderMemberDto> orderMemberDtoPage = memberService.findMembersBySearchingInQuerydsl(
////          searchOrderMemberDto, pageable);
//      Page<Tuple> tuples = memberService.findMembersBySearchingInQuerydsl(
//          searchOrderMemberDto, pageable);
//
//      log.info("=============== tuples" + tuples.getTotalPages());
//      log.info("=============== tuples" + tuples.getTotalElements());
//
//      long totalElements = tuples.getTotalElements();
//
//      List<Tuple> orderMemberDtos = tuples.getContent();
//      log.info(
//          "**************List<Tuple> 형태. tuples.getContent().size : " + tuples.getContent().size());
//      List<OrderMemberDto> orderMemberDtoList = new ArrayList<>();
//      log.info("************** orderMemberDtos.size() : " + orderMemberDtos.size());
//
//      for (Tuple tuple : tuples) {
//        Object[] objects = tuple.toArray();
//        log.info("+++++++++++++++++++++++ objects.length : " + objects.length);
//        OrderMemberDto temp = new OrderMemberDto((Member) objects[0], (LocalDateTime) objects[1],
//            (String) objects[2]);
//        orderMemberDtoList.add(temp);
//      }
//
//      Page<OrderMemberDto> orderMemberDtoPage =
//          new PageImpl<>(orderMemberDtoList, pageable, totalElements);
//      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getTotalPages() " + orderMemberDtoPage
//          .getTotalPages());
//      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getTotalElements() " + orderMemberDtoPage
//          .getTotalElements());
//      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getSize() " + orderMemberDtoPage.getSize());
//
//
//      model.addAttribute("orderMemberDtoPage", orderMemberDtoPage);
//    }
//
//    return "/admin/member/order";
//  }


  @GetMapping("/order/search")
  public String memberOrderSearchPost(
      @ModelAttribute(value = "SearchOrderMemberDto") SearchOrderMemberDto searchOrderMemberDto,
//      @RequestParam(value = "date1", required = false) @DateTimeFormat(pattern = "MMddyyyy") String date1,
//      @RequestParam(value = "date2", required = false) @DateTimeFormat(iso = ISO.DATE) String date2,
      ModelMap model, @PageableDefault(size = 1) Pageable pageable) {

    /*
      date1 , date2 가 stringbuffer 이면 null 도 아니고 "" 도 아니고.처음부터 capacity가 16이다...
      뭐지????
     */

    if (searchOrderMemberDto.isEmpty() == false) {

      log.info(searchOrderMemberDto.getName());
      log.info(searchOrderMemberDto.getLoginId());
      log.info(searchOrderMemberDto.getProductName());
      log.info(searchOrderMemberDto.getOrderStartDate());
      log.info(searchOrderMemberDto.getOrderEndDate());

//    log.info("date1 : " + date1 + "    date1.capacity = " + date1.length());
//    log.info("date2 : " + date2 + "    date1.capacity = " + date2.length());
//
//    if (!date1.equals("") && !date2.equals("")) {
//      date1 += "T00:00:00";
//      date2 += "T23:59:59";
//      //date1.append("T00:00:00");
//      //date2.append("T23:59:59");
//      log.info("date1 : " + date1);
//      log.info("date2 : " + date2);
//      searchOrderMemberDto.setOrderStartDate(LocalDateTime.parse(date1));
//      searchOrderMemberDto.setOrderEndDate(LocalDateTime.parse(date2));
//      log.info("searchDto : " + searchOrderMemberDto.getOrderStartDate());
//      log.info("searchDto : " + searchOrderMemberDto.getOrderEndDate());
//    } else if (date1.equals("") && date2.equals("")) {
//      log.info("==================== 날짜 빈칸!");
//    }

//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      //Pageable pageable = PageRequest.of(0, 3);
      ;
//    Page<OrderMemberDto> orderMemberDtoPage = memberService.findMembersBySearchingInQuerydsl(
//        searchOrderMemberDto, pageable);
      Page<Tuple> tuples = memberService.findMembersBySearchingInQuerydsl(
          searchOrderMemberDto, pageable);

      log.info("=============== tuples" + tuples.getTotalPages());
      log.info("=============== tuples" + tuples.getTotalElements());

      long totalElements = tuples.getTotalElements();

      List<Tuple> orderMemberDtos = tuples.getContent();
      log.info(
          "**************List<Tuple> 형태. tuples.getContent().size : " + tuples.getContent().size());
      List<OrderMemberDto> orderMemberDtoList = new ArrayList<>();
      log.info("************** orderMemberDtos.size() : " + orderMemberDtos.size());

      for (Tuple tuple : tuples) {
        Object[] objects = tuple.toArray();
        log.info("+++++++++++++++++++++++ objects.length : " + objects.length);
        OrderMemberDto temp = new OrderMemberDto((Member) objects[0], (LocalDateTime) objects[1],
            (String) objects[2]);
        orderMemberDtoList.add(temp);
      }

      Page<OrderMemberDto> orderMemberDtoPage =
          new PageImpl<>(orderMemberDtoList, pageable, totalElements);
      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getTotalPages() " + orderMemberDtoPage
          .getTotalPages());
      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getTotalElements() " + orderMemberDtoPage
          .getTotalElements());
      log.info("%%%%%%%%%%%%%%%% orderMemberDtoPages.getSize() " + orderMemberDtoPage.getSize());

      model.addAttribute("orderMemberDtoPage", orderMemberDtoPage);


    }



    return "/admin/member/order";
  }

  @GetMapping("/mileage/search")
  public String mileageSearch(@PageableDefault(sort = {"loginId"}, size = 10) Pageable pageable,
      @ModelAttribute("search") SearchDto searchDto, ModelMap modelMap) {
    Page<Member> members = memberService.findAllOrSearch(searchDto, pageable);
    modelMap.addAttribute("members", members);

    return "/admin/member/mileage-search";
  }

  @GetMapping("/mileage/modify")
  public String mileageModify(@RequestParam("loginId") String loginId, ModelMap modelMap) {

    modelMap.addAttribute("member", memberService.findMemberByLoginId(loginId));

    return "/admin/member/mileage-modify";
  }

  @PostMapping("/mileage/modify")
  public String mileageModifyPost(@ModelAttribute("mileageModify") MemberMileageDto memberMileageDto) {

    if (memberMileageDto.getAddMileage() < 0 || !memberMileageDto.isPossible())
      return "redirect:/admin";

    memberService.updateMember(memberMileageDto);

    return "redirect:/admin/member/mileage/search";
  }
}



/*
String regDateStart = memberSearchDto.getMemberRegDateStart();
    String regDateEnd = memberSearchDto.getMemberRegDateEnd();

    if (!regDateStart.equals("") && !regDateEnd.equals("")) {
      LocalDateTime startRegDateTime = LocalDateTime
          .parse(regDateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
       LocalDateTime endRegDateTime = LocalDateTime
          .parse(regDateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        jpqlQuery.where(qMember.regDate.between(startRegDateTime, endRegDateTime));
    }
 */