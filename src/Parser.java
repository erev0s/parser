import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.List;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.*;

public class Parser {

    public static void main(String[] args) throws Exception{
        InputStream in = new FileInputStream("C:\\\\Users\\\\erev\\\\IdeaProjects\\\\parser\\\\src\\\\Test.class");
        ClassReader reader = new ClassReader(in);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode,0);

        final List<MethodNode> methods = classNode.methods;
        for(MethodNode m: methods){
            InsnList instructionList = m.instructions;
           /* System.out.println("\n Method Name -> " + m.name);*/
            for(int i = 0; i< instructionList.size(); i++){
                if (instructionToString(instructionList.get(i)).contains("INVOKE")){
                    String sp = instructionToString(instructionList.get(i));
                    String trimmed = sp.trim();
                    String[] r = trimmed.split(" ");
                    System.out.println(m.name + " ===> " + r[1]);
                }

            }
        }
    }

    private static String instructionToString(AbstractInsnNode insn){

        Printer printer = new Textifier();
        TraceMethodVisitor tmv = new TraceMethodVisitor(printer);
        insn.accept(tmv);
        StringWriter sw = new StringWriter();
        printer.print(new PrintWriter(sw));
        return sw.toString();
    }



}