<template>
  <div class="wrapper">
    <Calculator class="calculator" @calculate="updateLog"/>
    <Log class="log" :equation-log="log" />
  </div>

</template>

<style scoped>

.wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  top: 10vh;
}

.calculator, .log {
  /*Flex and width doesn't seem to affect the final layout, but keeping it to show possibility*/
  flex: 1; /*ensure both components take equal height */
  width: 100%; /*both components take 100% width of the parent container */
  border-radius: 3%;
  max-width: 300px;
}
</style>

<script setup lang="ts">
import Calculator from '@/components/calculator/Calculator.vue'
import Log from '@/components/calculator/Log.vue'
import { ref } from 'vue'
import { calculationsRequest } from '@/utils/httputils'

const log = ref([] as string[]);

const updateLog = async (token: string) => {
  try {
    let response = await calculationsRequest(token);
    log.value = toList(response.data)
  } catch (e) {
    console.log(e)
  }
};

const toList = (content: Array) => {
  const list = []

  content.forEach(element => {
    list.push(element.expression + " = " + element.answer);
  })

  return list;
}

</script>