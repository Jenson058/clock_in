<script setup lang="ts">

import {MdEditor} from "md-editor-v3";
import {computed} from "vue";
import sanitizeHtml from 'sanitize-html';
import moment from "moment/moment";
import {FileBo, FileVo} from "@/common/dto/file/File";
import {upload} from "@/model/jenson-upload/upload";

interface Props{
  modelValue:string
  showOff:boolean
}

const props = withDefaults(defineProps<Props>(),{
  showOff:false
})

const emit = defineEmits(["update:modelValue"])

const modelValue = computed({
  get(){
    return props.modelValue
  },set(value){
    emit("update:modelValue",value)
  }
})

async function onUploadImg(files:Array<File>,callback){
  let result = []
  for (let file of files){
    let date = moment().format("YYYY-MM-DD_HH-mm-ss")
    let fileBo = new FileBo(
        date + "截图.png",
        "",
        "image/png",
        "png",
        0,
        0
    );
    let id = await upload(file, fileBo)
    result.push({
      url:FileVo.getUploadUrl(id),
      alt:date + "截图.png",
      title:date + "截图.png"
    })
  }

  callback(
      result
  )
}
</script>

<template>
  <MdEditor
      :class="showOff?'show':''"
      v-model="modelValue"
      @on-upload-img="onUploadImg"
      :show-toolbar-name="true"
      :show-code-row-number="true"
  >
  </MdEditor>
</template>

<style scoped>
.show >>> .md-editor-toolbar-wrapper{
  display: none;
}

.show >>> .md-editor-input-wrapper{
  display: none;
}
</style>