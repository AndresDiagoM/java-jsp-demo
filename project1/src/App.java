public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        int a = 5;
        int b = 10;
        int c = a + b;
        System.out.println("La suma de a + b es: " + c);

        // double: 64 bits, 8 bytes, 15 decimales
        double d = 5.5;
        double e = 10.5;
        double f = d + e;
        System.out.println("La suma de d + e es: " + f);
        System.out.println("int * double: " + (b*e));

        // pass a variable by casting it to another type
        System.out.println("int / double: " + ((double)b/e));

        // Long
        long g = 1000000000000000000L;
        System.out.println("long: " + g);
        
        // char: solo 1 caracter, letra o numero, se usa con comillas simples
        char h = 'a';
        char i = 2;
        System.out.println("char: " + h + i);

        // String: se usa con comillas dobles
        String j = "Hola";
        String k = "Mundo";
        System.out.println("String: " + j + " " + k);

        // en java las variables se declaran con camelCase
        // en java las variables guardan valores, no direcciones de memoria

        // escribir sysout para autocompletar system.out....

    }
}
