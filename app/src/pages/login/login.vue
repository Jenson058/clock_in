<template>
  <view class="content">
    <view class="loginBox">
      <h3 style="text-align: center;margin-bottom:120rpx;">欢迎登录</h3>
      <view>
        <view class="ipt">
          <input v-model="loginBo.code" class="login_input" type="text" placeholder="请输入账号"/>
        </view>
        <view class="ipt">
          <input v-model="loginBo.password" class="login_input" type="password" placeholder="请输入密码"/>
        </view>
        <button @click="login" class="login_button">登录</button>
        <view class="forgetPwd">
          <span>忘记密码</span>
        </view>
      </view>
    </view>
  </view>
  <image src="@/static/image/login_wave.svg" class="login_wave"/>
</template>

<script setup lang="ts">

import {ref} from "vue";
import {LoginBo, LoginVo} from "@/common/dto/system/login";
import LoginApi from "@/api/system/LoginApi";
import {UserVo} from "@/common/dto/user/User";
import useSystemStore from "@/store/system/SystemStore";

const loginBo = ref(new LoginBo())
const systemStore = useSystemStore()

function login() {
  LoginApi.login(loginBo.value).then(async (result) => {
    let loginVo = result?.data ?? new LoginVo()
    uni.setStorageSync("token", loginVo?.token as string);
    uni.setStorageSync("userVo", loginVo?.userVo as UserVo);
    systemStore.setUserVo(loginVo.userVo ?? new UserVo())
    let pages = getCurrentPages();   // 获取当前页面栈的实例
    let currPage = pages[pages.length - 1];   //当前页面
    let prevPage = pages[pages.length - 2];  //上一个页面
    if (prevPage){
      uni.navigateBack({delta: 1})
    }else {
      uni.switchTab({url: "/pages/home/home"})
    }
  });
}
</script>


<style lang="scss">
.login_wave {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 40%;
  box-sizing: border-box;
  display: block;
  background-color: #ffffff;
}

.loginBox {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -60%);
  width: 90%;
  border-radius: 20rpx;
  padding: 60rpx;
  box-sizing: border-box;
}

h3 {
  color: rgb(66, 157, 250);
  font-size: 40rpx;
  letter-spacing: 10rpx;
  margin-bottom: 40rpx;
}

.ipt {
  height: 86rpx;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 40rpx;
  background-color: #f5f5f5;
  border-radius: 10rpx;
  padding-left: 10rpx;
}

.ipt .login_input u-input{
  margin-left: 20rpx !important;
  font-size: 28rpx !important;
}

.forgetPwd {
  margin-top: 30rpx;
  font-size: 26rpx;
  color: #b5b5b5;
  text-align: end;
  padding: 0 10rpx;
  display: flex;
  justify-content: space-between;
}

.login_button{
  line-height: 85rpx !important;
  text-align: center !important;
  background: rgb(66, 157, 250) !important;
  border-radius: 40rpx !important;
  color: #fff !important;
  margin-top: 40rpx !important;
}

.otherUser {
  margin-top: 30rpx;
  display: flex;
  justify-content: center;
}
</style>
