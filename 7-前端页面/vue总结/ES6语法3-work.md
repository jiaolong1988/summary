## Object

```ts
//清空 form 对象上所有原有属性，把 form 变成一个几乎空对象
Object.keys(form).forEach((k) => delete form[k])

//Object.assign(目标对象, 源对象)
//把后面源对象的属性合并复制到第一个目标对象上（原地修改目标，不创建新对象）
Object.assign(form, { parentId: currentId.value })
```

> // 1、原地删除form全部属性（保留reactive引用，不丢响应）
>
>  //2、给form设置parentId，值取自ref变量currentId

### 解释

- 第一行：`Object.keys(form).forEach((k) => delete form[k])`

```
Object.keys(form)
```

> `Object.keys(对象)`：**取出对象自身所有可枚举的 key，返回字符串数组**。
>
> 例：`form={name:'aaa',age:18}` → `["name","age"]`

```
.forEach((k) => delete form[k])
```

- `k` 就是每一个属性名字符串；

- `delete form[k]`：**删除 form 对象上这个属性**

  

✅**效果：清空 form 对象上所有原有属性，把 form 变成一个几乎空对象**

> ⚠️注意：**不是重新赋值 `form = {}`！！是原地删除原有字段** 关键点区别（Vue3 响应式大坑）：
>
> - `form = {}`：直接修改变量引用，如果 form 是 reactive 响应式对象，**会直接丢失响应性！**
> - `delete form[k]`：原地删除对象内部属性，**保留 reactive 对象引用，响应式还在** 👉这就是为什么很多 Vue3 里面不清空直接`form={}`，而是循环 delete 删除！