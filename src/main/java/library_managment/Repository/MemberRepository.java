package library_managment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library_managment.Model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}