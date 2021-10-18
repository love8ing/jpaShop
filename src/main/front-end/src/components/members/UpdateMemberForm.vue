<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

      <div class="form-group">

        <label th:for="name">이름</label>
        <input type="text"  class="form-control" v-model="name"
               placeholder="이름을 입력하세요"
               >

      </div>


      <div class="form-group">
        <label th:for="city">도시</label>
        <input type="text"  class="form-control" v-model="city"
               placeholder="도시를 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="street">거리</label>
        <input type="text"  class="form-control" v-model="street"
               placeholder="거리를 입력하세요">
      </div>
      <div class="form-group">
        <label th:for="zipcode">우편번호</label>
        <input type="text"  class="form-control" v-model="zipcode"
               placeholder="우편번호를 입력하세요">
      </div>

      <button type="submit" class="btn btn-primary"  @click="updateMember">Submit</button>
    <br/>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "UpdateMemberForm"
  ,data () {
    return{
      name : '',
      city : '',
      street : '',
      zipcode : '',
      adminCheck :''
    }
  },
  methods : {
    initialize : function() {
      //초기화로직

      this.name = this.$route.query.data.name;
      this.city = this.$route.query.data.city;
      this.street = this.$route.query.data.street;
      this.zipcode = this.$route.query.data.zipcode;
      this.adminCheck = this.$route.query.data.adminCheck;
    },
    updateMember(){

      let form = {
        name: this.name,
        city: this.city,
        street: this.street,
        zipcode: this.zipcode,
        adminCheck : this.adminCheck
      };
      console.log(form);

      const id = this.$store.state.PK_id;
      /*
      var params = new URLSearchParams();
      params.append('id', id);
      */
      this.$axios.post('http://localhost:8080/members/' + id + '/MyUpdate'
          ,form)
          .then((res) => {
            this.resultData = res.data;
            console.log(this.resultData[0]);
            if(this.resultData[0] == "home_admin") {
              this.$router.push({name: 'home_admin'});
            }
            else if(this.resultData[0]== 'home'){
              this.$router.push({name: 'home'});
            }

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