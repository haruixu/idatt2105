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
import {isSubmittingSuccessful} from "@/utils/loginutils.js"
import {submissionRequest} from "@/utils/httputils.js"
import {useUserStore} from "@/stores/userstore.ts"

const users = useUserStore();

const submit = async (form) => {
  console.log("Submitting: " + form.value.name + " " + form.value.email);
  users.submitUser(form.value.name, form.value.email)

  let response = await submissionRequest(users.user);
      console.log("response: " + response.data + response.status)
      if (isSubmittingSuccessful(response)) {
        console.log("success!")
        router.push("/");
      }
}
</script>
