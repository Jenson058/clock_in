<script lang="ts" setup>
interface Props {
  size?: "large" | "default" | "small"
  type?: "warning" | "success" | "danger" | "info" | "primary" | "text"
  color?:string
  auth?:string
}

const props = withDefaults(defineProps<Props>(), {
  size: "small",
  auth:undefined
})
</script>

<template>
  <el-button
      :size="props.size"
      :type="props.type"
      :color="props.color"
      v-auth="props.auth"
  >
    <slot></slot>
  </el-button>
</template>

<style scoped>

</style>