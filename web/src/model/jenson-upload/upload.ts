// @ts-nocheck
import SparkMD5 from "spark-md5";
import {FileBo} from "@/common/dto/file/File";
import FileApi from "@/api/file/FileApi";


//分片大小为5MB
const CHUNK_SIZE = 2 * 1024 * 1024

/**
 * @description: 文件加密处理
 */
export const getMD5 = (file: any): Promise<string> => new Promise((resolve, reject) => {
    const spark = new SparkMD5.ArrayBuffer();
    // 获取文件二进制数据
    const fileReader = new FileReader();
    fileReader.readAsArrayBuffer(file); // file 就是获取到的文件
    // 异步执行函数
    fileReader.addEventListener('load', (e: any) => {
        spark.append(e.target.result);
        const md5: string = spark.end();
        resolve(md5);
    });
    fileReader.addEventListener('error', (e) => {
        reject(e);
    });
});

// 上传
export async function upload(file:File,fileBo:FileBo){
    fileBo.mdKey = await getMD5(file)
    let count = Math.ceil(file.size / CHUNK_SIZE)
    fileBo.count = count
    for (let i = 0; i < count; i++) {
        let start = i * CHUNK_SIZE
        let end = Math.min(start + CHUNK_SIZE, file.size)
        fileBo.number_ = i + 1;
        let form = new FormData()
        form.append("file", file.slice(start, end))
        form.append("fileBo", new Blob([JSON.stringify(fileBo)], {type: 'application/json'}))
        let result = await FileApi.upload(form)
        if (result?.content?.finish) {
            return result.content.id
        }
        // if (result?.content?.number_ != i) {
        //     i = result?.content?.number_ as number
        // }
    }
}