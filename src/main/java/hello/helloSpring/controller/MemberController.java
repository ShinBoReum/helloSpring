package hello.helloSpring.controller;

import hello.helloSpring.domain.Member;
import hello.helloSpring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final Memberservice memberservice;

    @Autowired
    public MemberController(Memberservice memberservice) {
        this.memberservice = memberservice;
    }

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/creatMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberservice.join(member);

        return "redirect:/";
    }

}
