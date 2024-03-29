<template>
  <div class="calculator">
    <div class="display">
      <p v-if="!hasError">{{ displayVal }}</p>
      <p v-else>{{ error }}</p>
    </div>
    <div class="buttons">
      <button v-for="(name, id) in btnNames" :key="id" :class='id' @click="clickBtn(name)">
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
  border: solid black;
  margin-bottom: 2px;
}

.buttons {
  display: grid;
  grid-template-areas:
    'delete-all answer delete plus'
    'one two three minus'
    'four five six multiply'
    'seven eight nine divide'
    'zero zero point equals';
  padding: 0.2rem;
}

button {
  font-size: 30px;
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
  font-size: 30px;
  color: black;
  grid-area: display;
  text-align: right;
  padding-right: 10px;
  padding-top: 20px;
  margin: 0.2rem;
  min-height: 100px;
  background-color: beige;
  border-radius: 10px;
  overflow: auto;
  border: solid black;
}

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
const operands = ['+', '-', '*', '/'];

let storedVal = "";
let isFinal = false;

function clickBtn(value: string) {
  switch (value) {
    case 'C': hasError.value = false; displayVal.value = "";
      break;
    case 'ANS': displayVal.value = useAnswer(displayVal.value)
      break;
    case 'DEL': hasError.value = false; displayVal.value = displayVal.value.slice(0, -1);
      break;
    case '.': displayVal.value = useDot(displayVal.value);
        break;
    case '=':
      displayVal.value = evaluateExpression(displayVal.value);
      break;
    case '+':
    case '-':
    case '/':
    case '*':
      displayVal.value = useOperator(value, displayVal.value);
      break;
    case '0': displayVal.value = useZero(displayVal.value);
      break;
    default: displayVal.value = useNumber(value, displayVal.value);
  }
}

function evaluateExpression(expression: string) {
  console.log("expression:" + expression + ":" + expression.length)

  if (expression === "") {
    return expression;
  }

  console.log("Result: " + expression)

  try {

    if (hasDividedByZero(expression)) {
      hasError.value = true;
      isFinal = true;
      error.value = "Cannot divide by zero";
      return "";
    }

    else {
      let result = eval(expression).toString();
      storedVal = result;
      emit('calculate', expression + " = " + result)
      console.log("result:" + result + ":" + result.length)
      isFinal = true;
      return result;
    }

  } catch(Error) {
    //1++, 1.1.1, 1+,
    console.log(Error); //todo remove
    isFinal = true;
    hasError.value = true;
    error.value = "Invalid expression"
    return "";
  }
}
function hasDividedByZero(expression: string) {
  return expression.indexOf("/0") !== -1
}

function useAnswer(expression: string) {
  isFinal = false;

  let lastChar = expression.charAt(expression.length -1);

  if (storedVal === "") {//Keep the same expression if there is no saved answer
    return expression;
  }

  if (expression === "") {//Add storedVal if there is no expression (either error or start)
    hasError.value = false;
    return storedVal;
  }

  else if (operands.includes(lastChar)) { //if last character is operand add storedVal to the string
    return expression + storedVal;
  }

  else {
    return expression + "*" + storedVal;
  }
}

function useOperator(operator: string, expression: string) {
  if (expression.length === 0) return expression;
  if (operands.includes(expression.charAt(expression.length - 1))) return expression;

  if (hasError.value) {
    hasError.value = false;
    return "";
  } else {
    isFinal = false;
    return expression + operator;
  }
}

function useNumber(number: string, expression: string) {
  if (isFinal) {
    isFinal = false;
    hasError.value = false;
    return number;
  } else if (operands.includes(expression.charAt(expression.length - 2))
      && expression.charAt(expression.length - 1) == '0') {
    return expression.substring(0, expression.length-1) + number;
  }
  else {
    return expression + number
  }
}

function useDot(expression: string) {
  if (displayVal.value.endsWith('.')) {
    return expression;
  }

  return useNumber('.', expression)
}

function useZero(expression: string) {
  if (expression.length === 0 || isFinal) {
    return "";
  }

  if (operands.includes(expression.charAt(expression.length - 2))
      && expression.charAt(expression.length - 1) == '0') return expression;

  return useNumber('0', expression)
}

const btnNames = {
  'delete-all': 'C', 'answer': 'ANS', 'delete': 'DEL', 'plus': '+', 'one': '1',
  'two': '2', 'three': '3', 'minus': '-', 'four': '4', 'five': '5', 'six': '6',
  'multiply': '*', 'seven': '7', 'eight': '8', 'nine': '9', 'divide': '/', 'zero': '0',
  'point': '.', 'equals': '=',
}

</script>