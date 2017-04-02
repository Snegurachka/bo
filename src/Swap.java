import com.sun.corba.se.spi.activation.EndpointInfoListHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elena on 02.04.17.
 */
public class Swap {


    public Swap() {}


    public List<List<String>> Resolv(List<String> litters)
    {
        List<List<String>> result = new ArrayList<>();
        Prmt(litters, 0, result);

        for (List<String> combo: result) {
            for (String  val: combo) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        return result;
    }


    private static void Prmt(List<String> litters, int i, List<List<String>> result) {
        if (i == litters.size() - 1) {
            result.add(litters);
            for(int s = 0; s < litters.size(); s++){
                System.out.print(litters.get(s));
            }
            System.out.println();
        } else {
            for (int j = i; j < litters.size(); j++) {
                Aswap(litters, i, j);
                Prmt(litters, i + 1, result);
                Aswap(litters, i, j);
            }
        }
    }


    private static void Aswap(List<String> list, int i, int j) {
        String k = list.get(i);
        list.set(i, list.get(j));
        list.set(j, k);
    }




}

/*
public class Main {
    public static void main(String[] args) {
        int[] pa = new int[] {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        prmt(pa, 0, result);

        for (List<Integer> combo: result) {
            for (Integer val: combo) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static List<Integer> toList(int[] pa) {
        List<Integer> result = new ArrayList<>();
        for (int a: pa) {
            result.add(a);
        }
        return result;
    }

    private static void prmt(int[] pa, int i, List<List<Integer>> result) {
        if (i == pa.length - 1) {
            result.add(toList(pa));
        } else {
            for (int j = i; j < pa.length; j++) {
                aswap(pa, i, j);
                prmt(pa, i + 1, result);
                aswap(pa, i, j);
            }
        }
    }

    private static void aswap(int[] pa, int i, int j) {
        int k = pa[i];
        pa[i] = pa[j];
        pa[j] = k;
    }
}

*/
