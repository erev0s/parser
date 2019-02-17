public class Test
{
    public static void main(String[] args) {
        System.out.println("method called ");
        printOne();
        System.out.println("method called ");
        printOne();
        System.out.println("method called ");
        printTwo();
    }
    
    public static void printOne() {
        System.out.println("method called is print itselfffffffff ");
        System.out.println("Hello World");
    }
    
    public static void printTwo() {
        printOne();
        printOne();
    }
}

