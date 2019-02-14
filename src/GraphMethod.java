import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class GraphMethod extends MethodVisitor{
    public GraphMethod() {
        super(ASM6);}

    public void visitMethodInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor, boolean isInterface) {
        System.out.println("The visitMethodinsn gives = " + owner + name + descriptor + isInterface);

    }
}

