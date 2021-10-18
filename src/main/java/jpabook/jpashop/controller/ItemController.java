package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }
    @PostMapping(value = "/items/new")
    public String create(@RequestBody BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);
        return "redirect";
    }

    @GetMapping("/items")
    public List<Item> list(Model model){
        List<Item> items = itemService.findItem();
        //model.addAttribute("items", items);
        //return "items/itemList";

        return items;
    }

    @GetMapping("items/{itemId}/edit")
    public BookForm updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId((item.getId()));
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());


        //model.addAttribute("form",form);
        //return "items/updateItemForm";
        return form;
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@RequestBody BookForm form){

        /*
        준영속 엔티티?
        영속성 컨텍스트가 더는 관리하지 않는 엔티티를 말한다.
        (여기서는 itemService.saveItem(book) 에서 수정을 시도하는 Book 객체다. Book 객체는 이미 DB
        에 한번 저장되어서 식별자가 존재한다. 이렇게 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준
        영속 엔티티로 볼 수 있다.

        준영속 엔티티를 수정하는 2가지 방법

        변경 감지 기능 사용
        병합( merge ) 사용
         */


        /*

        컨트롤러에서 어설프게 엔티티를 생성하지 마세요.
        트랜잭션이 있는 서비스 계층에 식별자( id )와 변경할 데이터를 명확하게 전달하세요.(파라미터 or dto)
        트랜잭션이 있는 서비스 계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경하세요.
        트랜잭션 커밋 시점에 변경 감지가 실행됩니다

        Book book = new Book();

        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        */

        itemService.updateItem(form.getId(), form.getName() , form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}
