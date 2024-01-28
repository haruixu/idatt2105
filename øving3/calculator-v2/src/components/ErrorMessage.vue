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
    margin-top: 0px;
    margin-bottom: 0px;

  }
</style>