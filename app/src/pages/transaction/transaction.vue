<template>
  <view class="transition">
    <up-text type="primary" text="基本"></up-text>
    <up-grid
        col="4"
    >
      <up-grid-item
          v-for="item in transaction.basic"
          @click="clickTransaction(item.path)"
      >
        <up-icon
            :customStyle="{paddingTop:20+'rpx'}"
            :name="item.icon"
            :size="30"
        ></up-icon>
        <text class="grid-text">{{item.name}}</text>
      </up-grid-item>
    </up-grid>
  </view>
</template>

<script setup lang="ts">

import {ref} from "vue";

const transaction = ref({
  basic:[
      {name:"查看打卡",icon:"calendar",path:"/pages/transaction/look"},
      {name:"补卡",icon:"checkmark-circle",path:"/pages/transaction/replacement"},
  ]
})

function clickTransaction(path){
  uni.navigateTo({
    url: path
  })
}

</script>


<style scoped lang="scss">
.transition{
  padding: 10px 30px 10px 30px;
}

.grid-text {
  font-size: 14px;
  color: #909399;
  padding: 10rpx 0 20rpx 0rpx;
  /* #ifndef APP-PLUS */
  box-sizing: border-box;
  /* #endif */
}
</style>
