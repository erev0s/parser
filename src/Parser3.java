import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Parser3 {
    public static void main(final String args[]) throws Exception {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\erev\\IdeaProjects\\Test.class");

        ClassReader reader = new ClassReader(inputFile);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassAdapter adapter = new ClassAdapter(writer);

        reader.accept(adapter, 0);

        FileOutputStream fos = new FileOutputStream("C:\\Users\\erev\\IdeaProjects\\parser\\out\\production\\parser\\Test.class");
        fos.write(writer.toByteArray());
        fos.close();
    }
}


class ClassAdapter extends ClassVisitor implements Opcodes {
    public String className;

    public ClassAdapter(final ClassVisitor cv) {
        super(ASM5, cv);
    }


    // override of the visit in order to acquire every time the correct owner
    // for the method that is calling other methods.
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        if (cv != null) {
            cv.visit(version, access, name, signature, superName, interfaces);
            className = name; // in order to get the class name as the owner
        }
    }

    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodAdapter(mv,name,className);
    }
}

class MethodAdapter extends MethodVisitor implements Opcodes {
    public String MthName;
    public String ClsName;

    public MethodAdapter(final MethodVisitor mv, String name, String className) {
        super(ASM5, mv);
        MthName = name;
        ClsName = className;
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        //Before calling
        mv.visitMethodInsn(opcode, owner, name, desc, itf);
        // After calling
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(ClsName + "." + MthName + " ===> " + owner + "." + name);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
