<template>
  <h1>
    This is signup view
  </h1>
  <SignupForm
    :username="errorMsg"
    @signup="signup"
  ></SignupForm>
</template>

<style scoped>
</style>

<script setup lang="ts">
import { ref } from 'vue'
import SignupForm from '@/components/form/SignupForm.vue'
import { useTokenStore } from '@/stores/tokenstore'
import { signupRequest } from '@/utils/httputils'
import router from '@/router'

const errorMsg = ref("")
const tokenStore = useTokenStore();
async function signup(loginForm) {
  try {

    console.log("Recevied emit, now signing up")
    console.log(loginForm)
    const response = await tokenStore.getToken(loginForm)
    if (response.status === 200) {
      console.log("SDuccess")
      //await router.push("/login")
    }
  } catch (e) {
    errorMsg.value = e.response.data
  }
}
</script>

