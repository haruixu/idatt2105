<template>
  <form @submit.prevent="signup">

    <Field
      id="username-field"
      label="username"
      required
      :has-error="!!errorMsg.username"
      :error-msg="errorMsg.username"
    >
      <Input
        v-model=signupForm.username
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
        v-model="signupForm.password"
        type="password"
        placeholder="password"
      />
    </Field>

    <button type="submit">
      Sign up
    </button>

  </form>
</template>

<style scoped>

</style>

<script setup lang="ts">

import { ref, defineProps, defineEmits} from 'vue'
import Field from '@/components/form/Field.vue'
import Input from '@/components/form/Input.vue'

const signupForm = ref({
  username: "",
  password: "",
});

const errorMsg = defineProps({
  username: String
})

const emit= defineEmits(['signup']);

function signup() {
  console.log("Emitting signup: ")
  console.log(signupForm)
  emit('signup', signupForm.value)
}

</script>
