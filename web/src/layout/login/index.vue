<script lang="ts" setup>
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import {onMounted, ref} from "vue";
import {LoginBo, LoginVo} from "@/common/dto/system/login";
import LoginApi from "@/api/system/LoginApi";
import useUserStore from "@/store/user/UserStore";
import router from "@/config/router";

const loginBo = ref<LoginBo>(new LoginBo());
const userStore = useUserStore()
async function login() {
    LoginApi.login(loginBo.value).then(async (it)=> {
        let loginVo = it?.content ?? new LoginVo()
        localStorage.setItem("token", loginVo?.token as string)
        localStorage.setItem("userId",loginVo?.userVo?.id ?? "0" as string)
        userStore.setUserVo(loginVo.userVo)
        await router.push("/admin/home/home_index")
    });

}
</script>

<template>
    <div class="body">
        <div class="main">
            <div class="image"></div>
            <div class="login">
                <div style="">
                    <div style="font-size: 20px;font-weight: bolder">账号/密码登录</div>
                    <el-form label-position="top" style="margin-top: 20px;">
                        <el-form-item label="账号">
                            <el-input v-model="loginBo.code" style="width: 300px;height: 50px;"/>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input type="password" v-model="loginBo.password" style="width: 300px;height: 50px;"/>
                        </el-form-item>
                    </el-form>
                    <jenson-button size="default" style="width: 100%;" @click="login">
                        登录
                    </jenson-button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.body {
    background: #1e4c9a;
    height: 100vh;
    align-items: center;
    justify-content: space-around;
    display: flex;
}

.main {
    width: 800px;
    height: 500px;
    background-color: #e6e6e6;
    display: flex;
}

.image {
    width: 400px;
    height: 500px;
    background-image: url("@/assets/image/R.png");
    background-size: 100%;
}

.login {
    width: 400px;
    height: 500px;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>