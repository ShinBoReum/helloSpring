package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //test 시작전 트렌젝션을 시작하고 끝난 후 롤백 이전의 afterEach
class MemberserviceIntegrationTest {

    @Autowired Memberservice memberservice;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();

        member.setName("Spring");


        //when
        Long saveId = memberservice.join(member);

        //then 우리가 저장한게 리포지토리에 있는게 맞아???
        Member result = memberservice.findOne(saveId).get();

        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void 중복회원검증() {
        //give
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");


        //when
      memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberservice.join(member2);
            fail("예외 미발생");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

}