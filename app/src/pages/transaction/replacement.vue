<script setup lang="ts">
// @ts-nocheck

import {onMounted, ref} from "vue";
import {formatDate} from "@/util/DateUtil";
import {LogBo} from "@/common/dto/clock/Log";
import useSystemStore from "@/store/system/SystemStore";
import LogApi from "@/api/clock/LogApi";
import {FileVo} from "@/common/dto/file/File";

const show = ref(false)
const replacement = ref()
const maxDate = ref()
const minDate = ref()
const systemStore = useSystemStore()

onMounted(function () {
  let date = new Date();
  maxDate.value = new Date(date.getFullYear(), date.getMonth(), date.getDate() - 1);
  minDate.value = new Date(date.getFullYear(), date.getMonth() - 1, 1);
})

function confirm(dataList: Array<string>) {
  let date = new Date();
  replacement.value = new Date(dataList[0]);

  console.log(date, replacement.value)
  show.value = false
}

function replacementBtn() {
  let bo = new LogBo()
  bo.userId = systemStore.getUserVo().id
  bo.year = replacement.value.getFullYear()
  bo.mouth = replacement.value.getMonth() + 1;
  bo.day = replacement.value.getDate()

  LogApi.edit(bo).then(function (result) {

    uni.showModal({
      title: '补卡通知',
      content: formatDate(replacement.value)+"补卡成功",
      confirmText: '确定',
      showCancel: false, // 是否显示取消按钮，默认为 true
      success:function (res){
      }
    })
  })
}
</script>

<template>
  <up-form
  >
    <up-form-item style="padding: 10px 20px 0 20px" label="日期">
      <view>{{ formatDate(replacement) }}</view>
      <up-button style="width: 30vw;" @click="show = true">选择日期</up-button>
    </up-form-item>
  </up-form>
  <up-button type="success" style="width: 90vw;" @click="replacementBtn">补卡</up-button>
  <up-calendar
      :minDate="minDate"
      :maxDate="maxDate"
      :show="show"
      mode="single"
      @confirm="confirm"
      @close="show = false"
      :defaultDate="maxDate"
  />
</template>

<style scoped>

</style>