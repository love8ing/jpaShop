package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    private String adminCheck;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
