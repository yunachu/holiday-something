package com.holidaysomething.holidaysomething.controller.user.member;

import com.holidaysomething.holidaysomething.domain.Member;
import com.holidaysomething.holidaysomething.dto.MemberAddDto;
import com.holidaysomething.holidaysomething.service.member.MemberAddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class MemberAddController {

    private final MemberAddService memberAddService;

    @GetMapping("/join")
    public String memberAdd(ModelMap model) {

        Member member = new Member();
        model.addAttribute("member", member);


        return "/user/join";
    }

    @PostMapping("/join")
    public String memberAddPost(
            @Valid @ModelAttribute(value = "member")MemberAddDto memberAddDto,
            BindingResult bindingResult, ModelMap model){

        memberAddDto.setRegDate(LocalDateTime.now());
        memberAddDto.setLastLogin(LocalDateTime.now());
        memberAddService.memberRegister(memberAddDto);

        return "redirect:/user/join";
    }
}
