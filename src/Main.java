import java.util.ArrayList;
import java.util.List;

/**
 * Created by elena on 02.04.17.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello");

        SquareWord squareWord = new SquareWord();


        List<List<String>> newMove1 = new ArrayList<>();
        newMove1 = squareWord.Resolve();


        List<String> tempLetters = new ArrayList<>();
        String L1 = "S";
        String L2 = "L";
        String L3 = "E";
        String L4 = "Z";
        String L5 = "A";
        tempLetters.add(L1);
        tempLetters.add(L2);
        tempLetters.add(L3);
        tempLetters.add(L4);
        tempLetters.add(L5);

        Swap swap = new Swap();
        List<List<String>> temp = swap.Resolv(tempLetters);

        for(int k = 0; k < temp.size(); k++){
            for(int i = 0; i < temp.get(i).size(); i++ ){
                System.out.print(temp.get(k).get(i));
            }
            System.out.println();
        }

//        for(int i = 0; i < startLetters.size(); i++){
//            for(int k = 0; k < startLetters.size(); k++){
//                System.out.print(newMove.get(i).get(k) + "; ");
//            }
//            System.out.println();
//        }

    }
}
