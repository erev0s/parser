import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;


public class GraphMethod extends MethodVisitor{
    public GraphMethod() {
        super(ASM5);}

    public void visitMethodInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor, boolean isInterface) {
        System.out.println("The visitMethodinsn gives Owner = " + owner + " Name = " + name + " Descriptor = " + descriptor + " IsInterface = " + isInterface);
    }
}
