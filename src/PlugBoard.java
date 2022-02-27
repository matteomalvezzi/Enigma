import java.util.ArrayList;

public class PlugBoard {


    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public ArrayList<Character> pg_dx;
    public ArrayList<Character> pg_sx;

    public PlugBoard() {
        this.pg_dx = arrayToArrayList(ALPHABET);
        this.pg_sx = new ArrayList<Character>();
        for (int i = 0; i < pg_dx.size(); i++) { pg_sx.add(null); }
    }


    public void setPlugCable(char l1, char l2){
        int l1_index = pg_dx.indexOf(l1);
        int l2_index = pg_dx.indexOf(l2);

        pg_sx.set(l1_index, l2);
        pg_sx.set(l2_index, l1);
    }
    public char doChange(char lc){
        int lc_index = pg_dx.indexOf(lc);
        if(pg_sx.get(lc_index) != null){
            return pg_sx.get(lc_index);
        }else{
            System.out.println("NON CI SONO SCAMBIATORI COLLEGATI");
            return lc;
        }
    }

    private ArrayList<Character> arrayToArrayList(char[] a){
        char[] cloned_aArray = a.clone();
        ArrayList<Character> cloned_a = new ArrayList<>();

        for(char l: cloned_aArray) cloned_a.add(l);
        return cloned_a;
    }
}
