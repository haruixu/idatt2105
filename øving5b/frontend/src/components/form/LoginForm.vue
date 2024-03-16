<template>
  <form @submit.prevent="login">

    <Field
      id="username-field"
      label="username"
      required
    >
      <Input
        v-model=loginForm.username
        type="text"
        placeholder="username"
      />
    </Field>

    <Field
      id="password-field"
      label="password"
      required
    >
      <Input
        v-model="loginForm.password"
        type="password"
        placeholder="password"
      />
    </Field>

    <ErrorMessage
      v-if="props.invalidCredentials">
      Invalid credentials biatch
    </ErrorMessage>
    <button type="submit">
      Log in
    </button>

  </form>
</template>

<style scoped>

</style>

<script setup lang="ts">

import { ref, defineEmits, defineProps} from 'vue'
import Field from '@/components/form/Field.vue'
import Input from '@/components/form/Input.vue'
import ErrorMessage from '@/components/form/ErrorMessage.vue'

const loginForm = ref({
  username: "",
  password: "",
});

const emit = defineEmits(['login']);

const props = defineProps({
  invalidCredentials: Boolean
  });

function login() {
  emit('login', loginForm.value)
}


</script>
