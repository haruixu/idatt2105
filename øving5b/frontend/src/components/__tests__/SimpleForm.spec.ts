import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import SimpleForm from '../SimpleForm.vue';

let wrapper;

describe('SimpleForm rendering', () => {
    beforeEach(() => {
        wrapper = mount(SimpleForm)
    });

    it("Renders all three label names", () => {
        expect(wrapper.text()).toContain("Name");
        expect(wrapper.text()).toContain("Email");
        expect(wrapper.text()).toContain("Message");
    })

    it("Renders all three error messages when first rendered", () => {
        expect(wrapper.text()).toContain("Name cannot be empty");
        expect(wrapper.text()).toContain("Email is invalid");
        expect(wrapper.text()).toContain("Message cannot be empty");
    })

    it("Removes name error message when name input field becomes non-empty", async () => {
        const instance = wrapper.vm;
        expect(wrapper.text()).toContain("Name cannot be empty");

        instance.form = { name: "name" }
        await wrapper.vm.$nextTick();

        expect(wrapper.text()).not.toContain("Name cannot be empty");
    })

    it("Removes email error message when email input field has correct format", async () => {
        const instance = wrapper.vm;
        expect(wrapper.text()).toContain("Email is invalid");

        instance.form = { email: "name@mail.com" }
        await wrapper.vm.$nextTick();

        expect(wrapper.text()).not.toContain("Email is invalid");
    })

    it("Removes message error message when message input field becomes non-empty", async () => {
        const instance = wrapper.vm;
        expect(wrapper.text()).toContain("Message cannot be empty");

        instance.form = { message: "message" }
        await wrapper.vm.$nextTick();

        expect(wrapper.text()).not.toContain("Message cannot be empty");
    })

    it("Activates submit button only when all fields are valid", async () => {
        const instance = wrapper.vm;
        const button = wrapper.find("#submitBtn")
        expect(button.element.disabled).toBe(true)

        instance.form = { name: "name", email: "name@hotmail.com", message: "message" }
        await wrapper.vm.$nextTick();

        await wrapper.vm.$nextTick();
        expect(button.element.disabled).toBe(false)
    })

    it("Binds input with form values", async () => {
        const instance = wrapper.vm;
        expect(instance.form.name).toBe("");

        const inputField = wrapper.find("#name-field");
        inputField.setValue("Harry");

        await wrapper.vm.$nextTick();
        expect(instance.form.name).toBe("Harry");
    })

    it("Triggers submit function when submitting", async () => {
        const onSubmitSpy = vi.spyOn(wrapper.vm, 'submit');
        const instance = wrapper.vm;

        //Set data to make form valid
        instance.form = { name: "name", email: "name@hotmail.com", message: "message" }
        await wrapper.vm.$nextTick();

        //Validate button is enabled
        expect(wrapper.find('button').element.disabled).toBe(false)

        await wrapper.find('#submitBtn').trigger('click');
        expect(onSubmitSpy).toHaveBeenCalledTimes(1);
    })

    it("Emits the entire form when submitting", async () => {

    })
})
