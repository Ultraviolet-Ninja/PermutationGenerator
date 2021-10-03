package heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PermutationGenerator<String> obj = new PermutationGenerator<>();
        Character[] cArr = {'c', 'd', 'w', 'e', 'r', '4', '2', '1', '0', '6', '%'};
        String[] splitter = "asdwfjtylomb".split("");
        Set<String[]> verification = new HashSet<>();
        long start = System.nanoTime();
        obj.heapPermutation(splitter, verification);
        long stop = System.nanoTime();

//        int i = 0;
//        while (i < permutations.size()) {
//            System.out.println(Arrays.toString(permutations.get(i)));
//            i++;
//        }
        System.out.println(stop - start);
    }
}
