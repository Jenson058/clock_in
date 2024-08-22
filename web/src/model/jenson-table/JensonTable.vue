<script lang="ts" setup>
import {Column} from "@/common/decoration/Column";
import JensonPagination from "@/model/jenson-pagination/JensonPagination.vue";
import PageConfig from "@/common/basic/PageConfig";
import JensonButton from "@/model/jenson-button/JensonButton.vue";
import {onMounted, ref} from "vue";
import {Hide, Refresh, Setting, View} from "@element-plus/icons-vue";
import {ObjectUtil} from "@/util/ObjectUtil";
import {ElMessage} from "element-plus";
import {useI18n} from "vue-i18n";

onMounted(() => {
  handleCheckAllChange(true)
  console.log(props.columnList)
})

interface Props {
  columnList?: Array<Column>
  tableData?: any[]
  pageConfig: PageConfig
  columnMinWidth?:number
  column?:boolean
  selection?:boolean
  index?:boolean
  setting?:boolean
  view?:boolean
  refresh?:boolean
}

const props = withDefaults(defineProps<Props>(), {
  pageConfig: () => new PageConfig()
})
const openSetting = ref(false)
const isIndeterminate = ref(false)
const boxColumn = ref<Array<string>>([])
const checkAll = ref(false)

const columnList = ref<Array<Column>>([])

const emit = defineEmits(["initTable","handleSelectionChange"])
const {locale} = useI18n()

async function initTable() {
  await emit("initTable")
}

function handleCheckAllChange(val: boolean) {
  checkAll.value = val
  let list = props?.columnList ?? []
  boxColumn.value = val ? list.map(it => it.label ?? "") : []
  isIndeterminate.value = false
  changeChecked(list, boxColumn.value)
}


const handleCheckedCitiesChange = (value: string[]) => {
  let list = props?.columnList ?? []
  const checkedCount = value.length
  checkAll.value = checkedCount === list.length
  isIndeterminate.value = checkedCount > 0 && checkedCount < list.length
  changeChecked(list, value)
}

function changeChecked(list: Array<Column>, value: string[]) {
  columnList.value = []
  for (let box of list) {
    for (let val of value) {
      if (ObjectUtil.isEqual(box.label ?? "", val)) {
        columnList.value.push(box)
      }
    }
  }
}

async function refresh(){
  await initTable()
  ElMessage.success(new Date() + "刷新成功")
}

function handleSelectionChange(val:Array<any>){
  emit("handleSelectionChange",val)
}

function getI18nLabel(column:Column){
  switch (locale.value){
    case "zh_CN": return column.label;
    case "en_US": return column.prop
  }
}
</script>

<template>
  <div class="header">
    <div class="header-add">
      <slot name="header-add"/>
    </div>
    <div class="header-setting">
      <div class="header-setting-btn" v-if="props.refresh">
        <jenson-button @click="refresh">
          <el-icon>
            <Refresh/>
          </el-icon>
        </jenson-button>
      </div>
      <div class="header-setting-btn" v-if="props.view">
        <jenson-button
            @click="()=>{
        openSetting = !openSetting
      }">
          <el-icon v-if="openSetting">
            <View/>
          </el-icon>
          <el-icon v-else>
            <Hide/>
          </el-icon>
        </jenson-button>
      </div>
      <div class="header-setting-btn" v-if="props.setting">
        <el-dropdown trigger="click">
          <jenson-button>
            <el-icon>
              <Setting/>
            </el-icon>
          </jenson-button>

          <template #dropdown>
            <div class="setting-dropdown">
              <div class="setting-dropdown-item">
                <span>表格字段</span>
                <div>
                  <el-checkbox
                      v-model="checkAll"
                      label="全部"
                      :indeterminate="isIndeterminate"
                      @change="handleCheckAllChange"
                  />
                  <el-checkbox-group v-model="boxColumn" @change="handleCheckedCitiesChange">
                    <el-checkbox v-for="box in props.columnList" :label="box.label ?? ''"/>
                  </el-checkbox-group>
                </div>
              </div>
              <div class="setting-dropdown-item">
                <span>表格属性</span>
              </div>
            </div>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
  <div :class="openSetting?'setting-open':'setting-not-open'">
    <slot name="setting-hide"/>
  </div>
  <el-table
      :class="!openSetting?'table-setting':'table-not-setting'"
      :data="props.tableData"
      border
      header-row-class-name="table-header"
      @selection-change="handleSelectionChange"
  >
    <el-table-column v-if="props.selection" type="selection" width="55" />
    <el-table-column
        v-if="props.index"
        :fixed="'left'"
        type="index"
        label="序号"
        width="80"
        :index="(index:number) => index +1"
    />
    <el-table-column
        v-for="column in columnList"
        :label="column.label"
        :prop="column.prop"
        :width="Number(column.width)"
    />
    <slot name="column"></slot>
    <el-table-column
        label="操作"
        :fixed="'right'"
        v-if="column"
        min-width="150px"
    >
      <template #default="{row}">
        <slot name="controls" :row="row"></slot>
      </template>
    </el-table-column>
    <template #empty>
      <el-empty description="暂无数据"/>
    </template>
  </el-table>
  <jenson-pagination v-model:page-config="props.pageConfig" @init-table="initTable"/>
</template>

<style scoped>
.table-setting {
  margin-top: 5px;
  border: 1px solid #e8e8e8;
  border-right: 1px solid #e8e8e8 !important;
  border-top: 0;
  height: calc(100vh - 230px) !important;
  transition: height 1s;
}

.table-not-setting {
  margin-top: 5px;
  border: 1px solid #e8e8e8;
  border-right: 1px solid #e8e8e8 !important;
  border-top: 0;
  height: calc(100vh - 330px) !important;
  transition: height 1s;
}

.table tr {
  --el-fill-color-lighter: #edf5f5;
}


.table-header {
  color: #000000;
  --el-table-header-bg-color: #edf5f5;
}

.el-table {
  --el-table-header-bg-color: #edf5f5;
}

.header {
  height: 30px;
  display: flex;
  justify-content: space-between;
}

.header-setting {
  margin-right: 10px;
  display: flex;
}

.setting-open {
  height: 100px;
  transition: height 1s;
}

.setting-not-open {
  height: 0px;
  transition: height 1s;
}

.header-setting-btn {
  margin-left: 10px;
}

.setting-dropdown {
  width: 200px;
}

.setting-dropdown-item {
  padding: 10px;
}
</style>