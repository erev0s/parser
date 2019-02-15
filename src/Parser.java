import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.List;



public class Parser {

    public static void main(String[] args) throws Exception{
        InputStream in = new FileInputStream("C:\\\\Users\\\\erev\\\\IdeaProjects\\\\Test.class");
        ClassReader reader = new ClassReader(in);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode,0);

        final List<MethodNode> methods = classNode.methods;
        for(MethodNode m: methods){
            InsnList instructionList = m.instructions;
            for(int i = 0; i< instructionList.size(); i++){
                if (instructionToString(instructionList.get(i)).contains("INVOKE")){
                    String sp = instructionToString(instructionList.get(i));
                    String trimmed = sp.trim();
                    String[] r = trimmed.split(" ");
                    System.out.println(classNode.name + "." + m.name + " ===> " + r[1]);
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