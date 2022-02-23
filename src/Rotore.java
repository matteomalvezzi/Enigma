import java.util.ArrayList;
import java.util.Random;

public class Rotore {

    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private ArrayList<Character> ALPHABET_AL;
    public char[] dx = {};
    public char[] sx = {};
    public int begin_index;

    public Rotore(char bg_i) {
        this.ALPHABET_AL = arrayToArrayList(ALPHABET);
        this.dx = generatePart(ALPHABET_AL);
        this.sx = generatePart(ALPHABET_AL);
        this.begin_index = arrayToArrayList(this.dx).indexOf(bg_i);
    }

    public static char[] ShiftToRight(char a[],int n){
        char temp = a[n];

        for (int i = n; i > 0; i--) {
            a[i] = a[i-1];
        }

        a[0] = temp;

        return a;
    }
    private ArrayList<Character> arrayToArrayList(char[] a){
        char[] cloned_aArray = a.clone();
        ArrayList<Character> cloned_a = new ArrayList<>();

        for(char l: cloned_aArray) cloned_a.add(l);
        return cloned_a;
    }

    public char[] generatePart(ArrayList<Character> alphabet){
        //Faccio una copia temporanea dell'alfabeto che uso per estrarre le lettere
        ArrayList<Character> clonedAlphabet = (ArrayList<Character>) alphabet.clone();
        //creo l'array di supporto che diventerà la nuova parte
        char[] rotorChars = new char[alphabet.size()];

        Random random = new Random();
        //Per 26 volte prendo a caso una lettere da clonedAlphabet e la sposto in rotoChars in modo tale da avere un alfabeto in disordine
        for(int i = 0;i < alphabet.size(); i++) {

            int generatedChar = random.nextInt(clonedAlphabet.size());

            rotorChars[i] = clonedAlphabet.get(generatedChar);

            clonedAlphabet.remove(generatedChar);
        }

        return rotorChars;

    }

    public void showPart(char[] part){
       for (char l: part) {
           System.out.printf(String.valueOf(l));
       }
    }

}
