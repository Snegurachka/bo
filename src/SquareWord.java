import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static java.sql.JDBCType.NULL;

/**
 * Created by elena on 02.04.17.
 */
public class SquareWord {

    private List<String> letters;
    private List<List<String>> startPosition;
    private List<List<List<String>>> usedStartPoints;
    private List<List<List<String>>> solTree;
    private List<List<String>> bestMove;

    public List<String> getLetters(){ return letters;}
    public List<List<String>> getStartPosition(){ return startPosition;}

    public SquareWord(){
        letters = new ArrayList<>();
        letters = CreateLetters();
        startPosition = new ArrayList<>();
        startPosition = CreateStartPosition();
        usedStartPoints = new ArrayList<>();
        solTree = new ArrayList<>();
        bestMove = new ArrayList<>();



    }

    //решение
    public List<List<String>> Resolve(){

        List<List<String>> move = new ArrayList<>();
        List<List<String>> tempMove = new ArrayList<>();
        Boolean key;

//        do{
            List<List<String>> newMove = StartPositionGeneration();
            key = false;

            for(int i = 0; i < usedStartPoints.size(); i++){
                if(CompareMove(newMove, usedStartPoints.get(i))){
                    key = true;
                    break;
                }
            }

            if(key == false){
                solTree.add(newMove);
                usedStartPoints.add(newMove);
            }

            //подсчет конфликтов
            Integer countConflict;
            countConflict = ConflictCount(newMove);

            if(countConflict == 0){
                return newMove;
            }

            tempMove = newMove;

            for (int i = 0; i < newMove.size(); i++) {

                List<Integer> tempPosition = new ArrayList<>();// позиции разрешенных перестановок
                List<String> tempFieldStr = new ArrayList<>(); //все разрешенные к перестановке буквы

                for (int j = 0; j < newMove.size(); j++) {
                    if (startPosition.get(i).get(j) == " ") {
                        tempFieldStr.add(newMove.get(i).get(j));
                        tempPosition.add(j);

                    }
                }

                if (tempFieldStr.size() == 0) {
                    continue;
                }

                List<List<String>> allPermutations = new ArrayList<>(); //!!!!!!!!!!!!!!!!!!!!!!!!//

//                for (int k = 0; k < allPermutations.size(); k++) {
//                    tempMove.set(i, allPermutations.get(k));
//                    int countConf = ConflictCount(tempMove);
//
//                    if (countConf == 0) {
//                        solTree.add(tempMove);
//                        return tempMove;
//                    }
//
//                    if (countConf < countConflict) {
//                        bestMove = tempMove;
//                        countConflict = countConf;
//                    }
//                }

//                solTree.add(bestMove);
//                tempMove.set(i, bestMove.get(i));
            }

//            solTree.clear();


//        } while (true);

        return newMove;

    }


    private Boolean CompareMove(List<List<String>> newMove, List<List<String>> tempMove){
        for(int i = 0; i < newMove.size(); i++){
            for(int j = 0; j < newMove.get(i).size(); j++){
                if(newMove.get(i).get(j) != tempMove.get(i).get(j)){
                    return false;
                }
            }
        }
        return true;
    }


    //подсчет количества конфликтов
    private Integer ConflictCount(List<List<String>> move){
        Integer countConflict = 0;
        List<String> tempLeftDiagonal = new ArrayList<>();
        List<String> tempRightDiagonal = new ArrayList<>();


        //проверка по диагонали
        for(int i = 0; i < move.size(); i++){
            tempLeftDiagonal.add(move.get(i).get(i));
            tempRightDiagonal.add(move.get(i).get(move.size() - 1 - i));
        }
        int counterLeft;
        int counterRight;


        for(int i = 0; i < letters.size(); i++){
            counterLeft = 0;
            counterRight = 0;
            for(int j = 0; j < tempLeftDiagonal.size(); j++){
                if(letters.get(i) == tempLeftDiagonal.get(j)){
                    counterLeft ++;
                }
                if(letters.get(i) == tempRightDiagonal.get(j)){
                    counterRight ++;
                }
            }
            if(counterLeft == 0){
                countConflict ++;
            }
            if(counterRight == 0){
                countConflict ++;
            }

        }

        //проверка по вертикали
        for(int i = 0; i < move.size(); i++){
            List<String> tempColumn = new ArrayList<>();
            for(int j = 0; j < move.size(); j++){
                tempColumn.add(move.get(j).get(i));
            }

            int counterColumn;
            for(int k = 0; k < letters.size(); k++){
                counterColumn = 0;
                for(int j = 0; j < tempColumn.size(); j++){
                    if(letters.get(k) == tempColumn.get(j)){
                        counterColumn++;
                    }
                }
                if(counterColumn == 0){
                    countConflict ++;
                }
            }


        }



        return countConflict;
    }







    //заполнение начального положения
    public List<List<String>> StartPositionGeneration(){
        List<List<String>> newMove = new ArrayList<>();

        for(int i = 0; i < startPosition.size(); i++){
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < startPosition.size(); j++){
                temp.add(startPosition.get(i).get(j));
            }
            newMove.add(temp);
        }
        Boolean key = false;


        for (int i = 0; i < newMove.size(); i++){



            List<String> tempL = new ArrayList<>();
            //должны найти буквы которых нету в строке
            for(int j = 0; j < letters.size(); j++){
                for(int k = 0; k < newMove.get(i).size(); k++){
                    if(letters.get(j) == newMove.get(i).get(k)){
                        key = true;
                        break;
                    } else {
                        key = false;
                    }
                }
                if (key == false){
                    tempL.add(letters.get(j));
                }
            }

            Integer n;
            int itoe;
            for (int j = 0; j < newMove.get(i).size(); j++){
                if (newMove.get(i).get(j) == " "){
                    n = tempL.size();
                    itoe = (int)(Math.random() * n);
                    newMove.get(i).set(j, tempL.get(itoe));
                    tempL.remove(itoe);
                }
            }
        }


        return newMove;
    }


    private List<String> CreateLetters(){
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
        return tempLetters;
    }

    private List<List<String>> CreateStartPosition(){
        List<List<String>> tempStart = new ArrayList<>();
        Integer n = 5;
        for(int i = 0; i < n; i++){
            List<String> tempString = new ArrayList<>();
            for(int k = 0; k < n; k++){
                tempString.add(" ");
            }
            tempStart.add(tempString);
        }

        tempStart.set(0, letters);

        tempStart.get(2).set(2, "L");
        tempStart.get(2).set(3, "E");
        tempStart.get(2).set(4, "S");

        return tempStart;
    }


}
