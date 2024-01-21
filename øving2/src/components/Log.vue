<template>
  <div class="container">
    <ul class="list">
      <li v-for="(expression, index) in equationLog" :key="index">
        {{expression}}
      </li>
    </ul>
  </div>
</template>

<style scoped>

.container {
  min-width: 400px;
  overflow: auto;
}

.list {
  font-size: 30px;
}

</style>


<script setup lang="ts">
const props = defineProps({
  equationLog: {
    type: Array as () => string[],
    required: true
  }
})
</script>