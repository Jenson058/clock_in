<script lang="ts" setup>
import {ColumnUtil} from "@/common/decoration/Column";
import {UserBo, UserSearchBo, UserVo} from "@/common/dto/user/User";
import {onMounted, ref} from "vue";
import JensonTable from "@/model/jenson-table/JensonTable.vue";
import PageConfig from "@/common/basic/PageConfig";
import UserApi from "@/api/user/UserApi";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import JensonEditModel from "@/model/jenson-edit-model/JensonEditModel.vue";
import {ElMessage} from "element-plus";
import {RoleSearchBo, RoleVo} from "@/common/dto/user/Role";
import RoleApi from "@/api/user/RoleApi";
import {getI18n} from "../../../../config/i18n";

const initTableData = ref<Array<UserVo>>([])
const pageConfig = ref<PageConfig>(new PageConfig())
const editModel = ref(false)
const roleModel = ref(false)
const userVo = ref(new UserVo())
const selectionList = ref<Array<UserVo>>([])
const roleList = ref<Array<RoleVo>>([])
const userRoleList = ref<Array<number>>([])

async function initTable() {
  let search = new UserSearchBo(pageConfig.value.page, pageConfig.value.pageSize)
  let result = await UserApi.pageAll(search)
  initTableData.value = result?.data?.content ?? []
  pageConfig.value.total = result?.data?.totalElements ?? 0
}

function onContextMenu(e: MouseEvent) {
  e.preventDefault();
  // ContextMenu.showContextMenu({
  //     x: e.x,
  //     y: e.y,
  //     items: [
  //
  //     ]
  // });
}

function add() {
  userVo.value = new UserVo();
  editModel.value = true
}

function edit(row: UserVo) {
  userVo.value = {...row}
  editModel.value = true
}

function submit() {
  let bo = {...userVo.value} as UserBo
  UserApi.edit(bo).then(async () => {
    ElMessage.success("保存成功")
    editModel.value = false
    roleModel.value = false
    await initTable()
  })
}

function roleSubmit() {
  userVo.value.roleList = userRoleList.value ?? []
  let bo = {...userVo.value} as UserBo
  UserApi.edit(bo).then(async () => {
    ElMessage.success("保存成功")
    editModel.value = false
    roleModel.value = false
    userRoleList.value = []
    await initTable()
  })
}

function delAll(){
  let search = new UserSearchBo()
  search.idList = selectionList.value.map(it => it.id ?? 0)
  UserApi.delAll(search).then(async ()=>{
    await initTable()
    ElMessage.success("删除成功")
  })
}

function del(row:UserVo){
  UserApi.del({id:row.id}).then(async ()=>{
    await initTable()
    ElMessage.success("删除成功")

  })
}

function addRole(row:UserVo){
  userVo.value = {...row}
  RoleApi.listAll(new RoleSearchBo())
      .then((res)=>{
        roleList.value = res?.content
      })
  userRoleList.value = row.roleList ?? []
  roleModel.value = true
}
</script>

<template>
  <div class="box" @contextmenu="onContextMenu($event)">
    <jenson-table
        :column-list="ColumnUtil.getColumnList(UserVo)"
        :table-data="initTableData"
        v-model:page-config="pageConfig"
        @init-table="initTable"
        @handle-selection-change="args => selectionList = args"
        column
        setting
        selection
        view
        refresh
    >
      <template #header-add>
        <jenson-button
            type="primary"
            @click="add"
            auth="user:add"
        >
          {{getI18n("btn.add")}}
        </jenson-button>
        <jenson-button
            v-show="selectionList.length > 0"
            type="danger"
            @click="delAll"
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
            @click="addRole(row)"
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
  </div>

  <jenson-edit-model
      v-model="editModel"
      type="drawer"
      title="新增/编辑 用户"
      :submit-on-click="submit"
  >
    <el-form>
      <el-form-item
          label="姓名"
          label-width="70"
      >
        <el-input v-model="userVo.name"/>
      </el-form-item>
      <el-form-item
          label="昵称"
          label-width="70"
      >
        <el-input v-model="userVo.nickname"/>
      </el-form-item>
      <el-form-item
          label="出生年月"
          label-width="70"
      >
        <el-date-picker
            v-model="userVo.birthday"
            type="date"
        />
      </el-form-item>
      <el-form-item
          label="性别"
          label-width="70"
      >
        <el-radio-group v-model="userVo.sex">
          <el-radio-button label="男"/>
          <el-radio-button label="女"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item
          label="手机号"
          label-width="70"
      >
        <el-input type="number" v-model="userVo.phone"/>
      </el-form-item>
      <el-form-item
          label="邮箱"
          label-width="70"
      >
        <el-input v-model="userVo.e_mail"/>
      </el-form-item>
    </el-form>
  </jenson-edit-model>
  <jenson-edit-model
      v-model="roleModel"
      title="绑定角色"
      style="width: 500px;"
      :submit-on-click="roleSubmit"
  >
    <el-checkbox-group
        v-model="userRoleList"
    >
      <el-checkbox
          v-for="role in roleList"
          :label="role.name"
          :value="role.id"
          border
      />
    </el-checkbox-group>
  </jenson-edit-model>
</template>

<style scoped>

</style>