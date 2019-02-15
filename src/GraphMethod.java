import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ASM5;

public class GraphMethod extends MethodVisitor{
    public String MthName;

    public GraphMethod(String name) {
        super(ASM5);
        MthName = name;
    }

    public void visitMethodInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor, boolean isInterface) {
        System.out.println(owner + "." + MthName + " ===> " + owner + "." + name);

    }
}