<template>
  <h1>
    This is login view
  </h1>
  <LoginForm
    :invalid-credentials="isFailedLogin"
    @login="login"
  ></LoginForm>
</template>

<style scoped>
</style>

<script setup lang="ts">
import LoginForm from '@/components/form/LoginForm.vue'
import { ref } from 'vue'
import { useTokenStore} from '@/stores/tokenstore'
import router from '@/router'

const isFailedLogin = ref(false);
const tokenStore = useTokenStore();
async function login(loginForm)
{
  console.log(loginForm)
  try {
    await tokenStore.getTokenAndSaveInStore(loginForm)
    if (tokenStore.state.jwtToken) {
      await router.push("/")
    }
    else {
      isFailedLogin.value = true;
    }
  } catch (e) {
    console.log(e)
  }
}

</script>

