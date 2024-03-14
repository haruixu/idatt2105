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
import LoginForm from '@/components/LoginForm.vue'
import { ref } from 'vue'
import { useTokenStore} from '@/stores/tokenstore'
import { isLoginSuccessful } from '@/utils/loginutils'
import router from '@/router'

const isFailedLogin = ref(false);
const tokenStore = useTokenStore();
async function login(loginForm) {

  let response = await tokenStore.getTokenAndSaveInStore(loginForm)
  if (isLoginSuccessful(response)) {
      await router.push("/home")
  }
  else {
      isFailedLogin.value = true;
    }
}
</script>

