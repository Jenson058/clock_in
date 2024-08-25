<script setup lang="ts">
import {onMounted, ref} from "vue";
import LogApi from "@/api/clock/LogApi";
import {LogSearchBo, LogVo} from "@/common/dto/clock/Log";
import useSystemStore from "@/store/system/SystemStore";
import {ListUtil} from "../../util/ListUtil";

const weekList = ref(['日', '一', '二', '三', '四', '五', '六'])
const year = ref(0)
const month = ref(0)
const weekDates = ref<Array<Array<number>>>([])
const logData = ref<Array<LogVo>>([])
const systemStore = useSystemStore()
const startX = ref()

onMounted(function () {
  let day = new Date()
  year.value = day.getFullYear()
  month.value = day.getMonth() + 1
  setWeekDates()
  initTable()
})

function initTable() {
  let searchBo = new LogSearchBo()
  searchBo.userId = systemStore.getUserVo().id
  searchBo.mouth = month.value
  searchBo.year = year.value
  LogApi.listAll(searchBo)
      .then(function (result) {
        logData.value = result?.data
      })
}
function setWeekDates() {
  weekDates.value = []
  let firstDate = new Date(year.value, month.value - 1, 1).getDate()
  let endDate = new Date(year.value, month.value, 0).getDate()
  let week = new Array(7)
  for (let i = firstDate; i <= endDate; i++) {
    let nowDate = new Date(year.value, month.value - 1, i)
    week[nowDate.getDay()] = nowDate.getDate()
    if (nowDate.getDay() == 6) {
      weekDates.value.push(week)
      week = new Array(7)
    } else if (i == endDate) {
      weekDates.value.push(week)
      week = new Array(7)
    }
  }
  console.log(firstDate, endDate, weekDates.value)
}

function updateMonth(update: number) {
  month.value += update;
  if (month.value == 0) {
    month.value = 12
    year.value --;
  }else if (month.value == 13){
    month.value = 1
    year.value ++;
  }
  setWeekDates()
  initTable()
}

function updateYear(update: number) {
  year.value += update;
  setWeekDates()
  initTable()
}

function touchStart(e){
  startX.value = e.touches[0].clientX
}

function touchEnd(e){
  let deltaX = e.changedTouches[0].clientX - startX.value
  if (deltaX >= 0){
    updateMonth(-1)
  }else {
    updateMonth(1)
  }
}
</script>

<template>
  <view class="view">
    <view class="card" @touchstart="touchStart" @touchend="touchEnd">
      <view class="year">
        <view style="padding-left: 10px;">
          <view @click="updateYear(-1)">
            <up-icon
                name="arrow-left-double"
                :size="20"
            ></up-icon>
          </view>
        </view>
        <view>
          <view @click="updateMonth(-1)">
            <up-icon
                name="arrow-left"
                :size="20"
            ></up-icon>
          </view>
        </view>
        <text>{{ year }}年{{ month }}月</text>
        <view>
          <view @click="updateMonth(1)">
            <up-icon
                name="arrow-right"
                :size="20"
            ></up-icon>
          </view>
        </view>
        <view style="padding-right: 10px;">
          <view @click="updateYear(1)">
            <up-icon
                name="arrow-right-double"
                :size="20"
            ></up-icon>
          </view>
        </view>
      </view>
      <view class="week">
        <view class="week-item" v-for="week in weekList">{{ week }}</view>
      </view>
      <view>
        <view class="week" v-for="week in weekDates">
          <view class="week-day-item" v-for="item in week">
            <view class="week-day-width">{{ item }}</view>
            <view class="week-day-width">{{
                ListUtil.exist(logData.map(it => it.day), item)
                    ?
                    '✔️' : ''
              }}</view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<style scoped>
.view {
  box-sizing: border-box;
  background: linear-gradient(#0287d2, white);
  width: 100vw;
  height: 95vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  background: white;
  width: 90vw;
  border-radius: 5vw;
}

.year {
  display: flex;
  height: 6vh;
  align-items: center;
  justify-content: space-between;

}

.week {
  display: flex;
  flex-direction: row;
}

.week-item {
  flex: 1;
  height: 6vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.week-day-item {
  flex: 1;
  height: 6vh;
  flex-wrap: wrap;
  align-content: flex-start;
}

.week-day-width {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>