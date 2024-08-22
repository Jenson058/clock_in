<script setup lang="ts">

import {UserVo} from "@/common/dto/user/User";
import useSystemStore from "@/store/system/SystemStore";

const systemStore = useSystemStore()

function outLogin(){
  uni.setStorageSync("userVo",new UserVo())
  systemStore.setUserVo(new UserVo())
  uni.redirectTo({url:"/pages/login/login"})
}

</script>

<template>
  <view class="setting_home">
    <view class="setting_home__context">
      <u-button class="setting_btn out_login_btn" @click="outLogin">退出登录</u-button>
    </view>
  </view>
</template>

<style lang="scss">
.setting_home{
  width: 100% !important;
  height: 100% !important;
}

.setting_home__context{
  width: calc(100vw - 40px) !important;
  margin: 20px !important;
}

.setting_btn{
  border-radius: 10px !important;
}

.out_login_btn{
  color: #fff !important;
  background: #f00 !important;
}
</style>