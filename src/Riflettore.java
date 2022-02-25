import java.util.ArrayList;
import java.util.Random;

public class Riflettore {


    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private ArrayList<Character> ALPHABET_AL;
    public char[] dx = {};
    public char[] sx = {};
    public ArrayList<Character> ar_dx;
    public ArrayList<Character> ar_sx;

    public Riflettore() {
        this.ALPHABET_AL = arrayToArrayList(ALPHABET);

        this.dx = generateDX(ALPHABET_AL);
        this.sx = generateSX(this.dx);

        //Metto i due array in dx e sx trasformati in ArrayList
        this.ar_dx = arrayToArrayList(this.dx);
        this.ar_sx = arrayToArrayList(this.sx);
    }

    private ArrayList<Character> arrayToArrayList(char[] a){
        char[] cloned_aArray = a.clone();
        ArrayList<Character> cloned_a = new ArrayList<>();

        for(char l: cloned_aArray) cloned_a.add(l);
        return cloned_a;
    }

    public char[] generateDX(ArrayList<Character> alphabet){

        ArrayList<Character> clonedAlphabet = (ArrayList<Character>) alphabet.clone();
        char[] rotorChars = new char[alphabet.size()];

        Random random = new Random();
        for(int i = 0;i < alphabet.size(); i++) {

            int generatedChar = random.nextInt(clonedAlphabet.size());

            rotorChars[i] = clonedAlphabet.get(generatedChar);

            clonedAlphabet.remove(generatedChar);
        }

        return rotorChars;

    }

    public char[] generateSX(char[] initial_dx ) {
        ArrayList<Character> clone_dx_al = (ArrayList<Character>) arrayToArrayList(initial_dx).clone();
        ArrayList<Character> clone_dx = (ArrayList<Character>) arrayToArrayList(initial_dx).clone();

        char[] new_sx = new char[clone_dx.size()];

        Random random = new Random();
        for(int i = 0; i < initial_dx.length; i++){
            if(new_sx[i] == 0){

                int index_char_in_sx;
                do {
                    index_char_in_sx = random.nextInt(clone_dx.size());
                }while (index_char_in_sx==0);

                //Prendo il primo carattere di dx
                char char_in_dx = clone_dx.get(0);

                //Prendo un carattere casuale da dx che andra in sx l'importante è che non sia dx stesso
                char char_in_sx = clone_dx.get(index_char_in_sx);


                //Prendo il carattere da posizionare in sx preso da dx e lo metto in sx proprio nella posizione dove ci sta dx
                new_sx[i] = char_in_sx;
                //Prendo il carattere di dx e lo metto in sx in posizione dove ho preso il carattere che ho messo in sx
                new_sx[clone_dx_al.indexOf(char_in_sx)] = char_in_dx;

                clone_dx.remove(index_char_in_sx);
                clone_dx.remove(0);
            }
        }
        return new_sx;
    }

}
