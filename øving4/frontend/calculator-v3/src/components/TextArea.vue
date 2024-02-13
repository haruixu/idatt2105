<script setup lang="ts">
import { inject } from 'vue'

const text = defineModel()
const props = defineProps({
  id: String,
  required: Boolean,
  invalid: Boolean,
})

const field = inject("field", props);

</script>

<template>

  <textarea
      :id="field.id"
      v-model="text"
      :required="field.required"
      :class="field.invalid ? 'invalid' : 'valid'"
  >
  </textarea>

</template>

<style scoped>
.invalid {
  border-color: red;
}

@media (max-width: 480px){
  textarea {
    font-family: "Arial",serif;
    width: 10vh;
    height: 200px;
  }
}

textarea {
  font-family: "Arial",serif;
  width: 50vh;
  height: 200px;
}
</style>