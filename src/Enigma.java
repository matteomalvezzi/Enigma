import java.util.ArrayList;

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

        int r1_change_R = changeInnerRotorFromRight(r1, 'c');
        System.out.println(r1_change_R);

        char r1_to_r2_R = changeBetweenRotorFromRight(r2, r1_change_R);
        System.out.println(r1_to_r2_R);

        int r2_change_R = changeInnerRotorFromRight(r2, r1_to_r2_R);
        System.out.println(r2_change_R);

        char r2_to_r3_R = changeBetweenRotorFromRight(r3, r2_change_R);
        System.out.println(r2_to_r3_R);

        int r3_change_R = changeInnerRotorFromRight(r3, r2_to_r3_R);
        System.out.println(r3_change_R);

        int r3_to_reflector = changeReflectors(rft, r3_change_R);
        System.out.println(r3_to_reflector);
    }

    public void convertChar(){

    }

    public static void startEncryption(Rotore rt1, Rotore rt2, Rotore rt3, Riflettore rft, char character){


    }

    public static int changeInnerRotorFromRight(Rotore r, char l){ return r.ar_sx.indexOf(l); }
    public static char changeBetweenRotorFromRight(Rotore r, int l_index){ return r.ar_dx.get(l_index); }

    public static int changeInnerRotorFromLeft(Rotore r, char l){ return r.ar_dx.indexOf(l); }
    public static char changeBetweenRotorFromLeft(Rotore r, int l_index){ return r.ar_sx.get(l_index); }


    public static int changeReflectors(Riflettore r, int l_index){
        char sx_l = r.ar_sx.get(l_index);
        return r.ar_dx.indexOf(sx_l);
    }

    public static void ShiftToRight(char a[],int n){
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

    private ArrayList<Character> arrayToArrayList(char[] a){
        char[] cloned_aArray = a.clone();
        ArrayList<Character> cloned_a = new ArrayList<>();

        for(char l: cloned_aArray) cloned_a.add(l);
        return cloned_a;
    }
}
