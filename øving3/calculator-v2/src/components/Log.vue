<template>
  <div class="container">
    <label>Log</label>
    <ul class="list">
      <li v-for="(expression, index) in equationLog" :key="index">
        {{expression}}
      </li>
    </ul>
  </div>
</template>

<style scoped>

label {
  position: relative;
  left: 5px;
  font-size: 20px;
}
.container {
  min-height: 30vh;
  max-height: 35vh;
  overflow: auto;
  border: solid black;
  padding: 2px;
}

.list {
  font-size: 20px;
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