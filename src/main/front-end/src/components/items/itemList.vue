<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>
    <div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>상품명</th>
          <th>가격</th>
          <th>재고수량</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in itemList" v-bind:key="item">
          <td>{{item.id}}</td>
          <td>{{item.name}}</td>
          <td>{{item.price}}</td>
          <td>{{item.stockQuantity}}</td>
          <td>
            <a href="#"
                 class="btn btn-primary" role="button" v-on:click="moveUpdateItemForm(item.id)">수정</a>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div th:replace="fragments/footer :: footer"/>
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "itemList",
  data: function () {
    return {
      itemList: [],
      resultList : []
    }
  },
  created: function () {
    this.getList()
  },
  methods: {
    getList: function () {
      this.$axios.get('http://localhost:8080/items').then(response => {
        console.log('### ' + JSON.stringify(response))
        this.itemList = response.data;
      }).catch(error => {
        console.log(error)
      })
    },

    moveUpdateItemForm(itemId) {
      console.log(itemId);


      this.$axios.get('http://localhost:8080/items/' + itemId + '/edit')
          .then((res) => {
            this.resultList = res.data;
            console.log(this.resultList);
            this.$router.push({name: 'updateItemForm', query: {data: this.resultList}});
          }).catch((err)=>{
        console.log(err);
      })

    }
  }
}
</script>

<style scoped>

</style>