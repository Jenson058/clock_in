<script setup lang="ts">
import {onMounted, ref} from "vue";

const weekList = ref(['日', '一', '二', '三', '四', '五', '六'])
const year = ref(0)
const month = ref(0)
const weekDates = ref<Array<Array<number>>>([])

onMounted(function () {
  let day = this.$moment.format("YYYY-MM-DD")
  year.value = day.getFullYear()
  month.value = day.getMonth() + 1

  day.setFullYear(year,month,1)
  var w1 = day.getDay;
})
</script>

<template>
  <view class="view">
    <view class="card">
      <view class="year">{{year}}年{{month}}月</view>
      <view class="week">
        <view class="week-item" v-for="week in weekList">{{week}}</view>
      </view>
      <view></view>
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

.card{
  background: white;
  width: 90vw;
  height: 50vh;
  border-radius:5vw;
}

.year{
  display: flex;
  justify-content: center;
}

.week{
  display: flex;
  flex-direction: row;
}

.week-item{
  flex: 1;
  display: flex;
  justify-content: center;
}
</style>