import java.util.ArrayList;
import java.util.Locale;

public class Enigma {


    public static void main(String[] args) {
        Rotore r1 = new Rotore('c');
        System.out.println("R1: ");
        r1.showPart(r1.dx);
        r1.showPart(r1.sx);

        Rotore r2 = new Rotore('l');
        System.out.println("R2: ");
        r2.showPart(r2.dx);
        r2.showPart(r2.sx);

        Rotore r3 = new Rotore('l');
        System.out.println("R3: ");
        r3.showPart(r3.dx);
        r3.showPart(r3.sx);

        Riflettore rft = new Riflettore();
        System.out.println("Reflector: ");
        System.out.println(rft.dx);
        System.out.println(rft.sx);

        toDoEncryption(r1, r2, r3, rft, "ciao".toLowerCase(Locale.ROOT));


    }

    public static void toDoEncryption(Rotore rotor1, Rotore rotor2, Rotore rotor3, Riflettore refletcor, String parola){
        char[] letter = parola.toCharArray();
        ArrayList<Character> new_letter = new ArrayList<Character>();

        int shift_rotor1_index = 0;
        int shift_rotor2_index = 0;

        for (char single_char : letter){
            new_letter.add(startEncryption(rotor1, rotor2, rotor3, refletcor, single_char));
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

        System.out.println(new_letter);

    }

    public static char startEncryption(Rotore rt1, Rotore rt2, Rotore rt3, Riflettore rft, char character){
        System.out.println("INIZIO PROCESSO DI CRIPTAZIONE");
        int r1_change_R = changeInnerRotorFromRight(rt1, character);
        System.out.println(r1_change_R);

        char r1_to_r2_R = changeBetweenRotorFromRight(rt2, r1_change_R);
        System.out.println(r1_to_r2_R);

        int r2_change_R = changeInnerRotorFromRight(rt2, r1_to_r2_R);
        System.out.println(r2_change_R);

        char r2_to_r3_R = changeBetweenRotorFromRight(rt3, r2_change_R);
        System.out.println(r2_to_r3_R);

        int r3_change_R = changeInnerRotorFromRight(rt3, r2_to_r3_R);
        System.out.println(r3_change_R);

        int r3_to_reflector = changeReflectors(rft, r3_change_R);
        System.out.println(r3_to_reflector);

        char reflector_to_r3 = changeBetweenRotorFromLeft(rt3, r3_to_reflector);
        System.out.println(reflector_to_r3);

        int r3_change_L = changeInnerRotorFromLeft(rt3, reflector_to_r3);
        System.out.println(r3_change_L);

        char r3_to_r2_L = changeBetweenRotorFromLeft(rt2, r3_change_L);
        System.out.println(r3_to_r2_L);

        int r2_change_L = changeInnerRotorFromLeft(rt2, r3_to_r2_L);
        System.out.println(r2_change_L);

        char r2_to_r1_L = changeBetweenRotorFromLeft(rt1, r2_change_L);
        System.out.println(r2_to_r1_L);

        int r1_change_L = changeInnerRotorFromLeft(rt1, r2_to_r1_L);
        System.out.println(r1_change_L);

        System.out.println("FINE PROCESSO DI CRIPTAZIONE, LETTERA CRIPTATA: " + rt1.ar_dx.get(r1_change_L));
        return rt1.ar_dx.get(r1_change_L);

    }

    public static int changeInnerRotorFromRight(Rotore r, char l){ return r.ar_sx.indexOf(l); }
    public static char changeBetweenRotorFromRight(Rotore r, int l_index){ return r.ar_dx.get(l_index); }

    public static int changeInnerRotorFromLeft(Rotore r, char l){ return r.ar_dx.indexOf(l); }
    public static char changeBetweenRotorFromLeft(Rotore r, int l_index){ return r.ar_sx.get(l_index); }

    public static int changeReflectors(Riflettore r, int l_index){
        char sx_l = r.ar_sx.get(l_index);
        return r.ar_dx.indexOf(sx_l);
    }

}
