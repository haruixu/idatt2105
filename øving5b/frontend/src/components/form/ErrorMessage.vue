<template>
  <p class="errorMsg">
    <slot />
  </p>
</template>

<style scoped>
  .errorMsg {
    color: red;
  }

  p {
    margin-top: 0;
    margin-bottom: 0;

  }
</style>