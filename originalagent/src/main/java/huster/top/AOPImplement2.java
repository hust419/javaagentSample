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
 * add methdo will cause
 * Caused by: java.lang.UnsupportedOperationException: class redefinition failed: attempted to add a method
 * The retransformation may change method bodies, the constant pool and attributes.
 * The retransformation must not add, remove or rename fields or methods, change the
 * signatures of methods, or change inheritance.  These restrictions maybe be
 * lifted in future versions.  The class file bytes are not checked, verified and installed
 * until after the transformations have been applied, if the resultant bytes are in
 * error this method will throw an exception.
 *
 */
public class AOPImplement2 implements ClassFileTransformer {

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
           ctmethod.insertBefore( "System.out.println(\"before called!!\");\n" );
           ctmethod.insertAfter( "System.out.println(\"after called!!\");\n" );
           return ctclass.toBytecode();
       } catch (Exception e) {
           e.printStackTrace();
           return classfileBuffer;
       }

   }
}
