<script setup lang="ts">

import useSystemStore from "@/store/system/SystemStore";
import {onMounted, ref} from "vue";
import {LogSearchBo, LogVo} from "@/common/dto/clock/Log";
import LogApi from "@/api/clock/LogApi";
import useUserStore from "@/store/user/UserStore";
import {ListUtil} from "@/util/ListUtil";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import {CalendarDateType, CalendarInstance} from "element-plus";
import {formatDate} from "@vueuse/shared";
import {DateUtil} from "../../../util/DateUtil";
import {UserSearchBo, UserVo} from "@/common/dto/user/User";
import UserApi from "@/api/user/UserApi";

let userStore = useUserStore()
let logData = ref<Array<LogVo>>([])
let date = ref(new Date())
let calendar = ref<CalendarInstance>()
let userList = ref<Array<UserVo>>([])
let userId = ref(userStore.getUserVo().id)

onMounted(function () {
  initUser()
  initTable()
})


function initUser() {
  let searchBo = new UserSearchBo()
  UserApi.listAll(searchBo)
      .then(function (result) {
        userList.value = result.content
      })
}
function initTable() {
  let searchBo = new LogSearchBo()
  searchBo.userId = userId.value
  searchBo.mouth = date.value.getMonth() + 1
  searchBo.year = date.value.getFullYear()
  LogApi.listAll(searchBo)
      .then(function (result) {
        logData.value = result.content
      })
}

const selectDate = (val: CalendarDateType) => {
  if (!calendar.value) return
  calendar.value.selectDate(val)
  initTable()
}
</script>

<template>
  <el-calendar ref="calendar" v-model="date">
    <template #date-cell="{ data }">
      <p>
        {{ data.day.split("-")[2] }}
        {{
          ListUtil.exist(logData.map(it => it.year), Number(data.day.split("-")[0]))
          && ListUtil.exist(logData.map(it => it.mouth), Number(data.day.split("-")[1]))
          && ListUtil.exist(logData.map(it => it.day), Number(data.day.split("-")[2]))
              ?
              '✔️' : ''
        }}
      </p>
    </template>
    <template #header="{data}">
      <div class="calendar-header">
        <div class="calendar-header-item">
          <el-form-item label="打卡人">
            <el-select style="width: 200px" v-model="userId">
              <el-option v-for="user in userList" :label="user.name" :value="user.id"/>
            </el-select>
          </el-form-item>
        </div>
        <div class="calendar-header-item">{{DateUtil.fromData(date)}}</div>
        <div class="calendar-header-item">
          <jenson-button @click="selectDate('prev-year')">上一年</jenson-button>
          <jenson-button @click="selectDate('prev-month')">上一月</jenson-button>
          <jenson-button @click="selectDate('today')">当前</jenson-button>
          <jenson-button @click="selectDate('next-month')">下一月</jenson-button>
          <jenson-button @click="selectDate('next-year')">下一年</jenson-button>
        </div>
      </div>
    </template>
  </el-calendar>
</template>

<style scoped>

.calendar-header{
  display: flex;
  width: 100%;
  justify-content:space-between;
}

.calendar-header-item{

}
</style>