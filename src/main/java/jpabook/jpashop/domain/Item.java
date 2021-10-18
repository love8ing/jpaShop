package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStcokExceoption;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
    // 재고수량(stock) 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    // 재고줄이기
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock <0){
            throw new NotEnoughStcokExceoption("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
