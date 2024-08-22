<script setup lang="ts">

// @ts-nocheck
import {computed, onMounted, ref, watch} from "vue";
import {UploadProps, UploadRequestOptions, UploadUserFile} from "element-plus";
import {Download, UploadFilled} from "@element-plus/icons-vue";
import FileApi from "@/api/file/FileApi";
import {FileBo, FileSearchBo, FileVo} from "@/common/dto/file/File";
import {ListUtil} from "@/util/ListUtil";
import SparkMD5 from "spark-md5"
import {getMD5, upload} from "@/model/jenson-upload/upload";

const fileList = ref<Array<UploadUserFile>>([])
//分片大小为5MB
const CHUNK_SIZE = 2 * 1024 * 1024

interface Props {
  type?: "file" | "image"
  modelValue?: string,
  disabled?: boolean
  limit?: number
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  limit: 1
})

const emit = defineEmits(["update:modelValue"])

const modelValue = computed({
  get() {
    return props.modelValue
  }, set(value) {
    emit("update:modelValue", value)
  }
})

onMounted(async () => {
  await changFileList(modelValue.value)
})

async function changFileList(value) {
  if (!value) {
    fileList.value = []
    return
  }
  const idList = ListUtil.stringToNumberList(value)
  let searchBo = new FileSearchBo()
  searchBo.idList = idList
  const result = await FileApi.listAll(searchBo)
  const data = result?.content
  fileList.value = data?.map(vo => FileVo.toUploadFile(vo) ?? [])
}



function toSuffix(name: string) {
  return name.split(".").slice(-1)[0];
}

async function uploadFile(option: UploadRequestOptions): UploadProps['httpRequest'] {
  let file = option.file
  let fileBo = new FileBo(
      option.file.name,
      "",
      option.file.type,
      toSuffix(option.file.name),
      0,
      0
  )
  return await upload(file,fileBo)
}

watch(fileList, (newValue) => {
  modelValue.value = ListUtil.listToString(newValue.map(it => it.response));
}, {
  deep: true
})

function display() {
  return fileList.value.length >= props.limit;
}
</script>

<template>
  <el-upload
      action="/"
      v-model:file-list="fileList"
      :multiple="true"
      list-type="picture-card"
      :limit="props.limit"
      :http-request="uploadFile"
      :disabled="props.disabled"
      :class="display() || props.disabled?'display':''"
      drag
  >
    <el-icon size="50" class="el-icon--upload">
      <upload-filled/>
    </el-icon>
  </el-upload>
  <span v-if="fileList.length ==0 && props.disabled">暂无数据</span>
</template>

<style scoped>

.display >>> .el-upload--picture-card {
  display: none;
}

</style>