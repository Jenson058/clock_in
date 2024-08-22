<script setup lang="ts">

import JensonTable from "@/model/jenson-table/JensonTable.vue";
import {ColumnUtil} from "@/common/decoration/Column";
import {RenewalBo, RenewalSearchBo, RenewalVo} from "@/common/dto/renewal/renewal";
import {ref} from "vue";
import PageConfig from "@/common/basic/PageConfig";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import JensonEditModel from "@/model/jenson-edit-model/JensonEditModel.vue";
import JensonImageUpload from "@/model/jenson-upload/JensonImageUpload.vue";
import JensonFileUpload from "@/model/jenson-upload/JensonFileUpload.vue";
import RenewalApi from "@/api/renewal/RenewalApi";

const pageConfig = ref(new PageConfig())
const editView = ref(false)
const renewalVo = ref(new RenewalVo())
const initTableData = ref<Array<RenewalVo>>([])

function initTable(){
  let searchBo = new RenewalSearchBo(pageConfig.value.page,pageConfig.value.pageSize)
  RenewalApi.pageAll(searchBo)
      .then(function (result){
        initTableData.value = result.content.content
        pageConfig.value.total = result.content.totalElements
      })
}

function add(){
  renewalVo.value = new RenewalVo()
  editView.value = true
}

function submit(){
  let bo = {...renewalVo.value} as RenewalBo
  RenewalApi.edit(bo)
      .then(function (result){
        initTable()
      })
}
</script>

<template>
  <jenson-table
      index
      :column-list="ColumnUtil.getColumnList(RenewalVo)"
      :page-config="pageConfig"
      view
      setting
      refresh
      :table-data="initTableData"
      @init-table="initTable"
  >
    <template #header-add>
      <jenson-button
          type="primary"
          @click="add"
      >
        上传版本
      </jenson-button>
    </template>
  </jenson-table>

  <jenson-edit-model
      v-model="editView"
      style="width: 400px;"
      title="上传版本"
      :footer="{
        submit:true,
        info:true
      }"
      :submit-on-click="submit"
  >
    <el-form>
      <el-form-item label="上传文件">
        <jenson-file-upload
            type="file"
            :limit="1"
            v-model="renewalVo.fileId"
        >

        </jenson-file-upload>
      </el-form-item>
      <el-form-item label="版本信息">
        <el-input v-model="renewalVo.version"/>
      </el-form-item>
    </el-form>
  </jenson-edit-model>
</template>

<style scoped>

</style>