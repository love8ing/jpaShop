package jpabook.jpashop.controller;


import jpabook.jpashop.api.OrderApiController;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public List<Object> createForm(Model model){

        List<Object> resultAll = new ArrayList<>();

        List<Member> memebers = memberService.findMemebers();
        List<Item> items = itemService.findItem();

        /*
        model.addAttribute("members", memebers);
        model.addAttribute("items", items);
        return "order/orderForm";
        */
        resultAll.add(memebers);
        resultAll.add(items);
        return resultAll;
    }

    @PostMapping("/orderPost")
    public String order(HttpServletRequest request)
    {
        //여러상품도 추가할 수 있게 고쳐보자 !

        Long memberId = Long.valueOf(request.getParameter("memberId"));
        Long itemId = Long.valueOf(request.getParameter("itemId"));
        int count = Integer.parseInt(request.getParameter("count"));
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @GetMapping("/ordersInit")
    public Map<String, String> orderListInit( Model model){

        Map<String, String> ResultMap = new HashMap<String, String>();

        ResultMap.put("ORDER" , OrderStatus.ORDER.toString());
        ResultMap.put("CANCEL" , OrderStatus.CANCEL.toString());
        return ResultMap;
    }

    @PostMapping("/orders")
    public List<OrderDto> orderList(HttpServletRequest request){

        OrderSearch orderSearch = new OrderSearch();

        String memberName = request.getParameter("memberName");
        orderSearch.setMemberName(memberName);

        String orderStatus = request.getParameter("orderStatus");
        if("ORDER".equals(orderStatus)){
            orderSearch.setOrderStatus(OrderStatus.ORDER);
        }
        else if("CANCEL".equals(orderStatus)){
            orderSearch.setOrderStatus(OrderStatus.CANCEL);
        }


        List<Order> orders = orderService.findOrders(orderSearch);

        //orders를 OrderDto 로 변환!
        List<OrderDto> result = orders.stream() //스트림을 써서
                .map(o -> new OrderDto(o))      //map은 변환, orders(o)를 -> new OrderDto에 (o)를 넘겨
                .collect(toList());

        return result;
    }

    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;//상품 명
        private int orderPrice; //주문 가격
        private int count; //주문 수량
        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
