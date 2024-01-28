<template>
  <Calculator class="calculator" @calculate="addToLog" />
  <Log class="log" :equation-log="log" />
</template>

<style scoped>
.calculator,
.log {
  /*Flex and width doesn't seem to affect the final layout, but keeping it to show possibility*/
  flex: 1; /*ensure both components take equal height */
  width: 100%; /*both components take 100% width of the parent container */
  border-radius: 3%;
  max-width: 300px;
}
</style>

<script setup lang="ts">
import Calculator from '@/components/Calculator.vue'
import Log from '@/components/Log.vue'
import { ref } from 'vue'

const log = ref([] as string[])
const addToLog = (expression: string) => {
  log.value.unshift(expression)
}
</script>
