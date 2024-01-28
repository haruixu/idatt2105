<script setup lang="ts">
import TheWelcome from '../components/TheWelcome.vue'
import {useUserStore} from "@/stores/userstore";

const userStore = useUserStore();
</script>

<template>
  <main>
    <h1>This is home</h1>
    <h1>This is homeThis is home</h1>
    <h1>This is homeThis is homeThis is home</h1>
    <h1>This is homeThis is homeThis is homeThis is home</h1>
    <h1>This is homeThis is homeThis is homeThis is homeThis is homeThis is home</h1>
    <h1> {{userStore.users}}</h1>

    <!--<TheWelcome /> -->
  </main>
</template>

<style scoped>
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
</style>