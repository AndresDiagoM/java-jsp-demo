public class Ciclos {
    public static void main(String[] args) {
        
        // ciclo while
        int a = 0;
        while (a < 5) {
            System.out.println("a es menor que 5");
            a++;
        }

        // scope ciclos
        int b = 0;
        // while (b < 5) {
        //     System.out.println("b es menor que 5");
        //     b++;
        //     int c = 0;
        //     while (c < 5) {
        //         System.out.println("c es menor que 5");
        //         c++;
        //     }
        // }

        // ciclo for (variable; condicion; incremento)
        for (int d = 0; d < 5; d++) {
            System.out.println("d es menor que 5");
        }

        // ciclso anidados
        for (int e = 0; e < 5; e++) {
            System.out.println("e:"+ e);
            for (int f = 0; f < 2; f++) {
                System.out.println(" f:"+f);
            }
        }
    }
}
