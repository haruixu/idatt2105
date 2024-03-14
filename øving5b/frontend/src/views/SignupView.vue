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
import SignupForm from '@/components/SignupForm.vue'
import { useTokenStore } from '@/stores/tokenstore'

const errorMsg = ref("")
const tokenStore = useTokenStore();
async function signup(loginForm) {
  const data = await tokenStore.getToken(loginForm)
  if (tokenStore.jwtToken) {
    alert("Created user")
  }
  else {
    // TODO: may need to change
    errorMsg.value = data;
  }
}
</script>

