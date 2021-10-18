package jpabook.jpashop.service;

import jpabook.jpashop.controller.MemberForm;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기가 많은 서비스 로직이기에 클래스에 선언
@RequiredArgsConstructor // 파이널이 있는 애만 생성자를 만들어줌 -> Autowired -> 의존성 주입
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    @Transactional // 기본은 readOnly = false 임 -> 이렇게만 해놓으면 이 메소드는 쓰기 가능
    public Long join(Member member){

        validateDuplicateMember(member); //중복회원 검사
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        //List<Member> findMembers = memberRepository.findByName(member.getName());
        List<Member> byEmail = memberRepository.findByEmail(member.getEmail());
        if(!byEmail.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //회원 전체 조회
    public List<Member> findMemebers(){

        return memberRepository.findAll();
    }


    //단건 조회
    public Member findOnd(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name){
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }

    //기능 확장
    @Transactional
    public void updateForm(Long memberId , MemberForm memberForm){
        Member member = memberRepository.findById(memberId).get();
        member.setName(memberForm.getName());
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        member.setAddress(address);
    }

    //기능 확장
    //로그인 검증
    public Member findMemberForLogin(String inputEmail, String inputPassword){
        Member memberForLogin = memberRepository.findMemberForLogin(inputEmail, inputPassword);
        return memberForLogin;
    }
}
