import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from '../calculator/Calculator.vue';

let wrapper;
describe('Calculator button and display rendering', () => {
    beforeEach(() => {
        wrapper = mount(Calculator);
    });

    it('Renders buttons correctly', () => {
        const buttons = ["0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "0",
            "+", "-", "*", "/", "C",
            "DEL", "ANS", "="];

        buttons.forEach(button => expect(wrapper.text()).toContain(button))
    })

    it('Renders display value is empty', () => {
        expect(wrapper.text()).toContain("");
    })

    it('Tests that displayed value displays the value of displayVal', async () => {
        const instance = wrapper.vm;
        instance.displayVal = "22";

        // Wait for the next tick to ensure the component updates
        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");

        expect(displayDiv.element.textContent).toBe("22");
    })

    it('Tests that displayed value displays the correct value when changed', async () => {
        const instance = wrapper.vm;
        instance.displayVal = "22";
        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.element.textContent).toBe("22");
        instance.displayVal = "223";
        await wrapper.vm.$nextTick();

        expect(displayDiv.element.textContent).toBe("223");
    })

    it("Shows the error value after an error"), async () => {
        const instance = wrapper.vm;

        instance.hasError = true;
        instance.error = "Error";
        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe("Error");
    }
})

describe('Calculator display rendering when using buttons', () => {
    beforeEach(() => {
        wrapper = mount(Calculator);
    });

    it('Clicking a button calls clickBtn once', () => {
        const clickBtnSpy = vi.spyOn(wrapper.vm, 'clickBtn')
        wrapper.find('button').trigger('click');
        expect(clickBtnSpy).toHaveBeenCalled();
    })

    it('Clears display value upon C-click', async () => {
        const instance = wrapper.vm;
        instance.displayVal = "22";

        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe("22");

        await instance.clickBtn('C');
        expect(displayDiv.text()).toBe("");
    })

    it('Renders same display value if answer is empty after ANS-click', async () => {
        const expression = "1+";
        const instance = wrapper.vm;
        instance.displayVal = expression;
        instance.storedVal = "";

        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('ANS');
        expect(displayDiv.text()).toBe(expression);
    })

    it('Renders ANS-value after ANS-click and expression is empty', async () => {
        const expression = "";
        const instance = wrapper.vm;
        instance.displayVal = expression;
        instance.storedVal = "33";

        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        // Simulate ANS click by calling useAnswer
        await instance.clickBtn('ANS');

        // Assert the updated display value
        expect(displayDiv.text()).toBe("33");
    })

    it('Appends ANS-value after ANS-click when the expression ends with operator', async () => {
        const expression = "1+";
        const instance = wrapper.vm;
        instance.displayVal = expression;
        instance.storedVal = "33";

        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('ANS');
        expect(displayDiv.text()).toBe("1+33");
    })

    it('Multiplies expression with ANS-val when last value in expression is digit', async () => {
        const expression = "1";
        const instance = wrapper.vm;
        instance.displayVal = expression;
        instance.storedVal = "33";

        await wrapper.vm.$nextTick();

        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('ANS');
        expect(displayDiv.text()).toBe("1*33");
    })

    it('Deletes last character after DEL-click', async () => {
        const expression = "1+";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('DEL');
        expect(displayDiv.text()).toBe("1");
    })

    it('does add dot to expression', async () => {
        const expression = "1";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('.');
        expect(displayDiv.text()).toBe("1.");
    })

    it('does not add dot if last character is dot', async () => {
        const expression = "1.";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('.');
        expect(displayDiv.text()).toBe("1.");
    })

    it('does not add operator if expression empty', async () => {
        const expression = "";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('+');
        expect(displayDiv.text()).toBe("");
    })

    it('does not add operator if last character is operator', async () => {
        const expression = "1+";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('/');
        expect(displayDiv.text()).toBe("1+");
    })

    it('appends opperator to expression', async () => {
        const expression = "1";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('+');
        expect(displayDiv.text()).toBe("1+");
    })

    it('appends opperator to expression', async () => {
        const instance = wrapper.vm;
        instance.hasError = true;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");

        await instance.clickBtn('+');
        expect(displayDiv.text()).toBe("");
    })

    it('appends zero after 0-click', async () => {
        const expression = "1";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('0');
        expect(displayDiv.text()).toBe("10");
    })

    it('Appends nothing if expression is empty', async () => {
        const expression = "";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");
        expect(displayDiv.text()).toBe(expression);

        await instance.clickBtn('0');
        expect(displayDiv.text()).toBe("");
    })

    it('Clears the expression if the displayed value is final', async () => {
        const instance = wrapper.vm;
        instance.isFinal = true;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");

        await instance.clickBtn('0');
        expect(displayDiv.text()).toBe("");
    })

    it('Appends nothing if last character was zero and second to last was operator', async () => {
        const expression = "1+0";
        const instance = wrapper.vm;
        instance.displayVal = expression;


        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");

        await instance.clickBtn('0');
        expect(displayDiv.text()).toBe("1+0");
    })

    it('shows "Cannot divide by zero" if expression divides by zero', async () => {
        const expression = "1/0";
        const instance = wrapper.vm;
        instance.displayVal = expression;

        await wrapper.vm.$nextTick();
        const displayDiv = wrapper.find(".display");

        await instance.clickBtn('=');
        expect(displayDiv.text()).toBe("Cannot divide by zero");
        }
    )

    it('shows "Invalid expression" with invalid expression', async () => {
            const expression = "1++";
            const instance = wrapper.vm;
            instance.displayVal = expression;

            await wrapper.vm.$nextTick();
            const displayDiv = wrapper.find(".display");

            await instance.clickBtn('=');
            expect(displayDiv.text()).toBe("Invalid expression");
        }
    )

    it('shows calculated value', async () => {
            const expression = "1+2";
            const instance = wrapper.vm;
            instance.displayVal = expression;

            await wrapper.vm.$nextTick();
            const displayDiv = wrapper.find(".display");

            await instance.clickBtn('=');
            expect(displayDiv.text()).toBe("3");
        }
    )
})

//Use spying when tracking calling behaviour for now
//Use mocking to force a certain path, e.g forcing hasDividedByZero to throw an error without
//needing to use the actual logic inside the function, then => spy on calling behaviour

//For forms, ensure button is disabled before valid
