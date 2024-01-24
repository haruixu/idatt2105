<template>
  <div class="calculator">
    <div class="display">
      <p v-if="!hasError">{{ displayVal }}</p>
      <p v-else>{{ error }}</p>
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
  /*width: 100px;
  height: 100px;*/
  border-radius: 5px;
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

/*.delete-all { grid-area: delete-all; }
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
.point { grid-area: point; }
.equals { grid-area: equals; }*/
.zero { grid-area: zero; }

</style>

<script setup lang="ts">
import { ref } from 'vue'

const emit = defineEmits<{
  calculate: [expression: string]
}>()

const displayVal = ref("");
const hasError = ref(false);
const error = ref("")

let storedVal = "";
let isFinal = false;

function calculate(value: string) {
  switch (value) {
    case 'C': hasError.value = false; displayVal.value = "";
      break;
    case 'ANS': useAnswer(displayVal.value)
    break;
    case 'DEL': hasError.value = false; displayVal.value = displayVal.value.slice(0, -1);
    break;
    case '=':
      evaluateExpression(displayVal.value);
      break;
    case '+':
    case '-':
    case '/':
    case '*':
      useOperator(value, displayVal.value);
      break;
    default: useNumber(value, displayVal.value);
  }
}

function evaluateExpression(expression: string) {
  console.log("expression:" + expression + ":" + expression.length)

  try {

    if (hasDividedByZero(expression)) {
      hasError.value = true;
      isFinal = true;
      error.value = "Cannot divide by zero";
      displayVal.value = "";
    }

    else if (expression.includes("//") || expression.includes("**")) {
      hasError.value = true;
      isFinal = true;
      error.value = "Invalid expression"
      displayVal.value = "";
    }

    else {
      let result = eval(expression).toString();
      storedVal = result;
      emit('calculate', expression + " = " + result)
      console.log("result:" + result + ":" + result.length)
      isFinal = true;
      displayVal.value = result;
    }

  } catch(Error) {
    console.log(Error); //todo remove
    isFinal = true;
    hasError.value = true;
    error.value = "Invalid expression"
    displayVal.value = "";
  }
}
function hasDividedByZero(expression: string) {
  return expression.indexOf("/0") !== -1
}

function useAnswer(expression: string) {
  let operands = ['+', '-', '*', '/'];
  let lastChar = expression.charAt(expression.length -1);

  if (storedVal === "") {//Keep the same expression if there is no saved answer
    displayVal.value = expression;
  }

  else if (expression === "") {//Add storedVal if there is no expression (either error or start)
    hasError.value = false;
    displayVal.value = storedVal;
  }

  else if (operands.includes(lastChar)) { //if last character is operand add storedVal to the string
    displayVal.value = expression + storedVal;
  }

  else {
    displayVal.value = expression + "*" + storedVal;
  }//Multiply if no operator
}

function useOperator(operator: string, expression: string) {
  if (hasError.value) {
    hasError.value = false;
    displayVal.value = "";
  } else {
    isFinal = false;
    displayVal.value = expression + operator;
  }
}

function useNumber(number: string, expression: string) {
  if (isFinal) {
    isFinal = false;
    hasError.value = false;
    displayVal.value = number;
  } else {
    displayVal.value = expression + number
  }
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