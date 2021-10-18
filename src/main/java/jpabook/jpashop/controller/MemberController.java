package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(@RequestBody MemberForm form){

        //BindingResult가 있으면 오류가 담겨서 실행된다


        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        if(!"1".equals(form.getAdminCheck())){
            member.setAdminCheck("0");
        }
        else{
            member.setAdminCheck(form.getAdminCheck());
        }

        memberService.join(member);


        return "login/login"; //첫번째 페이지로 넘어감.
    }

    @GetMapping("/members/{memberId}/MyUpdate")
    public List<Object> updateForm(@PathVariable("memberId") Long memberId, Model model){

        List<Object> resultAll = new ArrayList<>();

        Member ond = memberService.findOnd(memberId);
        MemberForm memberForm = new MemberForm();

        memberForm.setName(ond.getName());

        memberForm.setCity(ond.getAddress().getCity());
        memberForm.setStreet(ond.getAddress().getStreet());
        memberForm.setZipcode(ond.getAddress().getZipcode());
        memberForm.setAdminCheck(ond.getAdminCheck());
        //model.addAttribute("memberForm", memberForm);

        resultAll.add(memberForm);
        return resultAll;
        //return "members/UpdateMemberForm";
    }

    @PostMapping("/members/{memberId}/MyUpdate")
    public List<Object> updateMemberForm(@RequestBody MemberForm memberRequest , @PathVariable("memberId") Long memberId){

        String adminCheck = memberRequest.getAdminCheck();
        List<Object> resultAll = new ArrayList<>();

        try {
            memberService.updateForm(memberId ,memberRequest);
            if("1".equals(adminCheck)){
                resultAll.add("home_admin");
            }
            else{
                resultAll.add("home");
            }

        }
        catch (Exception e){
            e.getMessage();
        }

        return resultAll;
       // return "redirect:/members";
    }

    @GetMapping("members")
    public List<Object> list(Model model){
        List<Object> resultAll = new ArrayList<>();

        List<Member> memebers = memberService.findMemebers();
        for (Member memeber : memebers) {
            resultAll.add(memeber);
        }
        //resultAll.add(memebers);
        //model.addAttribute("members", memebers);
        //return "members/memberList";
        return resultAll;
    }


    /*
    여기부터는 로그인 추가 코딩
    주석으로 -> 기능 확장  이라고 되어있는건 추가확장코드임
     */

    @PostMapping("signIn")
    public List<Object> signIn(String inputEmail, String inputPassword , Model model) {
        log.info("id : {} , pw : {}", inputEmail, inputPassword);

        List<Object> resultAll = new ArrayList<>();

        Member member = memberService.findMemberForLogin(inputEmail, inputPassword);
        if(member != null) {
            //model.addAttribute("member", member);

            resultAll.add(member.getId());
            if("1".equals(member.getAdminCheck())){ //관리자 페이지

                resultAll.add("home_admin");
                return resultAll;
            }
            else{ //관리자가 아닐때
                resultAll.add("home");
                return resultAll;
            }
        }

        resultAll.add(null);
        resultAll.add("login/loginFail");

        return resultAll;
    }


}
