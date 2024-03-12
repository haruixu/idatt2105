<template>
  <h1>
    This is signup view
  </h1>
  <SignupForm
    :username="errorMsg"
    @login="signup"
  ></SignupForm>
</template>

<style scoped>
</style>

<script setup lang="ts">
import { ref } from 'vue'
import { signupRequest } from '@/utils/httputils'
import { isLoginSuccessful } from '@/utils/loginutils'
import router from '@/router'
import SignupForm from '@/components/SignupForm.vue'

const errorMsg = ref("")
async function signup(loginForm) {
  let response = await signupRequest(loginForm)
  if (isLoginSuccessful(response)) {
      await router.push("/home")
  }
  else {
    // TODO: may need to change
    errorMsg.value = response.body
  }
}
</script>

