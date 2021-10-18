package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //** 이렇게 해놓으면 해당 엔티티는 함부로 생성 new 할수 없다!
    protected OrderItem() {
    }

    //생성메소드
    public static OrderItem createOrderItem(Item item, int orderPrice , int count){
        OrderItem orderItem =  new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 주문을 했으니 재고를 깍는다.

        return orderItem;
    }

    //비즈니스 로직
    //재고수량을 원복해준다.
    public void cancel() {
        getItem().addStock(count);
    }

    // 조회 로직
    // 주문상품 전체 가격 조회
    // 총금액은 상품가격 * 주문 갯수
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
