package huster.top;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by longan.rtb on 2019/7/8.
 * agent的实现代码.
 */
public class AOPImplement implements ClassFileTransformer {

    private final static String methodName = "helloWorld";

    private final static String targetClassName = "huster.top.JavaAgentTest";

   public byte[]
    transform(  ClassLoader         loader,
                String              className,
                Class<?>            classBeingRedefined,
                ProtectionDomain protectionDomain,
                byte[]              classfileBuffer)
            throws IllegalClassFormatException {
       className = className.replace("/", ".");
       if (!className.equals(targetClassName)) {
           //只监控需要的类
           return classfileBuffer;
       }

       CtClass ctclass = null;
       try {
           ctclass = ClassPool.getDefault().get(className);
           //只监控需要的方法
           CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);
           //旧方法改个名字
           String oldMethod = methodName + "_____old____";
           ctmethod.setName(oldMethod);
           CtMethod newMethod = CtNewMethod.copy(ctmethod, methodName, ctclass, null);
           String methodBody = "{" +
                        "System.out.println(\"before called!!\");\n" +
                        "String a = " + oldMethod + "($$);\n" +
                        "System.out.println(\"after method called!!\");\n" +
                        "return a;" +
                   "}";
           newMethod.setBody(methodBody);
           ctclass.addMethod(newMethod);
           return ctclass.toBytecode();
       } catch (Exception e) {
           e.printStackTrace();
           return classfileBuffer;
       }

   }
}
