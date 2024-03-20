<template>
  <div id="wrapper">
    <h1>
      This is signup view
    </h1>
    <SignupForm
      :username="errorMsg"
      @signup="signup"
    ></SignupForm>
  </div>
</template>

<style scoped>
#wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  align-items: center;
}
h1 {
  display: flex;
  justify-content: center;
}
</style>

<script setup lang="ts">
import { ref } from 'vue'
import SignupForm from '@/components/form/SignupForm.vue'
import { useTokenStore } from '@/stores/tokenstore'
import router from '@/router'

const errorMsg = ref("")
const tokenStore = useTokenStore();
async function signup(loginForm) {
  try {

    console.log("Recevied emit, now signing up")
    console.log(loginForm)
    await tokenStore.getToken(loginForm)
    console.log("SDuccess")
    await router.push("/login")
  } catch (e) {
    errorMsg.value = e.response.data
  }
}
</script>

