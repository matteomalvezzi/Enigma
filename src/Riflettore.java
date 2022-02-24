import java.util.ArrayList;
import java.util.Random;

public class Riflettore {


    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private ArrayList<Character> ALPHABET_AL;
    public char[] dx = {};
    public char[] sx = {};

    public Riflettore() {
        this.ALPHABET_AL = arrayToArrayList(ALPHABET);

        this.dx = generateDX(ALPHABET_AL);
        this.sx = generateSX(this.dx);
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
        System.out.println("new_sx lenght" + new_sx.length);

        Random random = new Random();
        for(int i = 0; i < initial_dx.length; i++){
            if(new_sx[i] == 0){
                System.out.println(clone_dx);
                int index_char_in_sx;
                do {
                    index_char_in_sx = random.nextInt(clone_dx.size());
                }while (index_char_in_sx==0);
                System.out.println("NEW CHAR INDEX FOR SX: " + index_char_in_sx);

                //Prendo il primo carattere di dx
                char char_in_dx = clone_dx.get(0);
                System.out.println("CHAR CHE STA IN DX: " + char_in_dx);
                //Prendo un carattere casuale da dx che andra in sx l'importante è che non sia dx stesso
                char char_in_sx = clone_dx.get(index_char_in_sx);
                System.out.println("CHAR CHE STA IN SX: " + char_in_sx);

                //Prendo il carattere da posizionare in sx preso da dx e lo metto in sx proprio nella posizione dove ci sta dx
                new_sx[i] = char_in_sx;
                //Prendo il carattere di dx e lo metto in sx in posizione dove ho preso il carattere che ho messo in sx
                new_sx[clone_dx_al.indexOf(char_in_sx)] = char_in_dx;
                System.out.println("IMPORTANTE : " + index_char_in_sx);
                System.out.println(new_sx);
                System.out.println(initial_dx);

                clone_dx.remove(index_char_in_sx);
                clone_dx.remove(0);
            }
        }
        return new_sx;
    }

}
