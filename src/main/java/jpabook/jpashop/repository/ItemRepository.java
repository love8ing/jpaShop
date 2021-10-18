package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){  //jpa는 저장 전에은 id 값이 없다 , 즉 신규라는것 !
             em.persist(item);
        }
        else{
            em.merge(item);
        }
    }
//findAllById
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }


    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
