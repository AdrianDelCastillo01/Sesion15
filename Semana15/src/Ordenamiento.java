
import java.util.Scanner;

public class Ordenamiento {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese la lista de numeros enteros (separelos con ; ): ");
        int[] numeros = getIntArray(scan);

        numeros = ordenarDatos(scan, numeros);
        System.out.print("La lista ordenada es: ");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i] + ", ");
        }
        System.out.println();

        int index = obtenerValor(scan, numeros);
        if (index != -1) {
            System.out.println("El numero " + numeros[index]
                    + " se encuentra en la posicion numero " + (index + 1));
        } else {
            System.out.println("Ese numero no esta en la lista :(");
        }
    }

    //Devuelve los numeros ordenados
    private static int[] ordenarDatos(Scanner scan, int[] numeros) {
        System.out.print("Metodos de ordenamiento disponibles\n"
                + "1. Bubble Sort\n"
                + "2. Insertion Sort\n"
                + "3. Selection Sort\n"
                + "Escoja el numero de su preferencia: ");

        int opcion = getOption(scan, 3);
        if (opcion == 1) {
            numeros = bubbleSort(numeros);
        } else if (opcion == 2) {
            numeros = insertionSort(numeros);
        } else {
            numeros = selectionSort(numeros);
        }
        return numeros;
    }

    private static int obtenerValor(Scanner scan, int[] numeros) {
        System.out.print("Ingresa el numero a buscar: ");
        int valor = getInt(scan);

        System.out.print("Metodos de busqueda disponiles\n"
                + "1. Sequential Search\n"
                + "2. Binary Search\n"
                + "Escoja el numero de su preferencia: ");
        int opcion = getOption(scan, 2);

        if (opcion == 1) {
            return sequentialSearch(numeros, valor);
        } else {
            return binarySearch(numeros, valor);
        }
    }

    //Al iniciar pasa a comprobar todos los valores, incluso si ya estan ordenados
    private static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    //Comprueba solo 1 vez en caso ya esten ordenados al iniciar, realiza varios
    //intercambios
    private static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //Guarda un valor
            int temp = array[i];            
            //Mientras el numero anterior sea mayor que el guardado, los intercambia
            for (int j = i - 1; j >= 0 && array[j] > temp; j--) {
                array[j + 1] = array[j];
                array[j] = temp;
            }
        }
        return array;
    }

    //Comprueba todos los valores pero los intercambia solo 1 vez incluso si estan en lados opuestos
    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            //Solo los reordena si los indices son diferentes
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    //Devuelve el indice donde estaba el item, en caso de no estarlo regresa -1
    private static int sequentialSearch(int[] array, int valor) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    //Devuelve el indice o -1 si no existe, debe estar ordenado
    private static int binarySearch(int[] array, int valor) {
        int bajo = 0, alto = array.length - 1;
        while (bajo <= alto) {
            int central = (bajo + alto) / 2;

            if (valor == array[central]) {
                return central;
            }

            if (valor < array[central]) {
                alto = central - 1;
            } else {
                bajo = central + 1;
            }
        }
        return -1;
    }

    private static int getInt(Scanner scan) {
        return Integer.parseInt(scan.nextLine());
    }

    private static int[] getIntArray(Scanner scan) {
        String[] texto = scan.nextLine().split(";");
        int[] numeros = new int[texto.length];

        for (int i = 0; i < texto.length; i++) {
            numeros[i] = Integer.parseInt(texto[i]);
        }
        return numeros;
    }

    private static int getOption(Scanner scan, int limite) {
        int opcion = getInt(scan);
        while (opcion > limite || opcion < 1) {
            System.out.println("Ingrese un numero entre 1 y " + opcion);
            opcion = Integer.parseInt(scan.nextLine());
        }
        return opcion;
    }
}
