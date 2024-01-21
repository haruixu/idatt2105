<template>
  <div class="calculator">
    <div class="display">
      <p>{{ displayVal }}</p>
    </div>
    <div class="buttons">
      <button v-for="(name, id) in btnNames" :key="id" :class='id' @click="calculate(name)">
        {{ name }}
      </button>
    </div>
  </div>
</template>

<style scoped>

.calculator {
  display: flex;
  flex-direction: column;
  width:100%;
  border-radius: 5%;
}

.buttons {
  display: grid;
  grid-template-areas:
    'delete-all answer delete plus'
    'one two three minus'
    'four five six multiply'
    'seven eight nine divide'
    'zero zero point equals';
  min-width: 300px;
  padding: 0.2rem;
}

button {
  font-size: 30px;
  min-width: 100px;
  height: 100px;
  border-radius: 10%;
  border-color: #00bd7e;
}

.delete-all, .answer, .delete {
  background-color: gray;
}

.plus, .minus, .multiply, .divide, .equals {
  background-color: #ffaaaa
}

/* Apply grid areas to buttons */
.display {
  font-size: 40px;
  color: black;
  grid-area: display;
  text-align: right;
  padding-right: 10px;
  padding-top: 20px;
  margin: 0.2rem;
  min-height: 100px;
  background-color: beige;
  border-radius: 10px;
}

.delete-all { grid-area: delete-all; }
.answer { grid-area: answer; }
.delete { grid-area: delete; }
.plus { grid-area: plus; }
.one { grid-area: one; }
.two { grid-area: two; }
.three { grid-area: three; }
.minus { grid-area: minus; }
.four { grid-area: four; }
.five { grid-area: five; }
.six { grid-area: six; }
.seven { grid-area: seven; }
.eight { grid-area: eight; }
.nine { grid-area: nine; }
.divide { grid-area: divide; }
.zero { grid-area: zero; }
.point { grid-area: point; }
.equals { grid-area: equals; }

</style>

<script setup lang="ts">
import { ref } from 'vue'
//import Log from '@/components/Log.vue'

const isValid = ref(true);

let storedVal = "";
let displayVal = ref("  ");
let isFinal = false;

function calculate(value: string) {
  switch (value) {
    case 'C': displayVal.value = "";
      isFinal = true;
      break;

    case 'ANS': displayVal.value = useAnswer(displayVal.value)
    break;

    case 'DEL': displayVal.value = displayVal.value.slice(0, -1);
    break;

    case '=':
      displayVal.value = evaluateExpression(displayVal.value);
      isFinal = true;
      break;

    case '+':
    case '-':
    case '/':
    case '*':
      if (isFinal) {
        displayVal.value = "";
      } else {
        displayVal.value += value;
      }
      break;
    default: displayVal.value += value;
  }
}

function evaluateExpression(expression: string) {
  console.log(expression + ":")
  try {

    if (hasDividedByZero(expression)) {
        return "Cannot divide by zero";
    }

    let result = eval(expression).toString();
    storedVal = result;
    //Log.list.add(expression + " = " + result)
    return result;

  } catch(Error) {
    console.log(Error);
    return "Invalid expression"
  }
}
function hasDividedByZero(expression: string) {
  return expression.indexOf("/0") !== -1
}

function useAnswer(expression: string) {
  let operands = ['+', '-', '*', '/'];
  let lastChar = expression.charAt(expression.length -1);

  if (storedVal === "") {
    return expression;
  }

  if (operands.includes(lastChar)) {
    return expression + storedVal;
  }

  return expression + "*" + storedVal;
}

  const btnNames = {
    'delete-all': 'C',
    'answer': 'ANS',
    'delete': 'DEL',
    'plus': '+',
    'one': '1',
    'two': '2',
    'three': '3',
    'minus': '-',
    'four': '4',
    'five': '5',
    'six': '6',
    'multiply': '*',
    'seven': '7',
    'eight': '8',
    'nine': '9',
    'divide': '/',
    'zero': '0',
    'point': '.',
    'equals': '=',
  }
</script>