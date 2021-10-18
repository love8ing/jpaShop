<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>
    <div>
      <div>

          <div class="form-group mb-2">
            <input type="text" v-model="memberName"  placeholder="회원명"/>
          </div>
          <div class="form-group mx-sm-1 mb-2">

            <select class="form-control" v-model="orderStatus" >
              <option value="">선택</option>
              <option :key="i" :value="orderstat" v-for="(orderstat, i) in orderstateList">{{ orderstat }}</option>
            </select>

          </div>
          <button type="submit" class="btn btn-primary mb-2" v-on:click="searchlist">검색</button>

      </div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>회원명</th>
          <th>대표상품 이름</th>
          <th>대표상품 주문가격</th>
          <th>대표상품 주문수량</th>
          <th>상태</th>
          <th>일시</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="order in orderLists" v-bind:key="order">
          <td>{{order.id}}</td>
          <td>{{order.name}}</td>
          <td>{{order.orderItems[0].itemName}}</td>
          <td>{{order.orderItems[0].orderPrice}}</td>
          <td>{{order.orderItems[0].count}}</td>
          <td>{{order.orderStatus}}</td>
          <td>{{order.orderDate}}</td>

<!--          <td>-->
<!--            <div v-if="orderstat == 'ORDER'">-->
<!--              <a href="#"-->
<!--                 class="btn btn-primary" role="button" v-on:click="cancelOrder(item.id)">CANCEL</a>-->
<!--            </div>-->
<!--            <div v-else> a </div>-->
<!--          </td>-->

        </tr>


<!--        <tr th:each="item : ${orders}">-->
<!--          <td th:text="${item.id}"></td>-->
<!--          <td th:text="${item.member.name}"></td>-->
<!--          <td th:text="${item.orderItems[0].item.name}"></td>-->
<!--          <td th:text="${item.orderItems[0].orderPrice}"></td>-->
<!--          <td th:text="${item.orderItems[0].count}"></td>-->
<!--          <td th:text="${item.status}"></td>-->
<!--          <td th:text="${item.orderDate}"></td>-->
<!--          <td>-->
<!--            <a th:if="${item.status.name() == 'ORDER'}" href="#"-->
<!--               th:href="'javascript:cancel('+${item.id}+')'"-->
<!--               class="btn btn-danger">CANCEL</a>-->
<!--          </td>-->
<!--        </tr>-->

        </tbody>
      </table>
    </div>
    <div th:replace="fragments/footer :: footer"/>
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "orderList",
  data: function () {
    return {

      orderLists: [],
      orderstateList : [],

      memberName : '',
      orderStatus : ''

    }
  },
  created: function () {
    this.getList()
  },
  methods: {
    getList: function () {
      this.orderstateList = this.$route.query.orderstateList;
    },

    searchlist() {
      console.log(this.memberName);
      console.log(this.orderStatus);


      var params = new URLSearchParams();
      params.append('memberName', this.memberName);
      params.append('orderStatus', this.orderStatus);

      this.$axios.post('http://localhost:8080/orders' ,params)
          .then((res) => {
            console.log('### ' + JSON.stringify(res))
            this.orderLists = res.data;
          }).catch((err)=>{
            console.log(err);
          })
    },

    cancelOrder(orderId) {
      console.log(orderId);

      this.$axios.get('http://localhost:8080/orders/' + orderId + '/cancel')
          .then((res) => {
            console.log(res.data);
            this.$router.go(0);
          }).catch((err)=>{
        console.log(err);
      })

    },


  }
}
</script>

<style scoped>

</style>