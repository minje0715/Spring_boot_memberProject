package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/save-form")
    public String saveForm() {
        return "memberPages/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        System.out.println("memberDTO = " + memberDTO);
        return "index";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "memberPages/login";
    }
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {
      MemberDTO loginResult = memberService.login(memberDTO);
      if(loginResult != null) {
          session.setAttribute("loginEmail", loginResult.getMemberEmail());
          session.setAttribute("id", loginResult.getId());
          return "memberPages/main";
      }else {
          return "memberPages/login";
      }
    }
    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/list";
    }
}
