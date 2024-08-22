<script lang="ts" setup>
import Aside from "@/layout/admin/component/Aside.vue";
import Footer from "@/layout/admin/component/Footer.vue";
import {onMounted, ref, toRaw} from "vue";
import Header from "@/layout/admin/component/Header.vue";
import Main from "@/layout/admin/component/Main.vue";
import useUserStore from "@/store/user/UserStore";
import UserApi from "@/api/user/UserApi";
import {UserVo} from "@/common/dto/user/User";
import {ListUtil} from "@/util/ListUtil";

const isCollapse = ref(false)


const userStore = useUserStore()
const userVo = ref<UserVo>()

onMounted(()=>{
  userVo.value = userStore.getUserVo()
})

</script>

<template>
    <el-container v-if="userVo">
        <el-header>
            <Header v-model:user-vo="userVo"/>
        </el-header>
        <el-container class="container">
            <el-aside
                :class=" isCollapse === true? 'el-aside-coles' : 'el-aside-open' "
            >
                <Aside
                    v-model:isCollapse="isCollapse"
                    :user-vo="userVo"
                />
            </el-aside>
            <el-container>
                <el-main>
                    <Main/>
                </el-main>
                <el-footer>
                    <Footer
                        v-model:is-collapse="isCollapse"
                    />
                </el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>
<style scoped>
.el-header{
    height: 60px;
    padding: 0;
}

.el-main{
    padding: 10px 0 0 10px;
}

.el-footer{
    padding: 0;
}

.container{
    height: calc(100vh - 60px);
}

.el-aside-coles{
    width: 65px;
    transition: width 0.5s;
}
.el-aside-open{
    width: 200px;
    transition: width 0.5s 0.2s;
}
</style>