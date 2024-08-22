import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from "path";
const __dirname = path.resolve();

const pathResolve = (dir) => path.resolve(__dirname, dir);


// https://vitejs.dev/config/
export default ({mode}) =>{
    const env = loadEnv(mode,process.cwd())
    return defineConfig({
        plugins: [vue()],
        resolve: {
            alias:{
                "@":pathResolve("./src")
            }
        },
        css:{
          preprocessorOptions:{
              scss:{
                  additionalData:"@/assets/style/all.scss"
              }
          }
        },
        server:{
            //服务器代理
            proxy:{
                "^api":{
                    target: env.VITE_BACKEND_SERVER_URL ?? "http://localhost:8080",
                    secure:false,
                    changeOrigin:true
                }
            }
        }
    })
}
