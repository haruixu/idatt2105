<script setup lang="ts">

const props = defineProps({
  for: String,
  required: Boolean
})


</script>

<template>
  <label
      class="label"
      :for="props.for"
  >
    <slot/><span
      class="star"
      v-if="props.required"
    >*</span>
  </label>

</template>

<style scoped>
  .star {
    color: red;
  }
</style>