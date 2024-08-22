<script lang="ts" setup>

import PageConfig from "@/common/basic/PageConfig";
import {onMounted} from "vue";

interface Props{
    pageConfig:PageConfig
}

onMounted(async ()=>{
    await initTable()
})

const props = withDefaults(defineProps<Props>(),{
    pageConfig: () => new PageConfig()
})

const emit = defineEmits(["initTable"])

async function initTable(){
    emit("initTable")
}
</script>

<template>
    <div style="width: 100%">
        <el-pagination
                v-model:current-page="props.pageConfig.page"
                v-model:page-size="props.pageConfig.pageSize"
                :page-sizes="[50,100,200,500,1000]"
                :default-page-size="props.pageConfig.pageSize"
                background
                layout="total, sizes, prev, pager, next, jumper"
                :total="props.pageConfig.total"
                @change="initTable"
        />
    </div>
</template>

<style scoped>
.el-pagination{
    display: flex;
    justify-content: center;
    margin-top: 5px;
}
</style>