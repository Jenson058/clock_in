import {defineConfig,loadEnv} from "vite";
import uni from "@dcloudio/vite-plugin-uni";
import path from "path";

// https://vitejs.dev/config/
const __dirname = path.resolve();
const pathResolve = (dir) => path.resolve(__dirname, dir);

export default ({mode}) => {
    const env = loadEnv(mode, process.cwd())
    return defineConfig({
        plugins: [uni()],
        resolve: {
            alias:{
                "@":pathResolve("./src")
            }
        },
        server:{
            //服务器代理
            proxy:{
                "^api":{
                    target: env.VITE_BACKEND_SERVER_URL ?? "http://localhost:8888",
                    secure:false,
                    changeOrigin:false
                }
            }
        }
    });
}
