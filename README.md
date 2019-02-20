# Some Parsers for .class files in Java, that will print all the method calls.

[Parser1](https://github.com/erev0s/parser/blob/master/src/Parser.java) is using the *ClassNode* from the tree of ASM and it just reports back when the instruction appears to contain the string *INVOKE*

[Parser2](https://github.com/erev0s/parser/blob/master/src/Parser2.java) is overriding the *visitMethodInsn* to print the trace calls.

[Parser3](https://github.com/erev0s/parser/blob/master/src/Parser3.java) is instrumenting the class file in a similar method as the second one, and it writes the output to another .class file.


### There is also a test with JNI to see how it is reported through the ASM library.
This is the HelloJNI.java file
```java
public class HelloJNI { 
   static {
      System.loadLibrary("hello");}
   private native void sayHello();
   public static void main(String[] args) {
      new HelloJNI().sayHello();  
   }
}
```
and this is the instrumented HelloJNI.class file (decompiled)
```java
public class HelloJNI {
    public HelloJNI() {
        System.out.println("HelloJNI.<init> ===> java/lang/Object.<init>");
    }

    private native void sayHello();

    public static void main(String[] var0) {
        HelloJNI var10000 = new HelloJNI();
        System.out.println("HelloJNI.main ===> HelloJNI.<init>");
        var10000.sayHello();
        System.out.println("HelloJNI.main ===> HelloJNI.sayHello");
    }

    static {
        System.loadLibrary("hello");
        System.out.println("HelloJNI.<clinit> ===> java/lang/System.loadLibrary");
    }
}
```
*You may need to specify the library when you try to run the JNI class file by issuing*
`java -Djava.library.path=. HelloJNI`


### Finally there is a test with a simple hello world written using reflection
This is the [ReflectionHello_World.java](https://github.com/erev0s/parser/blob/master/src/ReflectionHello_World.java)
```java
import java.lang.reflect.Method;

public class ReflectionHello_World {


    public static void main(String [] argv) {
        try {
            // Take the System class
            Class<?> systemClass = Class.forName("java.lang.System");

            // Now get the static object out
            Object outObject = systemClass.getField("out").get(null);

            // Get the println method
            Method printlnMethod = outObject.getClass()
                    .getMethod("println", new Class[] { String.class});

            // Invoke println dynamically
            printlnMethod.invoke(outObject, "Hello, world!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```
and this is the instrumented decompiled class of it
```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHello_World {
    public ReflectionHello_World() {
        System.out.println("ReflectionHello_World.<init> ===> java/lang/Object.<init>");
    }

    public static void main(String[] argv) {
        try {
            Class var10000 = Class.forName("java.lang.System");
            System.out.println("ReflectionHello_World.main ===> java/lang/Class.forName");
            Class<?> systemClass = var10000;
            Field var5 = systemClass.getField("out");
            System.out.println("ReflectionHello_World.main ===> java/lang/Class.getField");
            Object var6 = var5.get((Object)null);
            System.out.println("ReflectionHello_World.main ===> java/lang/reflect/Field.get");
            Object outObject = var6;
            var10000 = outObject.getClass();
            System.out.println("ReflectionHello_World.main ===> java/lang/Object.getClass");
            Method var7 = var10000.getMethod("println", String.class);
            System.out.println("ReflectionHello_World.main ===> java/lang/Class.getMethod");
            Method printlnMethod = var7;
            printlnMethod.invoke(outObject, "Hello, world!");
            System.out.println("ReflectionHello_World.main ===> java/lang/reflect/Method.invoke");
        } catch (Exception var4) {
            var4.printStackTrace();
            System.out.println("ReflectionHello_World.main ===> java/lang/Exception.printStackTrace");
        }

    }
}
```
