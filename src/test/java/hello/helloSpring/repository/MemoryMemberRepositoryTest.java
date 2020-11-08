package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*; //Assertions. 을 안쓰고 그 이하만 쓰면 됨

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //단위 test가 끝날때 마다 결과를 지워줘야함 단위 테스트는 서로 의존적이지 않아야함.
    @AfterEach
    public void afterEach() {
        repository.clearMemory();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("tlsqhfma");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        //검증 junit member 가 result 랑 같니??
        //Assertions.assertEquals(member, result);
        //assertj 좀더 가독성이 좋은 느낌??
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);


        Member result = repository.findByName("Spring").get();

        System.out.println(result.getName());
        System.out.println(member.getName());
        assertThat(result.getName()).isEqualTo(member.getName());



    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("Spring3");
        repository.save(member3);

        Member result = repository.findAll().get(0);

        List<Member> listResult = repository.findAll();

        System.out.println(result.getName());
        System.out.println(listResult.size());

        assertThat("Spring").isEqualTo(result.getName());
        assertThatObject(3).isEqualTo(listResult.size());



    }

}
