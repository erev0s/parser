import java.io.FileInputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;


public class Parser2 {

    public static void main(final String args[]) throws Exception {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\erev\\IdeaProjects\\Test.class");
        ClassReader reader = new ClassReader(inputFile);

        GraphClass gc = new GraphClass();

        reader.accept(gc, 0);

    }
}

class GraphClass extends ClassVisitor {
    public GraphClass() {
        super(ASM5);
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        GraphMethod newVisitor = new GraphMethod(name);
        return newVisitor;
        //return super.visitMethod(access, name, desc, signature,exceptions);
    }
}


class GraphMethod extends MethodVisitor{
    public String MthName;

    public GraphMethod(String name) {
        super(ASM5);
        MthName = name;
    }

    public void visitMethodInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor, boolean isInterface) {
        System.out.println(owner + "." + MthName + " ===> " + owner + "." + name);

    }
}