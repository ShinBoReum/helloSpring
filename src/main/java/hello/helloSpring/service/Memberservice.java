package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Memberservice {

    private final MemberRepository memberRepository;

    public Memberservice(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        /*
        //같은 이름이 있는 중복 회원x
        Optional<Member> result = memberRepository.findByName(member.getName());
        //ifPresent .. Optional의 is not null null이 아니면 진행
        //추가 orElseGet 또는 orElse 값이 있음 꺼내고 아니면 값을 집어넣어 꺼내고 ..
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        });
        memberRepository.save(member);
        */
        //하기 코드는 위 코드를 상용버전(이쁘게?) 정리
        /*
         memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
         memberRepository.save(member);
        * */
        validateDuplicateMember(member); //중복회원 검증
        /*
        * 메서드 추출 리팩토링 ( 단축키 영역 드래그 ctrl alt m)

            - 한 메서드안에 이런저런 세세한 처리가 많다면 그런 처리를 묶어서 나누고 독립된 메서드로 추출하고
              추출한 메서드에는 적절한 이름을 붙임.

            - 장황한 코드가 읽기 편해짐.

            - 장단점 : 각 메서드가 짧아지는 장점이 있는 반면 메서드 개수가 늘어남
         */
        Member save = memberRepository.save(member);
        return member.getId();

    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * ID로 회원 한명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}



















