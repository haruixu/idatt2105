<template>
  <div id="wrapper">
    <h1>
      This is login view
    </h1>
    <LoginForm
      :invalid-credentials="isFailedLogin"
      @login="login"
    ></LoginForm>
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
      console.log("else")
      isFailedLogin.value = true;
    }
  } catch (e) {
    console.log("catch")
    isFailedLogin.value = true
    console.log(e)
  }
}

</script>

