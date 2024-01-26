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
  min-height: 30vh;
  max-height: 35vh;
  overflow: auto;
  border: solid #00bd7e;
  padding: 2px;
}

.list {
  font-size: 30px;
}

</style>


<script setup lang="ts">
  defineProps({
  equationLog: {
    type: Array as () => string[],
    required: true
  }
})
</script>