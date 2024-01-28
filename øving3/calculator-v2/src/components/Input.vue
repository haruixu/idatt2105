<script setup lang="ts">
import { inject } from 'vue'

const value = defineModel()
const props = defineProps({
  id: String,
  required: Boolean,
  invalid: Boolean,
})

const field = inject("field", props);


</script>

<template>

  <input
      :id="field.id"
      v-model="value"
      :required="field.required"
      :class="field.invalid ? 'invalid' : 'valid'">
  </input>

</template>

<style scoped>
  .invalid {
    border-color: red;
  }
</style>