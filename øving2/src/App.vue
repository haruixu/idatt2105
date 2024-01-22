<template>
    <Calculator class="calculator" @calculate="addToLog"/>
    <Log class="log" :equation-log="log" />
</template>

<style scoped>
  .wrapper {
    display: flex;
    max-width: 500px;
    justify-content: center;
    align-content: center;
    background-color: gray ;
    border-radius: 2%;
  }

  .calculator, .log {
    flex: 1; /*ensure both components take equal height */
    width: 100%; /*both components take 100% width of the parent container */
    border-radius: 5%;
  }

  .log {
    min-height: 30vh;
    max-height: 38vh;
    overflow: auto;
    border: #00bd7e;
    border-style: solid;
    padding: 2px;
  }

</style>

<script setup lang="ts">
//todo make log go to bottom at phone
  import Calculator from '@/components/Calculator.vue';
  import Log from '@/components/Log.vue'
  import { ref } from 'vue'

  const log = ref([] as string[]);

  const addToLog = (expression: string) => {
    log.value.unshift(expression);
  };

</script>

<!--
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
-------------------
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
---------------
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
-->
