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
import SignupForm from '@/components/form/SignupForm.vue'
import { useTokenStore } from '@/stores/tokenstore'
import router from '@/router'

const errorMsg = ref("")
const tokenStore = useTokenStore();
async function signup(loginForm) {
  console.log("Recevied emit, now signing up")
  const data = await tokenStore.getToken(loginForm)
  if (tokenStore.jwtToken) {
    alert("Created user")
    await router.push("/login")
  }
  else {
    // TODO: may need to change
    errorMsg.value = data;
  }
}
</script>

