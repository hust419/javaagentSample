# javaagentSample
    javaagent的示例程序.

# 代码说明
## maintest
 
被植入代码的目标程序,
通过向这个程序中的helloWorld方法中植入代码改变这个函数的行为

## originalagent 

javaagent的客户端, 具体要插入的逻辑


## attachagent

通过这个程序，可以讲agent attach到任何一个正在运行的jvm pid上.


# 用法

```
  mvn package
   
  [INFO] javaagent-demo 1.0-SNAPSHOT ........................ SUCCESS [  0.005 s]
  [INFO] jvm-sandbox-javaagent .............................. SUCCESS [  0.906 s]
  [INFO] original-agent ..................................... SUCCESS [  1.347 s]
  [INFO] main-test .......................................... SUCCESS [  0.084 s]
  [INFO] attach-agent 1.0-SNAPSHOT .......................... SUCCESS [  0.556 s]

```

  ## permain的加载方式
  
  ```
  
  java -javaagent:originalagent/target/original-agent-jar-with-dependencies.jar=args1 -jar maintest/target/main-test-1.0-SNAPSHOT-jar-with-dependencies.jar 
  
  ```
  ## attach的加载方式
  
  先把test jar启动起来.
  
  
  ```
    java -jar maintest/target/main-test-1.0-SNAPSHOT-jar-with-dependencies.jar
  
  ```
  
  找到进程号, 然后attach上去
  
  ```
      java -jar -Xbootclasspath/a:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/lib/tools.jar attach-agent-1.0-SNAPSHOT-jar-with-dependencies.jar 28971
  
  
  ```
  