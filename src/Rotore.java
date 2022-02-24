import java.util.ArrayList;
import java.util.Random;

public class Rotore {

    /**
     * Attributi
     * **/
    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private ArrayList<Character> ALPHABET_AL;
    public char[] dx = {};
    public char[] sx = {};
    public int begin_index;

    /**
     * Costruttore
     * **/
    public Rotore(char bg_i) {
        //Faccio una copia di ALPHABET sotto forma di oggetto
        this.ALPHABET_AL = arrayToArrayList(ALPHABET);
        //Inizializzo le parti (Creo arrary casuali con le lettere dell'alfabeto)
        this.dx = generatePart(ALPHABET_AL);
        this.sx = generatePart(ALPHABET_AL);
        //Estraggo l'indice della lettera di partenza
        this.begin_index = arrayToArrayList(this.dx).indexOf(bg_i);
        //Shifto l'array in modo tale che la lettera di partenza sia la prima
        this.setBegin(this.dx, this.dx.length-begin_index);
        this.setBegin(this.sx, this.sx.length-begin_index);
    }

    /**
     * Metodi
     * **/
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

    public void showPart(char[] part){ System.out.println(part); }

    public void setBegin(char a[], int n){
        for(int i = 0; i < n; i++){
            int j;
            char last;
            last = a[a.length-1];

            for(j = a.length-1; j > 0; j--){
                a[j] = a[j-1];
            }
            a[0] = last;
        }
    }

}
