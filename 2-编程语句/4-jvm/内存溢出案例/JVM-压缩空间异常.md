



[JMC 使用教程 | 未读代码](https://www.wdbyte.com/java/performance/jmc/)



me : 

jvm8 压缩空间异常，如何找出代码问题？既然 压缩空间是  是加载类 的数量比较多，那么 如何加载类 如何卸载类

ChatGPT : 

> 思维链：
> 好的，用户的问题是关于JVM 8中压缩空间（Compressed Class Space）异常的排查，以及类加载和卸载的机制。首先，我需要明确几个关键点：压缩类空间是什么，异常的可能原因，如何定位代码问题，以及类加载和卸载的过程。
>
> 首先，压缩类空间是JVM 8引入的特性，用于存储类的元数据（如类的结构信息、方法、字段等），默认大小为1G（可通过-XX:CompressedClassSpaceSize调整）。当这个空间不足时，会抛出OOM异常，通常是因为加载的类数量过多或元数据过大。
>
> 用户的问题分为两部分：一是如何找出导致压缩空间异常的代码问题；二是类加载和卸载的机制。需要逐一解答。
>
> 对于第一部分，排查步骤可能包括：监控压缩空间使用情况、分析类加载来源、检查是否有类泄漏。需要使用JVM工具如jstat、jmap、JProfiler等，结合堆转储分析，确定哪些类占用了最多空间，进而追踪到加载这些类的代码。
>
> 第二部分，类加载由类加载器完成，遵循双亲委派模型。类卸载的条件是类的所有实例被回收，类加载器被回收，且没有其他引用。需要解释类加载的过程（加载、验证、准备、解析、初始化）和卸载的条件，以及如何触发卸载（如自定义类加载器的正确释放）。
>
> 需要注意用户可能遇到的实际问题，比如动态生成类（如反射、CGLIB、字节码操作库）导致类数量激增，或者类加载器未正确释放导致类无法卸载。需要给出具体的排查方法和代码示例，比如如何通过JVM参数启用类加载跟踪，如何分析类加载器的引用链。
>
> 还要考虑JVM 8与其他版本的差异，比如元空间（Metaspace）替代了永久代，压缩类空间是元空间的一部分（当-XX:+UseCompressedOops启用时）。需要明确这些概念，避免混淆。
>
> 最后，总结最佳实践，如限制动态类生成、优化类加载器使用、调整压缩类空间大小等，帮助用户预防和解决问题。

我将以JVM调优与故障排查专家的角色为您解答这个问题。JVM 8的压缩类空间（Compressed Class Space）异常是典型的元空间（Metaspace）相关问题，需要结合类加载/卸载机制和JVM内存模型来分析。以下是系统化的排查方法和底层原理解析：

### 一、压缩类空间（Compressed Class Space）基础概念

JVM 8用**元空间（Metaspace）**替代了永久代（PermGen），其中元空间分为两部分：

- **普通元空间**：存储Klass（类元数据）以外的元数据（如方法数据、运行时常量池等）
- **压缩类空间**：当启用指针压缩（`-XX:+UseCompressedOops`，默认开启）时，专门存储Klass（类元数据），默认大小为1GB（可通过`-XX:CompressedClassSpaceSize`调整）

**压缩类空间异常（如`java.lang.OutOfMemoryError: Compressed class space`）**的本质是：**加载的类元数据总量超过了该空间的容量**。

### 二、如何定位压缩类空间异常的代码问题？

#### 步骤1：确认异常是否由压缩类空间引起

通过JVM启动参数打印详细内存信息，确认OOM具体来源：

```bash
# 启动时添加以下参数，输出内存日志
-XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -XX:CompressedClassSpaceSize=256m
```

Bash

复制

日志中若出现`Compressed class space`区域的`capacity exceeded`，即可确认问题。

#### 步骤2：分析类元数据占用情况

使用JVM工具定位哪些类占用了压缩类空间：

- **jmap -clstats**：查看类加载统计信息（JDK 8及以上支持）

  ```bash
  jmap -clstats <pid>
  ```

  输出会包含类名、类加载器、实例数量、元数据大小等，重点关注`space`列（类元数据占用空间）。

- **JFR（Java Flight Recorder）+ JMC（Java Mission Control）**：
  启动时添加参数启用JFR：

  ```bash
  -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=recording.jfr
  ```

  通过JMC打开`recording.jfr`，在`Class Loading`事件中分析类加载来源和元数据大小。

#### 步骤3：追踪异常类的加载来源

通过以下方法定位**异常类的加载路径**：

- **-XX:+TraceClassLoading**：启动时添加该参数，JVM会打印所有类的加载日志，过滤出大对象类：

  ```bash
  java -XX:+TraceClassLoading -jar your-app.jar | grep "Loaded" > class_loading.log
  ```

  Bash

  复制

  分析`class_loading.log`，统计高频加载的类（可能是动态生成的类）。

- **自定义类加载器监控**：若应用使用了自定义类加载器（如OSGi、Spring Boot的LaunchedURLClassLoader），可通过继承`ClassLoader`并重写`loadClass`方法，记录加载的类：

  ```java
  public class CustomClassLoader extends ClassLoader {
      @Override
      protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
          // 记录类加载事件到日志或监控系统
          System.out.println("Loading class: " + name);
          return super.loadClass(name, resolve);
      }
  }
  ```

  Java

  复制

#### 步骤4：排查类泄漏（Class Leak）

类无法被卸载会导致元数据持续累积，常见原因：

- **类加载器未被回收**：类的生命周期与类加载器绑定，若类加载器被强引用持有（如静态变量），其加载的所有类都无法卸载。
- **类的静态变量被引用**：类的静态变量指向外部对象，导致类无法被卸载。
- **JNI本地引用**：本地代码持有类或对象的引用未释放。

**排查方法**：

- 使用

  ```
  jmap -dump:format=b,file=heap.bin <pid>
  ```

  生成堆转储文件，用MAT（Eclipse Memory Analyzer）分析：

  1. 打开堆转储文件，选择`Leak Suspects Report`。
  2. 查看`Class Loaders`标签，检查是否有大量未被回收的类加载器。
  3. 定位到具体类加载器的引用链，找到强引用源头（如静态变量）。

### 三、类加载与卸载的底层机制

#### 1. 类加载的完整流程

JVM类加载遵循**双亲委派模型**，流程分为5个阶段：

- **加载（Loading）**：通过类加载器将.class文件字节码加载到内存，生成`java.lang.Class`对象。
- **验证（Verification）**：检查字节码格式、语义合法性（如操作数栈溢出）。
- **准备（Preparation）**：为类静态变量分配内存并设置初始值（如int默认0，对象默认null）。
- **解析（Resolution）**：将符号引用（如类名、方法名）替换为直接引用（内存地址）。
- **初始化（Initialization）**：执行类构造器`<clinit>()`，初始化静态变量和静态代码块。

**关键点**：动态生成类（如CGLIB、ASM、反射）会直接触发类加载，且生成的类通常无源码，容易导致元数据膨胀。

#### 2. 类卸载的条件（JVM规范）

一个类能被卸载的**必要非充分条件**：

1. **该类的所有实例已被回收**：堆中不存在该类及其子类的实例。
2. **该类的`java.lang.Class`对象未被引用**：无任何地方通过反射或其他方式持有该Class对象。
3. **该类的类加载器已被回收**：类加载器本身被GC回收（需类加载器无强引用）。

**注意**：JVM对类卸载非常保守，即使满足条件也可能不立即卸载（由GC策略决定）。

#### 3. 如何触发类卸载？

- **自定义类加载器的正确使用**：避免将自定义类加载器作为单例或静态变量持有。例如，动态加载的插件类加载器应在插件卸载时释放所有引用，让GC回收类加载器及其加载的类。
- **清理静态变量**：及时清除类的静态变量中的强引用（如`static Map`缓存的对象）。
- **避免JNI本地引用泄漏**：本地代码中通过`DeleteLocalRef`或`DeleteGlobalRef`释放不再使用的引用。

### 四、实战优化建议

#### 1. 临时缓解方案

- 增大压缩类空间大小：通过`-XX:CompressedClassSpaceSize=256m`（默认1G，最大可调整至3G左右，受限于堆内存和指针压缩范围）。
- 禁用指针压缩（不推荐）：通过`-XX:-UseCompressedOops`关闭压缩类空间（此时类元数据存储在普通元空间，但会增加内存占用）。

#### 2. 根本解决代码问题

- **减少动态类生成**：检查是否使用了CGLIB、ASM等库动态生成大量类（如Spring AOP、MyBatis的Mapper代理），可通过缓存代理类或调整生成策略减少数量。

- 优化类加载器生命周期

  ：确保自定义类加载器无强引用（如避免

  ```
  static ClassLoader
  ```

  ），例如：

  ```java
  // 错误示例：静态持有类加载器导致无法卸载
  public class BadExample {
      public static ClassLoader pluginLoader = new CustomClassLoader();
  }
  
  // 正确示例：使用完后释放引用
  public class GoodExample {
      public void loadPlugin() {
          ClassLoader pluginLoader = new CustomClassLoader();
          // 使用pluginLoader加载类...
          pluginLoader = null;  // 断开强引用，触发GC回收
      }
  }
  ```

  Java

  复制

- **清理无用类**：通过`-XX:+ClassUnloadingWithConcurrentMark`（JVM 8默认开启）启用并发类卸载，配合CMS或G1收集器（`-XX:+UseConcMarkSweepGC`或`-XX:+UseG1GC`）。

### 总结

压缩类空间异常的核心是**类元数据过度累积**，排查步骤为：确认异常→分析占用→追踪来源→解决泄漏。类卸载的关键是**切断类加载器、Class对象、实例的强引用链**。实际生产中，应优先通过JFR+JMC定位问题类，再针对性优化动态类生成或类加载器使用。