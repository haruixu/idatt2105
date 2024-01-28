<script setup lang="ts">

import { ref, provide, computed } from 'vue'
import Label from '@/components/Label.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import HelperMessage from '@/components/HelperMessage.vue';


const props = defineProps({
  id: String,
  label: String,
  required: Boolean,
  error: String,
  help: String,
});

provide('field', computed(() => {
  return {
    ...props,
    invalid: !!props.error
  }
}));

</script>

<template>
  <div class="container">

    <Label
        :for="props.id"
        v-if="props.label"
        :required="props.required"
    > {{ props.label }}</Label>

    <slot />

    <ErrorMessage
        v-if="props.error">
      {{ props.error }}
    </ErrorMessage>

    <HelperMessage
        v-if="props.help">
      {{ props.help }}
    </HelperMessage>

  </div>
</template>

<style scoped>

</style>