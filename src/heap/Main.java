package heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PermutationGenerator<String> obj = new PermutationGenerator<>();
        String[] splitter = "asdwfjtyloum".split("");
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
