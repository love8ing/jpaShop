package jpabook.jpashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired OrderRepository orderRepository;
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Test
    public void test1(){
        System.out.println("AAAAAAAaa :");
        List<Order> resultList = em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();


        for (Order order : resultList) {
            System.out.println("AAAAAAAaa :  " + order);
        }

        queryFactory = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;
        
        List<Order> result = queryFactory
                .select(order)
                .from(order)
                .join(order.member, member)
                //.join(order.delivery, delivery).fetchJoin()
                .fetch();

        for (Order order1 : result) {
            System.out.println("BBBBBBBBB :  " + order1);
        }

    }

    @Test
    public void test2(){
        List<Order> allWithMemberDeliveryByQueryDsl = orderRepository.findAllWithMemberDeliveryByQueryDsl();
        for (Order order : allWithMemberDeliveryByQueryDsl) {
            System.out.println("BBBBBBBBB :  " + order);
        }
    }

}