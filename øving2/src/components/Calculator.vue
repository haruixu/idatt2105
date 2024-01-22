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

let storedVal = "";
let displayVal = ref("  ");
let isFinal = false;
let isInvalid = false;

/*
if operator and lastchar is operator => return
if wasEqual and number => reset, add number
if wasEqual and operator => lastResult, add operator
 */

function calculate(value: string) {
  switch (value) {
    case 'C': displayVal.value = "";
      break;

    case 'ANS': displayVal.value = useAnswer(displayVal.value)
    break;

    case 'DEL': displayVal.value = displayVal.value.slice(0, -1);
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

    default: displayVal.value = useNumber(value, displayVal.value);
  }
}

function evaluateExpression(expression: string) {
  console.log("expression:" + expression + ":" + expression.length)

  try {

    if (hasDividedByZero(expression)) {
      isInvalid = true;
      isFinal = true;
      return "Cannot divide by zero";
    }

    if (expression.includes("//") || expression.includes("**")) {
      isInvalid = true;
      isFinal = true;
      return "Invalid expression"
    }

    let result = eval(expression).toString();
    storedVal = result;
    emit('calculate', expression + " = " + result)
    console.log("result:" + result + ":" + result.length)
    isFinal = true;
    return result;

  } catch(Error) {
    console.log(Error); //todo remove
    isFinal = true;
    isInvalid = true;
    return "Invalid expression"
  }
}
function hasDividedByZero(expression: string) {
  return expression.indexOf("/0") !== -1
}

function useAnswer(expression: string) {
  let operands = ['+', '-', '*', '/'];
  let lastChar = expression.charAt(expression.length -1);

  if (storedVal === "") {//Keep the same expression if there is no saved answer
    return expression;
  }

  if (expression === "") {//Add storedVal if there is no expression
    return storedVal;
  }

  if (operands.includes(lastChar)) { //if last character is operand add storedVal to the string
    return expression + storedVal;
  }

  if (isInvalid) {//if was invalid, delete "Invalid expression" string and use storedVal
    isInvalid = false;
    return storedVal
  }

  return expression + "*" + storedVal; //Multiply if no operator
}

function useOperator(operator: string, expression: string) {
  if (isInvalid) {
    isInvalid = false;
    return "";
  }
  isFinal = false;
  return expression + operator;

}

function useNumber(number: string, expression: string) {
  if (isFinal) {
    isFinal = false;
    isInvalid = false;
    return number;
  } else {
    return expression + number
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