<script setup lang="ts">
import {Editor, Toolbar} from "@wangeditor/editor-for-vue";
import '@wangeditor/editor/dist/css/style.css'
import {computed, onBeforeUnmount, ref, shallowRef} from "vue";
import {upload} from "@/model/jenson-upload/upload";
import {FileBo, FileVo} from "@/common/dto/file/File";
import moment from "moment"; // 引入 css

interface Props{
  modelValue:string
}

const props = withDefaults(defineProps<Props>(),{

})

const emit = defineEmits(["update:modelValue"])

const modelValue = computed({
  get() {
    return props.modelValue
  }, set(value) {
    console.log(value)
    emit("update:modelValue",value)
  }
})

const editorRef = shallowRef()
const valueHtml = ref('<p>hello</p>')
const mode = ref("default")

const toolbarConfig = {}
const editorConfig = {placeholder: '请输入内容...', MENU_CONF: {}}

function handleCreated(editor) {
  editorRef.value = editor
}

onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

editorConfig.MENU_CONF['uploadImage'] = {
  async customUpload(file: File, insertFn: InsertFnType) {
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
    insertFn(FileVo.getUploadUrl(id))
  }
}
</script>

<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="modelValue"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
    />
  </div>
</template>

<style scoped>

</style>