## vscode vite 断点配置

- launch.json

  > 地址：项目名/.vscode/launch.json

```
{
    // 使用 IntelliSense 了解相关属性。 
    // 悬停以查看现有属性的描述。
    // 欲了解更多信息，请访问: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [

        {
            "type": "chrome",
            "request": "launch",
            "name": "针对 localhost 启动 Chrome",
            "url": "http://localhost:5173",
            "webRoot": "${workspaceFolder}/src",
            "sourceMaps": true,
            "disableNetworkCache": true,
            "runtimeArgs": ["--incognito"],
            "enableContentValidation": false,
            "skipFiles": [
                "${workspaceFolder}/node_modules/**"
            ]
       
        }
    ]
}
```

- vite.config.ts

  > 项目名/vite.config.ts

```
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

//vant
import Components from 'unplugin-vue-components/vite';
import { VantResolver } from 'unplugin-vue-components/resolvers';

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    Components({
      resolvers: [VantResolver()],
    }),
  ],
  
// 断点配置开始  
  build: {
    sourcemap: true,
  },
// 断点配置结束  

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  }
})

```

