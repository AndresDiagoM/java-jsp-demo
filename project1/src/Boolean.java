public class Boolean {
    public static void main(String[] args) {
        // boolean: true o false
        boolean n = true;
        boolean o = false;
        System.out.println("boolean: " + n + " " + o);

        // scope en java es el alcance de las variables
        // las variables se declaran dentro de un scope

        // switch case
        int p = 5;
        switch (p) {
            case 1:
                System.out.println("p es 1");
                break;
            case 2:
                System.out.println("p es 2");
                break;
            case 3:
                System.out.println("p es 3");
                break;
            case 4:
                System.out.println("p es 4");
                break;
            case 5:
                System.out.println("p es 5");
                break;
            default:
                System.out.println("p no es 1, 2, 3, 4 o 5");
                break;
        }
    }
}