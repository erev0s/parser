import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.ASM6;

public class GraphPrinter extends ClassVisitor {
    public GraphPrinter() {
        super(ASM6);
    }

    public GraphMethod visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }
}

