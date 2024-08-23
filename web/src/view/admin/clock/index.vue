<script setup lang="ts">

import JensonTable from "@/model/jenson-table/JensonTable.vue";
import {ColumnUtil} from "@/common/decoration/Column";
import {LogSearchBo, LogVo} from "@/common/dto/clock/Log";
import {ref} from "vue";
import LogApi from "@/api/clock/LogApi";
import PageConfig from "@/common/basic/PageConfig";

const initTableData = ref<Array<LogVo>>([])
const pageConfig = ref(new PageConfig())

function initTable() {
  let searchBo = new LogSearchBo(pageConfig.value.page, pageConfig.value.pageSize)
  LogApi.pageAll(searchBo)
      .then((result) => {
        initTableData.value = result?.data?.content ?? []
        pageConfig.value.total = result?.data?.totalElements ?? 0
      })
}
</script>

<template>
  <jenson-table
      :column-list="ColumnUtil.getColumnList(LogVo)"
      :table-data="initTableData"
      @init-table="initTable"
      :page-config="pageConfig"
      index
      setting
      view
  >

  </jenson-table>
</template>

<style scoped>

</style>