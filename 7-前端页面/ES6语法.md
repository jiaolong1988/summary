好的！ES6（ECMAScript 2015）是 JavaScript 的一个重要版本，引入了许多新特性，极大地提升了语言的表达能力和开发效率。以下是 ES6 的所有主要特性及其详细案例，帮助你全面理解。

* * *

### **1\. let 和 const**

#### **(1) let**

*   替代 `var`，具有块级作用域。
*   示例：
    
    ```javascript
    for (let i = 0; i < 3; i++) {
      console.log(i); // 输出 0, 1, 2
    }
    console.log(i); // ReferenceError: i is not defined
    ```

    
    
    

#### **(2) const**

*   声明常量，值一旦赋值不可更改。
*   示例：
    
    ```javascript
    const PI = 3.14;
    PI = 3.1415; // TypeError: Assignment to constant variable.
    ```

    
    
    

* * *

### **2\. 解构赋值**

#### **(1) 对象解构**

*   从对象中提取属性。
*   示例：
    
    ```javascript
    const person = { name: 'Alice', age: 25 };
    const { name, age } = person;
    console.log(name, age); // 输出 Alice 25
    ```

    
    
    

#### **(2) 数组解构**

*   从数组中提取元素。
*   示例：
    
    ```javascript
    const [a, b] = [10, 20];
    console.log(a, b); // 输出 10 20
    ```

    
    
    

#### **(3) 默认值**

*   设置默认值。
*   示例：
    
    ```javascript
    const [x = 5] = [];
    console.log(x); // 输出 5
    ```

    
    
    

* * *

### **3\. 模板字符串**

*   使用反引号（\`\`）代替单引号或双引号，支持嵌入变量。
*   示例：
    
    ```javascript
    const name = 'Bob';
    const greeting = `Hello, ${name}!`;
    console.log(greeting); // 输出 Hello, Bob!
    ```

    
    
    

* * *

### **4\. 箭头函数**

*   简洁的函数定义方式。
*   示例：
    
    ```javascript
    const square = x => x * x;
    console.log(square(5)); // 输出 25
    
    const add = (x, y) => x + y;
    console.log(add(3, 4)); // 输出 7
    ```

    
    
    

* * *

### **5\. 默认参数**

*   函数参数可以设置默认值。
*   示例：
    
    ```javascript
    function greet(name = 'Guest') {
      return `Hello, ${name}`;
    }
    console.log(greet()); // 输出 Hello, Guest
    ```

    
    
    

* * *

### **6\. 剩余参数**

*   将多个参数收集为数组。
*   示例：
    
    ```javascript
    function sum(...numbers) {
      return numbers.reduce((total, num) => total + num, 0);
    }
    console.log(sum(1, 2, 3)); // 输出 6
    ```

    
    
    

* * *

### **7\. 扩展运算符**

*   将数组或对象展开。
*   示例：
    
    ```javascript
    const arr1 = [1, 2];
    const arr2 = [...arr1, 3, 4];
    console.log(arr2); // 输出 [1, 2, 3, 4]
    
    const obj1 = { a: 1, b: 2 };
    const obj2 = { ...obj1, c: 3 };
    console.log(obj2); // 输出 { a: 1, b: 2, c: 3 }
    ```

    
    
    

* * *

### **8\. 类**

#### **(1) 基本语法**

*   使用 `class` 关键字定义类。
*   示例：
    
    ```javascript
    class Person {
      constructor(name, age) {
        this.name = name;
        this.age = age;
      }
    
      greet() {
        console.log(`Hello, my name is ${this.name}`);
      }
    }
    
    const alice = new Person('Alice', 25);
    alice.greet(); // 输出 Hello, my name is Alice
    ```

    
    
    

#### **(2) Getter 和 Setter**

*   定义属性的 getter 和 setter 方法。
*   示例：
    
    ```javascript
    class Circle {
      constructor(radius) {
        this._radius = radius;
      }
    
      get radius() {
        return this._radius;
      }
    
      set radius(value) {
        if (value >= 0) {
          this._radius = value;
        } else {
          throw new Error('Radius must be non-negative');
        }
      }
    }
    
    const circle = new Circle(5);
    console.log(circle.radius); // 输出 5
    circle.radius = 10;
    console.log(circle.radius); // 输出 10
    ```

    
    
    

* * *

### **9\. 模块化**

#### **(1) 导出**

*   使用 `export` 导出变量或函数。
*   示例：
    
    ```javascript
    // math.js
    export const PI = 3.14;
    
    export function add(a, b) {
      return a + b;
    }
    ```

    
    
    

#### **(2) 导入**

*   使用 `import` 导入模块。
*   示例：
    
    ```javascript
    // main.js
    import { PI, add } from './math.js';
    console.log(PI); // 输出 3.14
    console.log(add(3, 4)); // 输出 7
    ```

    
    
    

#### **(3) 默认导出**

*   使用 `export default` 导出默认值。
    
*   示例：
    
    ```javascript
    // logger.js
    export default function log(message) {
      console.log(message);
    }
    ```

    
    
    
    ```javascript
    // main.js
    import log from './logger.js';
    log('Hello, world!');
    ```
    
    
    
    

* * *

### **10\. Promises**

*   处理异步操作。
*   示例：
    
    ```javascript
    const fetchData = () => {
      return new Promise((resolve, reject) => {
        setTimeout(() => resolve('Data fetched'), 1000);
      });
    };
    
    fetchData()
      .then(data => console.log(data)) // 输出 Data fetched
      .catch(error => console.error(error));
    ```

    
    
    

* * *

### **11\. Symbols**

*   一种新的原始数据类型，用于唯一标识。
*   示例：
    
    ```javascript
    const symbol1 = Symbol('description');
    const symbol2 = Symbol('description');
    
    console.log(symbol1 === symbol2); // 输出 false
    ```

    
    
    

* * *

### **12\. Maps 和 Sets**

#### **(1) Maps**

*   键值对集合。
*   示例：
    
    ```javascript
    const map = new Map();
    map.set('key1', 'value1');
    map.set('key2', 'value2');
    
    console.log(map.get('key1')); // 输出 value1
    ```

    
    
    

#### **(2) Sets**

*   唯一值集合。
*   示例：
    
    ```javascript
    const set = new Set([1, 2, 3, 3]);
    console.log(set.size); // 输出 3
    ```

    
    
    

* * *

### **13\. 迭代器和生成器**

#### **(1) 迭代器**

*   遍历数据结构。
*   示例：
    
    ```javascript
    const iterable = [1, 2, 3];
    for (const item of iterable) {
      console.log(item); // 输出 1, 2, 3
    }
    ```

    
    
    

#### **(2) 生成器**

*   可暂停和恢复的函数。
*   示例：
    
    ```javascript
    function* generator() {
      yield 1;
      yield 2;
      yield 3;
    }
    
    const gen = generator();
    console.log(gen.next().value); // 输出 1
    console.log(gen.next().value); // 输出 2
    ```

    
    
    

* * *

### **14\. 数值和二进制字面量**

*   支持八进制、二进制和十六进制字面量。
*   示例：
    
    ```javascript
    const decimal = 10;
    const binary = 0b1010; // 二进制表示 10
    const octal = 0o12; // 八进制表示 10
    const hex = 0xA; // 十六进制表示 10
    ```

    
    
    

* * *

### **15\. 其他特性**

#### **(1) 模块热更新**

*   支持动态模块加载。
*   示例：
    
    ```javascript
    import('./module.js').then(module => {
      module.default();
    });
    ```

    
    
    

#### **(2) Proxy**

*   代理对象，拦截操作。
*   示例：
    
    ```javascript
    const handler = {
      get(target, property) {
        return property in target ? target[property] : 'Not Found';
      }
    };
    
    const proxy = new Proxy({}, handler);
    proxy.name = 'Alice';
    console.log(proxy.name); // 输出 Alice
    console.log(proxy.age); // 输出 Not Found
    ```

    

* * *

通过以上案例，你应该能够全面理解 ES6 的核心特性。如果还有具体问题或需要进一步解释，请告诉我！