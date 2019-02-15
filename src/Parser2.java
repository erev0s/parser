import java.io.FileInputStream;
import org.objectweb.asm.ClassReader;



public class Parser2 {

    public static void main(final String args[]) throws Exception {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\erev\\IdeaProjects\\Test.class");
        ClassReader reader = new ClassReader(inputFile);

        GraphClass gc = new GraphClass();

        reader.accept(gc, 0);

    }






}
