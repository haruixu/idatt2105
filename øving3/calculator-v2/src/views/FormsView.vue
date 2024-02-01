<template>
  <div class="container">
    <SimpleForm @submitted="submit"/>
  </div>
</template>

<style scoped>
  .container {
    display: flex;
    border: 1px solid rgba(0, 0, 0, 0.5);
    justify-content: center;
    margin: 5vh 20vh 5vh 20vh;
    padding-bottom: 5vh;
    border-radius: 20px;
  }

  @media (max-width: 481px) {
    .container {
      margin: 5vh 0 5vh 0;
    }
  }
</style>


<script setup lang="ts">
import SimpleForm from "@/components/SimpleForm.vue";
import axios from "axios";
import router from '@/router';
import {isLoginSuccessful} from "@/utils/loginutils.js"
import {loginRequest} from "@/utils/httputils.js"

const submit = async (value, event) => {
  console.log("Subitting: " + value.value.name + " " + value.value.email);

  let response = await loginRequest({});
      console.log("got response login")
      if (isLoginSuccessful(response)) {
        //userStore.saveUserInStore(this.username);
        console.log("sucess!")
        router.push("/");
      }

    /*axios.post('http://localhost:5173/submissions', {
    name: value.value.name,
    mail: value.value.email,
  }).then(response => {
    console.log('Response:', response.data);
    // Handle the response data here
  }).catch(error => {
    console.error('Error:', error);
    // Handle the error here
  });*/
}
</script>
