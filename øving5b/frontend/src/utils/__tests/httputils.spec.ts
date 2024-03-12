import { describe, it, expect, vi } from "vitest";
import { submissionRequest } from "../httputils.js"
import axios from 'axios';

describe("httputils tests", () => {
  it("test that axios.post method is called while calling request method", async () => {
    const spy = vi.spyOn(axios, 'post');
    await submissionRequest({ name: "name", email: "email" });
    expect(spy).toHaveBeenCalledTimes(1);
  });
});
