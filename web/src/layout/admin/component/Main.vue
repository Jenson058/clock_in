<script lang="ts" setup>

import {useRouter} from "vue-router";
import {onMounted, ref, toRaw} from "vue";
import {useI18n} from "vue-i18n";
import useSystemStore from "@/store/system/SystemStore";
import UserApi from "@/api/user/UserApi";
import useUserStore from "@/store/user/UserStore";
import {UserVo} from "@/common/dto/user/User";
import LogApi from "@/api/clock/LogApi";

const {t} = useI18n();  // 解构调用函数
const systemStore = useSystemStore()

onMounted(()=>{
})
</script>

<template>
  <div>
    <el-breadcrumb style="margin-bottom: 10px" separator=">">
      <el-breadcrumb-item
          v-for="router in systemStore.getRouterList()"
          :to="router.path"
      >
        {{ router.meta.title }}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<style scoped>

</style>