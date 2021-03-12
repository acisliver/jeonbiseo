<template>
  <v-app-bar app flat>
    <v-container>

      <v-row align="center">
        <v-app-bar-title type="button" @click="$router.push({ name: 'Home'})"><span style="color: #1976d2">전</span>비서</v-app-bar-title>
        <v-spacer />
        <v-toolbar-items class="hidden-sm-and-down">
          <router-link to="/">홈</router-link>
<!--          <router-link to="/about">About</router-link>-->
          <router-link to="/compare">비교</router-link>
          <router-link to="/recommand">추천</router-link>
          <router-link to="/board">게시판</router-link>
          <router-link v-if="!isLogin" to="/login" >로그인</router-link>
          <router-link v-if="!isLogin" to="/signup">회원가입</router-link>
          <v-menu offset-y v-if="isLogin">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  text
                  dark
                  v-bind="attrs"
                  v-on="on"
                  icon
              >
                <v-icon color="green lighten-2">mdi-dialpad</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title>
                  <router-link :to="{
                    name: 'MyPage',
                    params: {
                      username: this.$store.state.userInfo.userName
                    }
                }">마이페이지</router-link></v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title
                    @click="$store.dispatch('logoutAction')"
                ><router-link :to="{name: 'Home'}">로그아웃</router-link></v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar-items>
      </v-row>

    </v-container>
  </v-app-bar>
</template>

<script>
import{ mapState, mapActions } from 'vuex'

export default {
  name: "AppBar",
  data() {
    return{
    }
  },
  computed: {
    ...mapState(['isLogin', 'isLoginError'])
  },
  methods: {
    ...mapActions(['logoutAction'])
  }
}
</script>

<style lang="scss">

.v-toolbar__content{
  padding: 30px;
  button{
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;
    padding: 10px;
  }
  a {
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;
    padding: 10px;

    &.router-link-exact-active {
       color: #1976d2;
     }
  }
}

//마이페이지
#list-item-23 > div > a{
  text-decoration: none;
  font-weight: bold;
  color: #2c3e50;
  padding: 10px;

  &.router-link-exact-active {
    color: #1976d2;
  }
}

//로그아웃
#list-item-25 > div > a{
  text-decoration: none;
  font-weight: bold;
  color: #2c3e50;
  padding: 10px;

}
</style>
