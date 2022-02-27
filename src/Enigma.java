import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Enigma {

    /** Attributi **/

    //Tripletta del giorno
    public static final char R1_BEGIN_LETTER = 'c';
    public static final char R2_BEGIN_LETTER = 'l';
    public static final char R3_BEGIN_LETTER = 'p';

    public static void main(String[] args) {
        String testo_in_chiaro = "ciao";
        System.out.println("TESTO IN CHIARO: " + testo_in_chiaro);


        Rotore r1 = new Rotore(R1_BEGIN_LETTER);
        System.out.println("R1: ");
        r1.showPart(r1.dx);
        r1.showPart(r1.sx);

        Rotore r2 = new Rotore(R2_BEGIN_LETTER);
        System.out.println("R2: ");
        r2.showPart(r2.dx);
        r2.showPart(r2.sx);

        Rotore r3 = new Rotore(R3_BEGIN_LETTER);
        System.out.println("R3: ");
        r3.showPart(r3.dx);
        r3.showPart(r3.sx);

        Riflettore rft = new Riflettore();
        System.out.println("Reflector: ");
        System.out.println(rft.dx);
        System.out.println(rft.sx);

        PlugBoard pg= new PlugBoard();
        pg.setPlugCable('d', 'p');
        pg.setPlugCable('n', 't');
        pg.setPlugCable('f', 'z');
        pg.setPlugCable('u', 'a');


        String testo_criptato = toDoEncryption(r1, r2, r3, rft,pg, testo_in_chiaro.toLowerCase(Locale.ROOT));
        System.out.println("TESTO_CRITTOGRAFATO: " + testo_criptato);
    }

    /**
     * Metodo per criptare una frase
     * **/
    public static String toDoEncryption(Rotore rotor1, Rotore rotor2, Rotore rotor3, Riflettore refletcor, PlugBoard plugBoard, String parola){
        char[] letter = parola.toCharArray();
        ArrayList<Character> new_letter = new ArrayList<Character>();

        int shift_rotor1_index = 0;
        int shift_rotor2_index = 0;

        for (char single_char : letter){
            char encryption_letter= startEncryption(rotor1, rotor2, rotor3, refletcor, single_char);
            encryption_letter = plugBoard.doChange(encryption_letter);
            new_letter.add(encryption_letter);
            rotor1.ShiftToRight(1);
            shift_rotor1_index++;

            if(shift_rotor1_index==20){
                rotor2.ShiftToRight(1);
                shift_rotor2_index++;
                shift_rotor1_index = 0;
            }
            if(shift_rotor2_index==20){
                rotor3.ShiftToRight(1);
                shift_rotor2_index = 0;
            }


        }
        return new_letter.stream()
                .map(e->e.toString())
                .collect(Collectors.joining());
    }

    /**
     * Metodo per criptare una singola lettera
     * **/
    public static char startEncryption(Rotore rt1, Rotore rt2, Rotore rt3, Riflettore rft, char character){
        /** Processo di criptazione di una singola lettera **/
        int r1_change_R = changeInnerRotorFromRight(rt1, character);

        char r1_to_r2_R = changeBetweenRotorFromRight(rt2, r1_change_R);

        int r2_change_R = changeInnerRotorFromRight(rt2, r1_to_r2_R);

        char r2_to_r3_R = changeBetweenRotorFromRight(rt3, r2_change_R);

        int r3_change_R = changeInnerRotorFromRight(rt3, r2_to_r3_R);

        int r3_to_reflector = changeReflectors(rft, r3_change_R);

        char reflector_to_r3 = changeBetweenRotorFromLeft(rt3, r3_to_reflector);

        int r3_change_L = changeInnerRotorFromLeft(rt3, reflector_to_r3);

        char r3_to_r2_L = changeBetweenRotorFromLeft(rt2, r3_change_L);

        int r2_change_L = changeInnerRotorFromLeft(rt2, r3_to_r2_L);

        char r2_to_r1_L = changeBetweenRotorFromLeft(rt1, r2_change_L);

        int r1_change_L = changeInnerRotorFromLeft(rt1, r2_to_r1_L);
        return rt1.ar_dx.get(r1_change_L);

    }

    /**
     * Funzioni per crittografare
     * **/
    public static int changeInnerRotorFromRight(Rotore r, char l){ return r.ar_sx.indexOf(l); }
    public static char changeBetweenRotorFromRight(Rotore r, int l_index){ return r.ar_dx.get(l_index); }

    public static int changeInnerRotorFromLeft(Rotore r, char l){ return r.ar_dx.indexOf(l); }
    public static char changeBetweenRotorFromLeft(Rotore r, int l_index){ return r.ar_sx.get(l_index); }

    public static int changeReflectors(Riflettore r, int l_index){
        char sx_l = r.ar_sx.get(l_index);
        return r.ar_dx.indexOf(sx_l);
    }

}
