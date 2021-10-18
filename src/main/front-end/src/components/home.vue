<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader" />
    <div class="jumbotron">
      <h1>HELLO SHOP</h1>
      <p class="lead">회원 기능</p>


      <v-container fluid fill-height>
        <v-layout align-center>


          <v-flex xs12 sm8 md4>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn depressed large color="blue white--text" @click="moveUpdateFormMember">내정보수정</v-btn>
            </v-card-actions>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn depressed large color="green white--text" @click="moveItemList">상품 목록</v-btn>
            </v-card-actions>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn depressed large color="purple white--text" @click="moveOrder">상품 주문</v-btn>
              <v-btn depressed large color="purple white--text" @click="moveOrderList">주문 내역</v-btn>
            </v-card-actions>


          </v-flex>
        </v-layout>
      </v-container>

<!--      <p>-->
<!--        <a href="#" v-on:click="moveUpdateFormMember"-->
<!--           class="btn btn-primary" role="button">내정보 수정</a>-->
<!--      </p>-->
<!--      <p class="lead">상품 기능</p>-->
<!--      <p>-->

<!--        <a class="btn btn-lg btn-dark" v-on:click="moveItemList">상품 목록</a>-->
<!--      </p>-->
<!--      <p class="lead">주문 기능</p>-->
<!--      <p>-->
<!--        <a class="btn btn-lg btn-info" v-on:click="moveOrder">상품 주문</a>-->
<!--        <a class="btn btn-lg btn-info" v-on:click="moveOrderList">주문 내역</a>-->
<!--      </p>-->

    </div>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  data () {
    return{
      PK_id : ''
    }
  },
  /*
  created() {
    this.PK_id = this.$store.state.PK_id;
  },
*/
  methods :{
    moveUpdateFormMember() {

      console.log("PK_id : "  + this.$store.state.PK_id);

      var params = new URLSearchParams();
      params.append('id', this.$store.state.PK_id);
      this.$axios.get('http://localhost:8080/members/' + this.$store.state.PK_id + '/MyUpdate'
          ,params)
          .then((res) => {
            this.resultData = res.data;
            console.log(this.resultData[0]);
            this.$router.push({name: 'UpdateMemberForm', query: {data: this.resultData[0]}});
          }).catch((err)=>{
        console.log(err);
      })
    },
  moveItemList() {
    this.$router.push({name: 'itemList'});
  }
    ,

    moveOrder() {
      /*
      var params = new URLSearchParams();
      params.append('id', this.PK_id);
      */
      this.$axios.get('http://localhost:8080/order')
          .then((res) => {
            this.resultData = res.data;
            console.log(this.resultData);
            this.$router.push({name: 'orderForm', query: {member: this.resultData[0], item : this.resultData[1]}});
          }).catch((err)=>{
        console.log(err);
      })
    },

    moveOrderList() {
      this.$router.push({name: 'orderList'});
    }

  }

}
</script>

<style>

</style>