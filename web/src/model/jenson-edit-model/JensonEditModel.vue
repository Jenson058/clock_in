<script setup lang="ts">
// @ts-nocheck

import {computed} from "vue";
import JensonButton from "@/model/jenson-button/JensonButton.vue";

interface Props {
  modelValue: boolean
  type: "dialog" | "drawer"
  title?: string
  submitOnClick?: Function
  footer: {
    info: boolean
    submit: boolean
  }
}

const props = withDefaults(defineProps<Props>(), {
  modelValue: () => false,
  type: () => "dialog",
  footer: {
    info: true,
    submit: true
  } as object
})

const emit = defineEmits(["update:modelValue"])

const modelValue = computed({
  get() {
    return props.modelValue
  }, set(value: boolean) {
    emit("update:modelValue", value)
  }
})

function info() {
  modelValue.value = false
}
</script>

<template>
  <el-dialog
      v-if="props.type == 'dialog'"
      v-model="modelValue"
      :title="props.title"
      align-center
      draggable
  >
    <slot/>
    <template #footer>
      <slot name="footer"/>
      <jenson-button
          type="info"
          size="default"
          @click="info"
          v-show="props.footer.info"
      >
        取消
      </jenson-button>
      <jenson-button
          size="default"
          type="primary"
          @click="props.submitOnClick"
          v-show="props.footer.submit"
      >
        保存
      </jenson-button>
    </template>
  </el-dialog>

  <el-drawer
      v-else
      :title="props.title"
      v-model="modelValue"
  >
    <slot/>
    <template #footer>
      <slot name="footer"/>
      <jenson-button
          type="info"
          size="default"
          @click="info"
          v-show="props.footer.info"
      >
        取消
      </jenson-button>
      <jenson-button
          size="default"
          type="primary"
          @click="props.submitOnClick"
          v-show="props.footer.submit"
      >
        保存
      </jenson-button>
    </template>
  </el-drawer>
</template>

<style scoped>

</style>