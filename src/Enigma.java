public class Enigma {

    public static void main(String[] args) {
        Rotore r1 = new Rotore('c');
        r1.showPart(r1.dx);
        System.out.println("");
        r1.showPart(r1.sx);
    }
}
