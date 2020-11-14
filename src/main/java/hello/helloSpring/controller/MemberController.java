package hello.helloSpring.controller;

import hello.helloSpring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final Memberservice memberservice;

    @Autowired
    public MemberController(Memberservice memberservice){
        this.memberservice = memberservice;
    }
}
