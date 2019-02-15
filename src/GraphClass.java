import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM5;

public class GraphClass extends ClassVisitor {
    public GraphClass() {
        super(ASM5);
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        GraphMethod newVisitor = new GraphMethod(name);
        return newVisitor;
        //return super.visitMethod(access, name, desc, signature,exceptions);
    }
}


