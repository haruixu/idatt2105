import { describe, it, expect, vi } from "vitest";
import { isSubmittingSuccessful } from "../loginutils.js"

describe("submission tests", () => {
  it("test sumbission creation successful", () => {
    const successfulResponse = 201
    expect(isSubmittingSuccessful(successfulResponse)).toBe(true);
  });

  it("test submission fail", () => {
    const response = 404;
    expect(isSubmittingSuccessful(response)).toBe(false);
  });

  it("test submission on no data", () => {
    const response = undefined
    expect(isSubmittingSuccessful(response)).toBe(false);
  });

  it("test submission on null data", () => {
    const response = null
    expect(isSubmittingSuccessful(response)).toBe(false);
  });

});
