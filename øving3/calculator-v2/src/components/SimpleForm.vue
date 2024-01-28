<script setup lang="ts">
import { ref } from 'vue'
import Input from '@/components/Input.vue';
import Field from "@/components/Field.vue";
import TextArea from "@/components/TextArea.vue";

const form = ref({
  name: "",
  email: "",
  message: ""
})

const errors = ref({})

async function submit() {
  errors.value = {
    name: "Name is invalid",
    email: "Email is invalid",
    message: "Message is invalid",
  }
}

</script>

<template>
  <form
    @submit.prevent="submit"
  >
    <Field
      id="name-field"
      label="Name"
      required
      :error="errors.name"
    >

      <Input
          v-model="form.name"
          type="text"
          placeholder="Name"
      />

    </Field>

    <Field
        id="email-field"
        label="Email"
        required
        :error="errors.email"
    >

      <Input
          v-model="form.email"
          type="text"
          placeholder="Email"
      />

    </Field>

    <Field
        id="message"
        label="Message"
        required
        :error="errors.message"
    >
      <TextArea
        v-model="form.message"
        type="text"
        placeholder="Write your message here"
      />

    </Field>

    <button>Submit</button>

  </form>

</template>


<style scoped>


textarea {
  resize: none;
}
</style>
