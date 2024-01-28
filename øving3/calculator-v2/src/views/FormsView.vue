<template>
  <div class="container">
    <SimpleForm/>
  </div>
</template>

<style scoped>
  .container {
    display: flex;
    border: solid black;
    justify-content: center;
    margin: 5vh 20vh 5vh 20vh;
    padding-bottom: 5vh;
  }

  @media (max-width: 481px) {
    .container {
      margin: 5vh 0vh 5vh 0vh;

    }
  }
</style>


<script setup lang="ts">
import SimpleForm from "@/components/SimpleForm.vue";
</script>
