public class Enigma {


    public static void main(String[] args) {
        Rotore r1 = new Rotore('c');
        r1.showPart(r1.dx);
        r1.showPart(r1.sx);


        Riflettore rft = new Riflettore();

        System.out.println(rft.dx);
        System.out.println(rft.sx);
    }

    public void convertChar(Rotore rt1, Rotore rt2, Rotore rt3, Riflettore rft){

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
}
