<script lang="ts" setup>
// @ts-nocheck
import {Grid, SwitchFilled} from "@element-plus/icons-vue";

import {useI18n} from "vue-i18n";
import {computed, onMounted, ref, toRaw} from "vue";
import {zh_CN} from "@/config/i18n/zh_CN";
import {getI18n, i18n} from "@/config/i18n";  // 引用组件
import {useRouter} from "vue-router";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import {LoginBo} from "@/common/dto/system/login";
import LoginApi from "@/api/system/LoginApi";
import {ElMessage} from "element-plus";
import useUserStore from "@/store/user/UserStore";
import {UserBo, UserVo} from "@/common/dto/user/User";
import UserApi from "@/api/user/UserApi";
import JensonUpload from "@/model/jenson-upload/JensonImageUpload.vue";
import {FileVo} from "@/common/dto/file/File";

const loginBo = ref<LoginBo>(new LoginBo())
const router = useRouter()

const {locale} = useI18n();  // 解构调用函数

const settingDrawer = ref(false)
const userStore = useUserStore()
const isUpdate = ref(false)
const uploadIsShow = ref(false)
const activeName = ref("BasicInformation")

const props = defineProps({
  userVo:UserVo
})

const emit = defineEmits(["update:userVo"])

const userVo = computed({
  get(){
    return props.userVo
  },
  set(value){
    emit("update:userVo",value)
  }
})

onMounted(() => {
  locale.value = localStorage.getItem("i18n")
  changeLanguage(locale.value)
})

function changeLanguage(value: "zh_CN" | "en_US") {
  locale.value = value;
  localStorage.setItem("i18n",locale.value)
  let title = toRaw(router).currentRoute.value.name as string ?? "admin";
}

async function updatePassword() {
  LoginApi.updatePassword(loginBo.value)
      .then(() => {
        ElMessage.success("修改成功，请重新登录")
        router.push("/login")
      })
}

function cancel(){
  userVo.value = {...userStore.getUserVo()}
  isUpdate.value = false
  uploadIsShow.value = false
  setTimeout(()=>{
    uploadIsShow.value = true
  },2)
}

async function submit(){
  let bo = {...userVo.value} as UserBo
  UserApi.edit(bo).then((result)=>{
    ElMessage.success("保存成功")
    let userVo_ = result?.data
    userStore.setUserVo(userVo_)
    userVo.value = {...userVo_}
    isUpdate.value = false
  })
}

const avatar = computed({
  get(){
    return userStore.getUserVo().avatar == undefined ?
        "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" :
        FileVo.getUploadUrl(userStore.getUserVo().avatar)
  }
})
</script>

<template>
  <div class="header">
    <div style="margin-left: 10px;">
      <el-icon size="40">
        <SwitchFilled/>
      </el-icon>
    </div>
    <div class="header-right">
      <div class="header-right-item">
        <el-dropdown trigger="hover">
          <el-icon size="30">
            <Grid/>
          </el-icon>
          <template #dropdown>
            <el-dropdown-item @click="changeLanguage('zh_CN')">中文</el-dropdown-item>
            <el-dropdown-item @click="changeLanguage('en_US')">英文</el-dropdown-item>
          </template>
        </el-dropdown>
      </div>
      <div class="header-right-item">
        <el-dropdown trigger="hover">
          <el-avatar
              @click="settingDrawer = true"
              :src="avatar"
          />
        </el-dropdown>
      </div>
    </div>
  </div>

  <el-drawer
      @open="()=>{
        isUpdate = false
        activeName = 'BasicInformation'
        userVo = {...userStore.getUserVo()}
        uploadIsShow = true
      }"
      @close="()=>{
        uploadIsShow = false
      }"
      v-model="settingDrawer"
  >
    <template #header>
      <div style="height: 150px;">
        <div class="drawer_dropdown_image" style="background-color: #ffd322;">

        </div>
        <div class="drawer_dropdown_image" style="background-color: #e29857;top: 100px">

        </div>
        <div class="drawer_dropdown_image" style="top: 50px">
          <el-avatar
              style="width: 100px;height: 100px;"
              :src="avatar"
          />
          <div>
            <span style="color: black">{{userStore.getUserVo().nickname}}</span>
          </div>
        </div>
      </div>
    </template>
    <el-tabs
        tab-position="left"
        v-model="activeName"
    >
      <el-tab-pane name="BasicInformation" label="基本信息">
        <el-form-item label-width="80" label="头像">
          <jenson-upload
              v-if="uploadIsShow"
              :disabled="!isUpdate"
              v-model="userVo.avatar"
          />
        </el-form-item>
        <el-form-item label-width="80" label="账号">
          <el-input
              disabled
              v-model="userVo.code" style="width: 300px;"/>
        </el-form-item>
        <el-form-item label-width="80" label="姓名">
          <el-input
              :disabled="!isUpdate"
              v-model="userVo.name" style="width: 300px;"/>
        </el-form-item>
        <el-form-item label-width="80" label="昵称">
          <el-input
              :disabled="!isUpdate"
              v-model="userVo.nickname" style="width: 300px;"/>
        </el-form-item>
        <el-form-item label-width="80" label="出生日期">
          <el-date-picker
              :disabled="!isUpdate"
              v-model="userVo.birthday"
              type="date"
          />
        </el-form-item>
        <el-form-item label-width="80" label="性别">
          <el-radio-group
              :disabled="!isUpdate"
              v-model="userVo.sex">
            <el-radio-button label="男"/>
            <el-radio-button label="女"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label-width="80" label="手机号">
          <el-input
              :disabled="!isUpdate"
              v-model="userVo.phone" style="width: 300px;"/>
        </el-form-item>
        <el-form-item label-width="80" label="邮箱">
          <el-input
              :disabled="!isUpdate"
              v-model="userVo.e_mail" style="width: 300px;"/>
        </el-form-item>
        <jenson-button
            style="width: 93%;"
            v-if="!isUpdate"
            type="success"
            @click="isUpdate = true"
        >
          修改
        </jenson-button>
        <div v-else>
          <jenson-button type="danger" @click="cancel" style="width: 45%;">
            撤销
          </jenson-button>
          <jenson-button type="warning" @click="submit" style="width: 45%;">
            保存
          </jenson-button>
        </div>
      </el-tab-pane>
      <el-tab-pane name="ChangePassword" label="重置密码">
        <div style="padding-left: 10px;">
          <el-form label-position="right" style="margin-top: 20px;">
            <el-form-item label-width="110" label="账号">
              <el-input v-model="loginBo.code" style="width: 300px;"/>
            </el-form-item>
            <el-form-item label-width="110" label="旧密码">
              <el-input type="password" v-model="loginBo.oldPassword" style="width: 300px;"/>
            </el-form-item>
            <el-form-item label-width="110" label="新密码">
              <el-input type="password" v-model="loginBo.password" style="width: 300px;"/>
            </el-form-item>
            <el-form-item label-width="110" label="再次输入新密码">
              <el-input type="password" v-model="loginBo.subPassword" style="width: 300px;"/>
            </el-form-item>
          </el-form>
          <jenson-button @click="updatePassword" style="width: 100%;" type="success" size="large">
            提交
          </jenson-button>
        </div>
      </el-tab-pane>
    </el-tabs>
    <template #footer>
      <jenson-button
          type="danger"
          size="large"
          @click="async ()=>{
                        ElMessage.success('退出成功')
                        await router.push('/login')
                    }"
      >
        退出登录
      </jenson-button>
    </template>
  </el-drawer>
</template>

<style scoped>
.header {
  height: 60px;
  width: 100%;
  background-color: #e3e3e3;
  justify-content: space-between;
  display: flex;
  align-items: center;
}

.header-right {
  display: flex;
  margin-right: 10px;
  align-items: center;
}

.header-right-item {
  margin-left: 15px;
}

.dropdown_image {
  width: 100px;
  height: 60px;
  position: absolute;
  text-align: center;
  top: 0;
  left: 0;
}

.drawer_dropdown_image {
  width: 100%;
  height: 100px;
  position: absolute;
  text-align: center;
  top: 0;
  left: 0;
}

.el-drawer__close-btn {
  display: none !important;
}
</style>