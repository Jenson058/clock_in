<script setup lang="ts">

import {computed, onMounted, ref, watch} from "vue";
import {KeyValue} from "@/common/basic/KeyValue";
import AuthApi from "@/api/user/AuthApi";
import {useI18n} from "vue-i18n";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import {useRouter} from "vue-router";
import useRoleStore from "@/store/user/RoleStore";
import {ElMessage} from "element-plus";
import {RoleVo} from "@/common/dto/user/Role";
import RoleApi from "@/api/user/RoleApi";
import {Back} from "@element-plus/icons-vue";
import {getI18n} from "../../../../config/i18n";

const authAll = ref<Array<KeyValue<string, Array<string>>>>([])
const authThis = ref([])
const router = useRouter()
const roleStore = useRoleStore();


onMounted(async ()=>{
  initAuthAll()
  initAuth()
})

function initAuthAll(){
  AuthApi.all()
      .then((result) => {
        authAll.value =  result.content;
      })
}

function initAuth(){
  let id = {id:roleStore.getRoleVo().id ?? localStorage.getItem("roleId")}
  RoleApi.get(id)
      .then(result => {
        authThis.value = result?.data?.authAll ?? []
        if (!roleStore.getRoleVo().id){
          roleStore.setRoleVo(result?.data)
        }
      })
}

async function backs(){
  await router.push("role")
}

async function submit(){
  let bo = {...roleStore.getRoleVo()} as RoleVo
  bo.authAll = authThis.value
  console.log(bo)
  RoleApi.edit(bo).then(async () => {
    ElMessage.success("保存成功")
  })
}
</script>

<template>
  <jenson-button
      class="btn"
      type="info"
      @click="backs"
  >
    <el-icon><Back /></el-icon>返回角色
  </jenson-button>
  <jenson-button
      type="primary"
      class="btn"
      @click="submit"
  >
    保存
  </jenson-button>
  <div class="auth">
    <el-card
        class="card"
        v-for="auth in authAll"
    >
      <template #header>
        {{ getI18n("auth.key." + auth.key) }}
      </template>
      <el-checkbox-group v-model="authThis">
        <el-checkbox class="checkbox" v-for="item in auth.value" :label="getI18n('auth.controls.' + item)" :value="auth.key+':'+item" border />
      </el-checkbox-group>
    </el-card>
  </div>
</template>

<style scoped>
.auth{
  display: flex;
  flex-wrap: wrap;
}
.card{
  margin: 5px;
  width: calc(16.66% - 20px)
}
.checkbox{
  min-width: 100px;margin: 2px
}
.btn{
  width: 100px;
  height: 40px;
  border-radius: 40px;
  font-size: 14px;
}
</style>