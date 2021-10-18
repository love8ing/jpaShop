package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJPARepository extends JpaRepository<Item , Long> {

}
