package Enigma;
import java.util.HashMap;

public class Enigma {

    public final static int[][] R1 = {
            { 10, 21, 5, -17, 21, -4, 12, 16, 6, -3, 7, -7, 4, 2, 5, -7, -11, -17, -9, -6, -9, -19, 2, -3, -21, -4 },
            { 17, 4, 19, 21, 7, 11, 3, -5, 7, 9, -10, 9, 17, 6, -6, -2, -4, -7, -12, -5, 3, 4, -21, -16, -2, -21 }
    };

    public final static int[][] R2 = {
            { 3, 17, 22, 18, 16, 7, 5, 1, -7, 16, -3, 8, 2, 9, 2, -5, -1, -13, -12, -17, -11, -4, 1, -10, -19, -25 },
            { 25, 7, 17, -3, 13, 19, 12, 3, -1, 11, 5, -5, -7, 10, -2, 1, -2, 4, -17, -8, -16, -18, -9, -1, -22, -16 }
    };
    public final static int[][] R3 = {
            { 1, 16, 5, 17, 20, 8, -2, 2, 14, 6, 2, -5, -12, -10, 9, 10, 5, -9, 1, -14, -2, -10, -6, 13, -10, -23 },
            { 12, -1, 23, 10, 2, 14, 5, -5, 9, -2, -13, 10, -2, -8, 10, -6, 6, -16, 2, -1, -17, -5, -14, -9, -20, -10 }

    };

    public final static int[] Reflector = {
            25, 23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, -1, -3, -5, -7, -9, -11, -13, -15, -17, -19, -21, -23, -25
    };

    public final static char[] Alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static HashMap<Integer, Character> digitLetter = new HashMap<Integer, Character>();
    public static HashMap<Character, Integer> letterDigit = new HashMap<Character, Integer>();

    public static void digitToLetter() {
        digitLetter.put(0, 'A');
        digitLetter.put(1, 'B');
        digitLetter.put(2, 'C');
        digitLetter.put(3, 'D');
        digitLetter.put(4, 'E');
        digitLetter.put(5, 'F');
        digitLetter.put(6, 'G');
        digitLetter.put(7, 'H');
        digitLetter.put(8, 'I');
        digitLetter.put(9, 'J');
        digitLetter.put(10, 'K');
        digitLetter.put(11, 'L');
        digitLetter.put(12, 'M');
        digitLetter.put(13, 'N');
        digitLetter.put(14, 'O');
        digitLetter.put(15, 'P');
        digitLetter.put(16, 'Q');
        digitLetter.put(17, 'R');
        digitLetter.put(18, 'S');
        digitLetter.put(19, 'T');
        digitLetter.put(20, 'U');
        digitLetter.put(21, 'V');
        digitLetter.put(22, 'W');
        digitLetter.put(23, 'X');
        digitLetter.put(24, 'Y');
        digitLetter.put(25, 'Z');
    }

    public static void letterToDigit() {
        letterDigit.put('A', 0);
        letterDigit.put('B', 1);
        letterDigit.put('C', 2);
        letterDigit.put('D', 3);
        letterDigit.put('E', 4);
        letterDigit.put('F', 5);
        letterDigit.put('G', 6);
        letterDigit.put('H', 7);
        letterDigit.put('I', 8);
        letterDigit.put('J', 9);
        letterDigit.put('K', 10);
        letterDigit.put('L', 11);
        letterDigit.put('M', 12);
        letterDigit.put('N', 13);
        letterDigit.put('O', 14);
        letterDigit.put('P', 15);
        letterDigit.put('Q', 16);
        letterDigit.put('R', 17);
        letterDigit.put('S', 18);
        letterDigit.put('T', 19);
        letterDigit.put('U', 20);
        letterDigit.put('V', 21);
        letterDigit.put('W', 22);
        letterDigit.put('X', 23);
        letterDigit.put('Y', 24);
        letterDigit.put('Z', 25);
    }

    public static class Rotor {

        public int cnt;
        public int[][] tab;
        public int[][] tmp;
        public boolean rotationInversed;
        public Rotor next;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("         | ");
            for (int i = 0; i < 26; i++) {
                sb.append(tab[0][i]).append(" | ");
            }
            sb.append("\n");
            sb.append("         | ");
            for (int i = 0; i < 26; i++) {
                sb.append(tab[1][i]).append(" | ");
            }
            sb.append("\n");

            return sb.toString();
        }

        public Rotor(String rotor, String direction, int startingPoint) {

            if (rotor.equals("R1")) {
                tmp = R1;
            } else if (rotor.equals("R2")) {
                tmp = R2;
            } else if (rotor.equals("R3")) {
                tmp = R3;
            }

            cnt = 0;
            tab = new int[2][26];
            if (direction.equals("D")) {
                rotationInversed = false;
            } else if (direction.equals("G")) {
                rotationInversed = true;
            }

            for (int i = 0; i < 26; i++) {
                tab[0][i] = tmp[0][(i + startingPoint + 26) % 26];// +26 to avoid negative numbers

                tab[1][i] = tmp[1][(i + startingPoint + 26) % 26];
            }

        }

        public void rotate() {

            if (rotationInversed) {
                int tmp1 = tab[0][25];
                int tmp2 = tab[1][25];

                for (int i = 25; i > 0; i--) {
                    tab[0][i] = tab[0][(i + 25) % 26]; // 25 = -1 mod 26
                    tab[1][i] = tab[1][(i + 25) % 26];
                }
                tab[0][0] = tmp1;
                tab[1][0] = tmp2;

            } else {
                int tmp1 = tab[0][0];
                int tmp2 = tab[1][0];
                for (int i = 0; i < 25; i++) {
                    tab[0][i] = tab[0][(i + 1) % 26];
                    tab[1][i] = tab[1][(i + 1) % 26];
                }
                tab[0][25] = tmp1;
                tab[1][25] = tmp2;
            }

            if (cnt == 25) {
                next.rotate();
                cnt = 0;
            } else {
                cnt++;
            }

        }

    }

    public static void compute(String message, Rotor Rotor1, Rotor Rotor2, Rotor Rotor3) {

        String encryptedMessage = "";

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            if (c == ' ') {
                encryptedMessage += ' ';
            } else if (!Character.isLetter(c)) {
                encryptedMessage += c;
            } else {

                int index0 = letterDigit.get(c); 
                int count = index0;
                count = Rotor1.tab[0][(count + 260) % 26] + ((count + 260) % 26); // +260 to avoid negative numbers
                count = Rotor2.tab[0][(count + 260) % 26] + ((count + 260) % 26);
                count = Rotor3.tab[0][(count + 260) % 26] + ((count + 260) % 26);
                count = Reflector[(count + 260) % 26] + ((count + 260) % 26);
                count = Rotor3.tab[1][(count + 260) % 26] + ((count + 260) % 26);
                count = Rotor2.tab[1][(count + 260) % 26] + ((count + 260) % 26);
                count = Rotor1.tab[1][(count + 260) % 26] + ((count + 260) % 26);
                int index8 = (count + 260) % 26;

                encryptedMessage += digitLetter.get(index8);

                Rotor1.rotate();

            }
        }

        System.out.println("\nEncrypted message:  " + encryptedMessage);
        System.out.println("\n");

    }

    public static void print2DArray(int[][] array) {
        // int rows = array.length;
        int columns = array[0].length;
        System.out.print("  +");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.print("----+");
        }
        System.out.println();
        for (int rowIndex = 1; rowIndex >= 0; rowIndex--) {
            System.out.printf("  |", rowIndex);
            for (int colIndex = 0; colIndex < columns; colIndex++) {
                System.out.printf("%4d|", array[rowIndex][colIndex]);
            }
            System.out.println();
            System.out.print("  +");
            for (int colIndex = 0; colIndex < columns; colIndex++) {
                System.out.print("----+");
            }
            System.out.println();
        }
    }

    public static void print1DArrayChar(char[] row) {
        int columns = row.length;
        System.out.print("  +");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.print("----+");
        }
        System.out.println();
        System.out.print("  |");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.printf(" %c  |", row[colIndex]);
        }
        System.out.println();
        System.out.print("  +");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.print("----+");
        }
        System.out.println();
    }

    public static void print1DArray(int[] row) {
        int columns = row.length;
        System.out.print("  +");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.print("----+");
        }
        System.out.println();
        System.out.print("  |");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.printf("%4d|", row[colIndex]);
        }
        System.out.println();
        System.out.print("  +");
        for (int colIndex = 0; colIndex < columns; colIndex++) {
            System.out.print("----+");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        digitToLetter();
        letterToDigit();

        Rotor Rotor1 = new Rotor(args[0], args[1], Integer.parseInt(args[2]));
        Rotor Rotor2 = new Rotor(args[3], args[4], Integer.parseInt(args[5]));
        Rotor Rotor3 = new Rotor(args[6], args[7], Integer.parseInt(args[8]));

        Rotor1.next = Rotor2;
        Rotor2.next = Rotor3;
        Rotor3.next = Rotor1;

        System.out.println("\nReflector");
        print1DArray(Reflector);
        System.out.println("\nRotor 3 ");
        print2DArray(Rotor3.tab);
        System.out.println("Rotor 2 ");
        print2DArray(Rotor2.tab);
        System.out.println("Rotor 1 ");
        print2DArray(Rotor1.tab);
        System.out.println("\n");
        print1DArrayChar(Alphabet);
        System.out.println("\n\nKEY :    (" + args[0] + " " + args[1] + " " + args[2] + ")  (" + args[3] + " " + args[4]
                + " " + args[5] + ")  (" + args[6] + " " + args[7] + " "
                + args[8] + ")\n");

        System.out.printf("Enter your message: ");
        String message = System.console().readLine().toUpperCase();
        compute(message, Rotor1, Rotor2, Rotor3);

        System.out.println("\nReflector");
        print1DArray(Reflector);
        System.out.println("\nRotor 3 ");
        print2DArray(Rotor3.tab);
        System.out.println("Rotor 2 ");
        print2DArray(Rotor2.tab);
        System.out.println("Rotor 1 ");
        print2DArray(Rotor1.tab);
        System.out.println("\n");
        print1DArrayChar(Alphabet);
        System.out.println("\n\nKEY :    (" + args[0] + " " + args[1] + " " + args[2] + ")  (" + args[3] + " " + args[4]
                + " " + args[5] + ")  (" + args[6] + " " + args[7] + " "
                + args[8] + ")\n");

    }
}