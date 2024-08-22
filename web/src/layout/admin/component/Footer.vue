<script setup>
import {Expand, Fold, HomeFilled} from "@element-plus/icons-vue";
import {computed} from "vue";

const props = defineProps({
    isCollapse: {
        type: Boolean,
        default: false
    }
})
const emit = defineEmits(["update:isCollapse"])
const isCollapse = computed({
    get() {
        return props.isCollapse
    },
    set(value) {
        emit("update:isCollapse", value)
    }
})

function updateIsCollapse() {
    isCollapse.value = !isCollapse.value
}
</script>

<template>
    <el-row style="line-height: 60px;background-color: #e6e6e6">
        <el-col :span="1">
            <el-icon
                    size="25"
                    style="margin-left: 10px;"
                    @click="updateIsCollapse"
            >
                <Expand
                        v-if="isCollapse"
                />
                <Fold
                        v-else
                />
            </el-icon>
        </el-col>
        <el-col :span="22">
            <div style="text-align: center">COPYRIGHT © Jenson</div>
        </el-col>
        <el-col :span="1">
            <el-icon size="25">
                <HomeFilled/>
            </el-icon>
        </el-col>
    </el-row>
</template>

<style scoped>

</style>