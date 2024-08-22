<template>
  <view class="clock_in">
    <view class="clock_in__header">
      <view class="clock_in__title">
        <view>
          打卡系统
        </view>
      </view>
      <image src='@/static/image/wave.svg' mode='scaleToFill' class='gif-wave'></image>
    </view>
    <view class="clock_in__time">
      <view>
        {{ DateTimeUtil.formatDateTime(time, "yyyy年MM月dd日 HH:mm:ss") }}
      </view>
    </view>
    <view class="clock_in__body">
      <view id="wai" class="clock_in__bth clock_in__wai" :style="'background-color:'+wai_bg">
        <view id="zhong" class="clock_in__bth clock_in__zhong" :style="'background-color:'+zhong_bg">
          <view @click="clock" id="in" class="clock_in__bth clock_in__in" :style="'background-color:'+nei_bg">
            <view v-if="!clockIn">
              点击打卡
            </view>
            <view v-else>
              打卡成功
            </view>
          </view>
        </view>
      </view>
    </view>

    <view class="clock_in__text">
      <view class="clock_in__text_item">
        上班时间 08:30
      </view>
      <view class="clock_in__text_item">
        下班时间 17:00
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">

import {onMounted, ref} from "vue";
import {DateTimeUtil} from "@/util/TimeUtil";
import useSystemStore from "@/store/system/SystemStore";
import LogApi from "@/api/clock/LogApi";
import {LogBo, LogSearchBo} from "@/common/dto/clock/Log";

const time = ref(new Date())
const systemStore = useSystemStore()
let clockIn = ref(false)

const wai_bg = ref("#8cd8f9")
const zhong_bg = ref("#3dbff6")
const nei_bg = ref("#0287d2")

function toDayIsClockIn() {
  let searchBo = new LogSearchBo()
  searchBo.userId = systemStore.getUserVo().id
  LogApi.toDayIsClockIn(searchBo)
      .then((result) => {
        if (result?.content) {
          wai_bg.value = "#c2edd9"
          zhong_bg.value = "#92d2b4"
          nei_bg.value = "#33c481"
          clockIn.value = true;
        } else {
          wai_bg.value = "#8cd8f9"
          zhong_bg.value = "#3dbff6"
          nei_bg.value = "#0287d2"
        }
      })
}

function clock(){
  if (!clockIn.value){
    let logBo = new LogBo()
    logBo.userId = systemStore.getUserVo().id
    LogApi.clockIn(logBo).then((result) => {
      toDayIsClockIn()
    })
  }
}

onMounted(function () {
  systemStore.getVersion()
  setInterval(function () {
    time.value = new Date()
  })
  toDayIsClockIn()
})

</script>


<style lang="scss">
.clock_in {
  width: 100%;
}

.clock_in__header {
  width: 100%;
  height: 30vh;
  background: #0287d2;
  border-radius: 0 0 100vw 100vw / 0 0 33vw 33vw;
  position: relative;
}

.clock_in__title {
  width: 100%;
  height: calc(30vh - 15vw);
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 40px;
  font-weight: bold;
}

.gif-wave {
  position: absolute;
  width: 100vw;
  bottom: 0;
  left: 0;
  z-index: 99;
  mix-blend-mode: screen;
  height: 15vw;
}

.clock_in__time {
  width: 100vw;
  height: 10vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

.clock_in__text {
  width: 100vw;
  height: 10vw;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}

.clock_in__text_item {
  width: 100%;
  text-align: center;
}

.clock_in__body {
  width: 100%;
  height: 30vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.clock_in__bth {
  border-radius: 50%;
}

.clock_in__wai {
  width: 180px;
  height: 180px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.clock_in__zhong {
  width: 165px;
  height: 165px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.clock_in__in {
  width: 150px;
  height: 150px;
  display: flex;
  color: #fff;
  justify-content: center;
  align-items: center;
}
</style>
