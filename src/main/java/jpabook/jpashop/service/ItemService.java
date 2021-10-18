package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemJPARepository;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemJPARepository itemJPARepository;

    @Transactional
    public void saveItem(Item item){
        itemJPARepository.save(item);
    }

    //변경감지를 이용한 수정 방법 -> 키값으로 엔티티를 새로 조회해서 거기에 변경감지를 사용해 update 침
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Optional<Item> byId = itemJPARepository.findById(itemId);
        Item findItem = byId.orElseThrow(NullPointerException::new);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    public List<Item> findItem(){
        return itemJPARepository.findAll();
    }

    public Item findOne(Long itemId){
        Optional<Item> byId = itemJPARepository.findById(itemId);
        Item findItem = byId.orElseThrow(NullPointerException::new);
        System.out.println("AAA");
        return findItem;
        //return itemJPARepository.findById(itemId).get();
    }
}
