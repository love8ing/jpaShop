<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

      <div class="form-group">
        <label th:for="name">상품명</label>
        <input type="text" v-model="name"  class="form-control"
               placeholder="이름을 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="price">가격</label>
        <input type="number" v-model="price"  class="form-control"
               placeholder="가격을 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="stockQuantity">수량</label>
        <input type="number" v-model="stockQuantity"   placeholder="수량을 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="author">저자</label>
        <input type="text" v-model="author"  class="form-control"
               placeholder="저자를 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="isbn">ISBN</label>
        <input type="text" v-model="isbn"  class="form-control"
               placeholder="ISBN을 입력하세요">
      </div>
      <button type="submit" class="btn btn-primary" @click="CreateItem">Submit</button>

    <br/>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "createItemForm",
data () {
  return{
    name : '',
    price : '',
    stockQuantity : '',
    author : '',
    isbn :''
  }
},
methods : {

  CreateItem(){

    let form = {
      name: this.name,
      price: this.price,
      stockQuantity: this.stockQuantity,
      author: this.author,
      isbn : this.isbn
    };
    console.log(form);

    this.$axios.post('http://localhost:8080/items/new'
        ,form)
        .then((res) => {

          console.log(res.data);
          this.$router.go(-1);

        }).catch((err)=>{
      console.log(err);
    })

  }

}

}
</script>

<style scoped>

</style>