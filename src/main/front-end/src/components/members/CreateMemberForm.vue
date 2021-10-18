<template>
<div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

          method="post">
        <div class="form-group">
            <label th:for="name">이름</label>
            <input type="text" v-model="name"  class="form-control"
                   placeholder="이름을 입력하세요"
                   th:class="${#fields.hasErrors('name')}? 'form-control fieldError' : 'form-control'">
            <!-- <p th:if="${#fields.hasErrors('name')}"
               th:errors="*{name}">Incorrect date</p> -->
        </div>

        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input
                type="email" class="form-control" id="exampleInputEmail1"  v-model="email"
                name="email" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll
                never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input
                type="password" class="form-control" id="exampleInputPassword1"  v-model="password"
                name="password" placeholder="Password">
        </div>


        <div class="form-group">
            <label th:for="city">도시</label>
            <input type="text"  class="form-control"  v-model="city"
                   placeholder="도시를 입력하세요">
        </div>
        <div class="form-group">
            <label th:for="street">거리</label>
            <input type="text"  class="form-control"  v-model="street"
                   placeholder="거리를 입력하세요">
        </div>
        <div class="form-group">
            <label th:for="zipcode">우편번호</label>
            <input type="text"  class="form-control"  v-model="zipcode"
                   placeholder="우편번호를 입력하세요">
        </div>
        <div class="checkbox mb-3">
            <label>

                <input type="checkbox"  v-model="adminChk" name="adminCheck" value='1' id="adminCheck"/>

                관리자 계정 유무
            </label>
        </div>
        <button type="submit" class="btn btn-primary" @click="join">가입</button>

    <br/>
    <div th:replace="fragments/footer :: footer" />
    </div> <!-- /container -->
</template>

<script>
export default {
  data: function () {
    return {

      name : '',
      email : '',
      password : '',
      city : '',
      street : '',
      zipcode : '',
      adminCheck :''
    }
  },
  methods :{
    join(){

      let form = {
        email: this.email,
        name: this.name,
        password: this.password,
        city: this.city,
        street: this.street,
        zipcode: this.zipcode,
        adminCheck: this.adminCheck
      };

      this.$axios.post('http://localhost:8080/members/new'
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

<style>

</style>