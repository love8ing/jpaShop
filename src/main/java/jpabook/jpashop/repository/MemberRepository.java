package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);

    List<Member> findByEmail(String email);

    @Query("select m from Member m where email = :email and password = :Password")
    Member findMemberForLogin(@Param("email") String inputEmail , @Param("Password") String inputPassword);

}
