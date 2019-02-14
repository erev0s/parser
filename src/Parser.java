import java.io.FileInputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;


public class Parser {

    public static void main(final String args[]) throws Exception {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\erev\\IdeaProjects\\parser\\src\\Test.class");
        ClassReader cr = new ClassReader(inputFile);

        GraphPrinter gp = new GraphPrinter();

        cr.accept(gp, 0);

    }






}
