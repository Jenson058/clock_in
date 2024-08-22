<script setup lang="ts">

import JensonTable from "@/model/jenson-table/JensonTable.vue";
import {ColumnUtil} from "@/common/decoration/Column";
import {RoleSearchBo, RoleVo} from "@/common/dto/user/Role";
import {ref} from "vue";
import PageConfig from "@/common/basic/PageConfig";
import RoleApi from "@/api/user/RoleApi";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import JensonEditModel from "@/model/jenson-edit-model/JensonEditModel.vue";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import useRoleStore from "@/store/user/RoleStore";
import {getI18n} from "../../../../config/i18n";
const selectionList = ref<Array<RoleVo>>([])

const initTableData = ref<Array<RoleVo>>([])
const pageConfig = ref<PageConfig>(new PageConfig())
const editModel = ref(false)
const roleVo = ref(new RoleVo())
const router = useRouter()
const roleStore = useRoleStore();

async function initTable(){
  let searchBo = new RoleSearchBo(pageConfig.value.page,pageConfig.value.pageSize)
  RoleApi.pageAll(searchBo)
      .then(result =>{
        initTableData.value = result?.content?.content ?? []
        pageConfig.value.total = result?.content?.totalElements ?? 0
      })
}

function add(){
  console.log("add")
  roleVo.value = new RoleVo()
  editModel.value = true
}

function edit(row:RoleVo){
  roleVo.value = {...row}
  editModel.value = true
}

async function submit(){
  let bo = {...roleVo.value} as RoleVo
  RoleApi.edit(bo).then(async () => {
    ElMessage.success("保存成功")
    editModel.value = false
    await initTable()
  })
}

async function goToAuth(row:RoleVo){
  roleStore.setRoleVo(row)
  localStorage.setItem("roleId",row.id)
  await router.push("auth")
}

async function delAll(){
  let searchBo = new RoleSearchBo()
  searchBo.idList = selectionList.value.map(it => it.id)
  RoleApi.delAll(searchBo)
      .then(async ()=>{
        await initTable()
        ElMessage.success("删除成功")
      })
}

async function del(row:RoleVo){
  RoleApi.del({id:row.id})
      .then(async ()=>{
        await initTable()
        ElMessage.success("删除成功")
      })
}
</script>

<template>
  <jenson-table
      :column-list="ColumnUtil.getColumnList(RoleVo)"
      :table-data="initTableData"
      @init-table="initTable"
      :page-config="pageConfig"
      @handle-selection-change="args => selectionList = args"
      column
      index
      view
      refresh
      selection
      setting
  >
    <template #header-add>
      <jenson-button
          type="primary"
          @click="add"
      >
        {{getI18n("btn.add")}}
      </jenson-button>
      <jenson-button
          type="danger"
          @click="delAll"
          v-show="selectionList.length > 0"
      >
        {{getI18n("btn.deleteAll")}}
      </jenson-button>
    </template>

    <template #controls="{row}">
      <jenson-button
          type="success"
          @click="edit(row)"
      >
        {{getI18n("btn.edit")}}
      </jenson-button>
      <jenson-button
          type="warning"
          @click="goToAuth(row)"
      >
        {{getI18n("btn.role")}}
      </jenson-button>
      <jenson-button
          type="danger"
          @click="del(row)"
      >
        {{getI18n("btn.delete")}}
      </jenson-button>
    </template>
  </jenson-table>

  <jenson-edit-model
      v-model="editModel"
      title="用户"
      style="width: 400px"
      :submit-on-click="submit"
  >
    <el-form>
      <el-form-item
          label="名称"
      >
        <el-input v-model="roleVo.name"/>
      </el-form-item>
    </el-form>
  </jenson-edit-model>
</template>

<style scoped>

</style>