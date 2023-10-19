package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 생성자를 통한 의존성 주입
    private final MemberService memberService;

    //MemberController가 생성될 때 스프링빈에 등록되어 있는 memberService를 가져다 넣어줌 (의존관계 주입)
    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    /*
    * 회원 웹 기능
    * */
    @GetMapping("members/new")
    public String crweateForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members =  memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
