<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

      <!-- id -->
      <input type="hidden"  v-model="id" />
      <div class="form-group">
        <label th:for="name">상품명</label>
        <input type="text"  class="form-control" v-model="name"
               placeholder="이름을 입력하세요" />
      </div>
      <div class="form-group">
        <label th:for="price">가격</label>
        <input type="number"  class="form-control" v-model="price"
               placeholder="가격을 입력하세요" />
      </div>
      <div class="form-group">
        <label th:for="stockQuantity">수량</label>
        <input type="number" v-model="stockQuantity" class="form-
control" placeholder="수량을 입력하세요" />
      </div>
      <div class="form-group">
        <label th:for="author">저자</label>
        <input type="text" v-model="author"  class="form-control"
               placeholder="저자를 입력하세요" />
      </div>
      <div class="form-group">
        <label th:for="isbn">ISBN</label>
        <input type="text" v-model="isbn"  class="form-control"
               placeholder="ISBN을 입력하세요" />
      </div>
      <button type="submit" class="btn btn-primary" @click="updateItem">Submit</button>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "updateItemForm"
  ,data () {
    return{
      id: '',
      name : '',
      price : '',
      stockQuantity : '',
      author : '',
      isbn :'',
      resultData : []
    }
  },
  methods : {
    initialize : function() {
      //초기화로직
      this.id = this.$route.query.data.id;
      this.name = this.$route.query.data.name;
      this.price = this.$route.query.data.price;
      this.stockQuantity = this.$route.query.data.stockQuantity;
      this.author = this.$route.query.data.author;
      this.isbn = this.$route.query.data.isbn;
    },
    updateItem(){

      let form = {
        id: this.id,
        name: this.name,
        price: this.price,
        stockQuantity: this.stockQuantity,
        author: this.author,
        isbn : this.isbn
      };
      console.log(form);

      /*
      var params = new URLSearchParams();
      params.append('id', id);
      */
      this.$axios.post('http://localhost:8080/items/' + this.id + '/edit'
          ,form)
          .then((res) => {
            this.resultData = res.data;
            console.log(this.resultData[0]);
            this.$router.go(-1);

          }).catch((err)=>{
        console.log(err);
      })

    }

  },

  mounted: function () {

    this.initialize();
  }

}
</script>

<style scoped>

</style>