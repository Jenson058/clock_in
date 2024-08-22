<template>
  <view class="wrapper">
    <!-- 个人资料 -->
    <view>
      <view class="top">
        <view class="center">
          <view class="center_login" v-if="!isLogin">
            <button @click="gotoLogin" class="center_login_btn">
              登录
            </button>
          </view>
          <view v-else class="center_top">
            <view class="center_img">
              <!-- 这里可以放自己的静态头像 -->
              <u-image :show-loading="true" width="66px" height="66px"
                       :src="FileVo.getUploadUrl(userVo.avatar as string)"></u-image>
            </view>
            <view class="center_info">
              <view class="center_name">
                <!-- 这里可以放自己的名称图片 -->
                <view>{{ userVo.nickname }}</view>
              </view>
            </view>
            <view style="margin-top: 30rpx;margin-left: auto">
              <u-image @click="gotoSetting" width="30px" height="30px" style="width: 30px;height: 30px;" src="@/static/icon/setting.png"></u-image>
            </view>
          </view>
        </view>
        <image src='@/static/icon/waterflow.gif' mode='scaleToFill' class='gif-wave'></image>
      </view>
    </view>

    <!-- 统计 -->
    <!-- 地址管理 -->
    <view class="extra">
      <view class="item icon-arrow">联系作者</view>
      <view class="item icon-arrow">意见反馈</view>
    </view>
    <view class="extra">
      <view @click="gotoRenewal" class="item icon-arrow">关于软件</view>
    </view>
  </view>
</template>

<script setup lang="ts">

import {onMounted, ref} from "vue";
import {UserVo} from "@/common/dto/user/User";
import useSystemStore from "@/store/system/SystemStore";
import {FileVo} from "@/common/dto/file/File";
import {onLoad, onShow} from "@dcloudio/uni-app";

const isLogin = ref(false)
const systemStore = useSystemStore()
const userVo = ref(new UserVo())

onMounted(() => {
  getUser()
})

function getUser() {
  if (systemStore.getUserVo().id) {
    isLogin.value = true
    userVo.value = systemStore.getUserVo()
  } else {
    const user = uni.getStorageSync("userVo") as UserVo
    if (user.id) {
      isLogin.value = true
      systemStore.setUserVo(user)
      userVo.value = user
    } else {
      isLogin.value = false
    }
  }
}

onShow(()=>{
  getUser()
})

function gotoLogin() {
  uni.navigateTo({
    url: "/pages/login/login"
  })
}

function gotoSetting(){
  uni.navigateTo({
    url: "/pages/setting/setting"
  })
}

function gotoRenewal(){
  uni.navigateTo({
    url: "/pages/renewal/about"
  })
}
</script>

<style lang="scss">
.top {
  width: 100%;
  height: 130px;
  background: #1c90ed;
  padding-top: 15px;
  position: relative;
}

.center {
  width: 95%;
  height: 100px;
  background: white;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  border-radius: 5px;
}

.center_top {
  display: flex;
  flex-direction: row;
  width: 80%;
  height: 80px;
  margin: 0 auto;
  margin-top: 20rpx;
  border-bottom: 1px solid #EEEEEE;
}

.center_img {
  width: 66px;
  height: 66px;
  border-radius: 50%;
  overflow: hidden;
}

.center_img image {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.center_login {
  width: 100% !important;
  height: 100% !important;
  display: flex;
  align-items: center !important;
  justify-content: center !important;
}

.center_login_btn {
  width: 50% !important;
  background: #1c90ed !important;
  color: white !important;
  border-radius: 16px !important;
}

.center_info {
  display: flex;
  flex-direction: column;
  margin-top: 20rpx;
  margin-left: 30px;
}

.center_name {
  font-size: 18px;
}

.center_rank {
  width: 50%;
  height: 35px;
  display: flex;
  flex-direction: row;
}

.center_rank image {
  width: 35px;
  height: 35px;
}

.center_score {
  width: 50%;
  height: 35px;
  display: flex;
  flex-direction: row;
}

.center_score image {
  width: 35px;
  height: 35px;
}

.gif-wave {
  position: absolute;
  width: 100%;
  bottom: 0;
  left: 0;
  z-index: 99;
  mix-blend-mode: screen;
  height: 100rpx;
}

.wrapper {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  background-color: #F4F4F4;
}

.profile {
  height: 375rpx;
  background-color: #ea4451;
  display: flex;
  justify-content: center;
  align-items: center;

  .meta {
    .avatar {
      display: block;
      width: 140rpx;
      height: 140rpx;
      border-radius: 50%;
      border: 2rpx solid #fff;
      overflow: hidden;
    }

    .nickname {
      display: block;
      text-align: center;
      margin-top: 20rpx;
      font-size: 30rpx;
      color: #fff;
    }
  }
}

.count {
  display: flex;
  margin: 0 20rpx;
  height: 120rpx;
  text-align: center;
  border-radius: 4rpx;
  background-color: #fff;

  position: relative;
  top: 10rpx;

  .cell {
    margin-top: 10rpx;
    flex: 1;
    padding-top: 20rpx;
    font-size: 27rpx;
    color: #333;
  }

  text {
    display: block;
    font-size: 24rpx;
  }
}

.orders {
  margin: 20rpx 20rpx 0 20rpx;
  padding: 40rpx 0;
  background-color: #fff;
  border-radius: 4rpx;

  .title {
    padding-left: 20rpx;
    font-size: 30rpx;
    color: #333;
    padding-bottom: 20rpx;
    border-bottom: 1rpx solid #eee;
    margin-top: -30rpx;
  }

  .sorts {
    padding-top: 30rpx;
    text-align: center;
    display: flex;
  }

  [class*="icon-"] {
    flex: 1;
    font-size: 24rpx;

    &::before {
      display: block;
      font-size: 48rpx;
      margin-bottom: 8rpx;
      color: #ea4451;
    }
  }
}

.address {
  line-height: 1;
  background-color: #fff;
  font-size: 30rpx;
  padding: 25rpx 0 25rpx 20rpx;
  margin: 10rpx 20rpx;
  color: #333;
  border-radius: 4rpx;
}

.extra {
  margin: 10rpx 20rpx;
  background-color: #fff;
  border-radius: 4rpx;

  .item {
    line-height: 1;
    padding: 25rpx 0 25rpx 20rpx;
    border-bottom: 1rpx solid #eee;
    font-size: 30rpx;
    color: #333;
  }

  button {
    text-align: left;
    background-color: #fff;

    &::after {
      border: none;
      border-radius: 0;
    }
  }
}

.icon-arrow {
  position: relative;

  &::before {
    position: absolute;
    top: 50%;
    right: 20rpx;
    transform: translateY(-50%);
  }
}
</style>