<script setup lang="ts">

import { provide, computed } from 'vue'
import Label from '@/components/Label.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import HelperMessage from '@/components/HelperMessage.vue';


const props = defineProps({
  id: String,
  label: String,
  required: Boolean,
  hasError: String,
  errorMsg: String,
  help: String,
});

provide('field', computed(() => {
  return {
    ...props,
    invalid: props.hasError
  }
}));

</script>

<template>

    <Label
        :for="props.id"
        v-if="props.label"
        :required="props.required"
    > {{ props.label }}</Label>

    <slot />

    <ErrorMessage
        v-if="props.hasError">
      {{ props.errorMsg }}
    </ErrorMessage>

    <HelperMessage
        v-if="props.help">
      {{ props.help }}
    </HelperMessage>
</template>

<style scoped>

  label, slot {
    padding-top: 20px;
    margin-top: 10px;
  }

</style>