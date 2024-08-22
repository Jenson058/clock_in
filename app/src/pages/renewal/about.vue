<script setup lang="ts">
// @ts-nocheck
</script>

<template>
  <view class="about">
    <view class="logo">
      <u-image class="item" width="100px" height="100px" src="@/static/logo.png"/>
      <text class="item">打卡</text>
      <text class="item">Version</text>
    </view>
  </view>
</template>

<style scoped>
.about{
  width: 100vw;
  height: 100vh;
}

.logo{
  height: 40vh;
  display: flex;
  align-content: center;
  justify-content: center;
  flex-wrap: wrap;
}

.logo .item{
  flex-basis: 100vw;
  text-align: center;
  display: flex;
  justify-content: center;
  margin: 10px 0 10px 0;
}
</style>