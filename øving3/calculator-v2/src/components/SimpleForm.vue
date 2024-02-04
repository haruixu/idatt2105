<script setup lang="ts">
import { ref, computed } from 'vue'
import Input from '@/components/Input.vue';
import Field from "@/components/Field.vue";
import TextArea from "@/components/TextArea.vue";

import axios from 'axios';

const form = ref({
  name: "",
  email: "",
  message: ""
})

const emit = defineEmits(['submitted']);

const errors = ref({
  name: "Name cannot be empty",
  email: "Email is invalid",
  message: "Message cannot be empty",
})

//Read only properties to bind invalid state to (error)
const isNameEmpty = computed(() => form.value.name === '');
const isEmailInvalid = computed(() => validateEmail(form.value.email) === null);
const isMsgEmpty = computed(() => form.value.message === '');
const isInvalid = computed(() => isNameEmpty.value
    || isEmailInvalid.value
    || isMsgEmpty.value)

const validateEmail = (email: string) => {
  return String(email)
      .toLowerCase()
      .match(
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
};

function submit() {
  emit('submitted', form)
}

</script>

<template>
  <form
    @submit.prevent="submit()"
  >
    <Field
      id="name-field"
      label="Name"
      required
      :hasError="isNameEmpty"
      :error-msg="errors.name"
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
        :hasError="isEmailInvalid"
        :error-msg="errors.email"
    >

      <Input
          v-model="form.email"
          type="text"
          placeholder="per@pal.com"
      />

    </Field>

    <Field
        id="message"
        label="Message"
        required
        :hasError="isMsgEmpty"
        :error-msg="errors.message"
    >
      <TextArea
        v-model="form.message"
        type="text"
        placeholder="Write your message here"
      />
    </Field>

    <button
        id="submitBtn"
        :disabled="isInvalid"
        @click="submit()"
    >Submit</button>

  </form>

</template>


<style scoped>

form {
  display: flex;
  flex-direction: column;
  align-items: baseline;
}

@media (max-width: 481px) {
  Input, TextArea {
    max-width: 100px;
  }

  TextArea {
    max-width: 200px;
  }
}

Field {
  margin-top: 2px;
}

textarea {
  resize: none;
}

button {
  margin-top: 10px;
}

</style>
