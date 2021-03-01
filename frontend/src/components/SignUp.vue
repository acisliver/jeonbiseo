<template>
  <v-container class="fill-height" style="max-width: 700px">
    <v-row justify="center">
      <v-col
          cols="12"
          sm="10"
          md="8"
          lg="6"
      >
        <v-card>
          <v-card-text>
            <v-text-field
                v-model="userName"
                :rules="[() => !!userName || '필수입력입니다.']"
                label="이름"
                required
            ></v-text-field>
            <v-text-field
                v-model="email"
                :rules="[
                () => !!email || '필수입력입니다.']"
                label="이메일"
                maxlength="25"
                counter="25"
                required
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="[() => !!password || '필수입력입니다.']"
                label="비밀번호"
                maxlength="25"
                counter="25"
                required
            ></v-text-field>
            <v-text-field
                v-model="year"
                :rules="[() => !!year || '필수입력입니다.']"
                label="년"
                maxlength="4"
                counter="4"
                required
            ></v-text-field>
            <v-autocomplete
                v-model="month"
                :rules="[() => !!month || '필수입력입니다.']"
                :items="month_list"
                label="월"
                required
            ></v-autocomplete>
            <v-text-field
                v-model="day"
                :rules="[() => !!day || '필수입력입니다.']"
                label="일"
                maxlength="2"
                counter="2"
                required
            ></v-text-field>
          </v-card-text>

          <v-divider class="mt-12"></v-divider>

          <v-card-actions>
            <v-btn
                text
                @click="$router.back()"
            >
              Cancel
            </v-btn>
            <v-spacer></v-spacer>

            <v-btn
                color="primary"
                text
                @click="signup_summit({userName, email, password})"
            >
              Submit
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions } from 'vuex'

export default {


  name: "SignUp",
  data () {
    return {
      userName: null,
      email: null,
      password: null,
      year: null,
      month: null,
      day: null,
      month_list: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
      rules: [v => v.length <= 25 || 'Max 25 characters'],
      // wordsRules: [v => v.trim().split(' ').length <= 5 || 'Max 25 words'],
    }
  },
  methods: {
    ...mapActions(['signup_summit'])
  }
}
</script>

<style scoped>

</style>
