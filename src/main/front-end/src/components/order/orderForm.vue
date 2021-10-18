<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>
      <div class="form-group">
        <label>주문회원</label>

        <select class="form-control" v-model="memberId" >
          <option value="">선택</option>
          <option :key="i" :value="member.id" v-for="(member, i) in memberList">{{ member.name }}</option>
        </select>

      </div>

      <div class="form-group">
        <label >상품명</label>
        <select class="form-control" v-model="itemId" >
          <option value="">선택</option>
          <option :key="i" :value="item.id" v-for="(item, i) in itemList">{{ item.name }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="count">주문수량</label>
        <input type="number" name="count" class="form-control" id="count" v-model="count"
               placeholder="주문 수량을 입력하세요">
      </div>
      <button type="submit" class="btn btn-primary" @click="SetOrder">Submit</button>
    <br/>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "orderForm",
  data: function () {
    return {
      memberList: [],
      itemList: [],
      resultList : [],
      memberId : '',
      itemId : '',
      count : ''
    }
  },
  created: function () {
    this.getList()
  },
  methods: {
    getList: function () {

      this.memberList = this.$route.query.member;
      this.itemList = this.$route.query.item;
    },

    SetOrder() {
      console.log(this.memberId);
      console.log(this.itemId);
      console.log(this.count);

      var params = new URLSearchParams();
      params.append('memberId', this.memberId);
      params.append('itemId', this.itemId);
      params.append('count', this.count);
      this.$axios.post('http://localhost:8080/orderPost', params)
          .then((res) => {
            console.log(res);
            this.$router.go(-1);
          }).catch((err)=>{
            console.log(err);
          })
    },

    changeMember() {
      alert(this.memberId);
    },
    changeItem() {
      alert(this.itemId);
    }
  }
}
</script>

<style scoped>

</style>