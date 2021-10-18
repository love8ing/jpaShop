package jpabook.jpashop.dslTest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class dslTest {
    @Id
    @GeneratedValue
    private Long id;
}
