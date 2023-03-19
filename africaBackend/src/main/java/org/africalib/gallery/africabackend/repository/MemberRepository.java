package org.africalib.gallery.africabackend.repository;

import org.africalib.gallery.africabackend.entity.Item;
import org.africalib.gallery.africabackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);

}
