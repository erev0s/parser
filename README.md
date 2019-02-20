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
