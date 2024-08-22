import { createSSRApp } from "vue";
import App from "./App.vue";
import uViewPlus from "uview-plus"
import pinia from "@/config/pinia";
export function createApp() {
  const app = createSSRApp(App);
  app.use(uViewPlus).use(pinia)
  return {
    app,
  };
}
