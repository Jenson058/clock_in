<script lang="ts" setup>
import {onMounted, ref, toRaw} from "vue";
import {RouteRecordRaw, useRouter} from "vue-router";
import {ListUtil} from "@/util/ListUtil.ts";
import {useI18n} from "vue-i18n";
import useSystemStore from "@/store/system/SystemStore.js";
import {getI18n} from "@/config/i18n";
import {UserVo} from "@/common/dto/user/User";
import useUserStore from "@/store/user/UserStore";

const menuList = ref([])
const router = useRouter()
const systemStore = useSystemStore()
const userStore = useUserStore()

const props = defineProps({
  isCollapse: {
    type: Boolean,
    default: false
  }
})

function getRouterAside() {
  //todo:权限信息变更后，需要刷新才能把路由完全展示出来，router.options.routes没有获取到全部路由
  let aside = router.options.routes.filter(it => it.name === 'admin')[0]
  getNoFilterChildren(aside)
  menuList.value = aside.children.filter(it => !ListUtil.isEmpty(it.children))
}

function getNoFilterChildren(aside:RouteRecordRaw) {
  if (!ListUtil.isEmpty(aside.children)) {
    aside.children = aside.children
        .filter(it => it.meta.isShow !== false)
    if (userStore.getUserVo().code !== "admin") {
      aside.children = aside.children
          .filter(it => !it.meta.auth || ListUtil.exist(userStore.getUserVo()?.authList, it.meta.auth))
    }
    aside.children.forEach(it => {
      getNoFilterChildren(it)
    })
  }
}

onMounted(() => {
  getRouterAside()
})
</script>

<template>
  <el-menu
      active-text-color="#ffd04b"
      background-color="#545c64"
      text-color="#fff"
      class="el-menu-vertical-demo text-no-select"
      default-active="2"
      :collapse="props.isCollapse"
  >
    <el-sub-menu
        v-for="(menu,index) in menuList"
        :key="index"
        :index="String(index)"
    >

      <template #title>
        <img
            style="margin-left: -5px;margin-right: 10px"
            :src="menu.icon"/>
        <span>
                    {{ menu.meta.title }}
                </span>
      </template>
      <el-menu-item
          v-for="menuChildren in menu.children"
      >
        <router-link :to="{name: menuChildren.name }">
          <span>{{ menuChildren.meta.title }}</span>
        </router-link>
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<style scoped>
.el-scrollbar {
  height: 100%;
  background-color: #545c64;
}

.el-menu {
  height: 100%;
}

.router-link-active {
  text-decoration: none;
  color: yellow;
}

a {
  text-decoration: none;
  color: white;
}

</style>