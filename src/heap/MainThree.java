package heap;

import java.util.Arrays;
import java.util.Set;

public class MainThree {
    public static void main(String[] args) {
        String[] splitter = "asdwfjty".split("");
        PermutationGeneratorMk3<String> generator = new PermutationGeneratorMk3<>(splitter);

        long start = System.nanoTime();
        Set<String[]> permutations = generator.generateConditionalPermutations(array -> array[0].equals("a"));
        long stop = System.nanoTime();

        for (String[] array : permutations) {
            System.out.println(Arrays.toString(array));
        }

        System.out.println(stop - start);

        String[] splitter2 = "aa".split("");
        PermutationGeneratorMk3<String> generator2 = new PermutationGeneratorMk3<>(splitter2);
        long start2 = System.nanoTime();
        Set<String[]> permutations2 = generator2.generateAllPermutations();
        long stop2 = System.nanoTime();

        for (String[] array : permutations2) {
            System.out.println(Arrays.toString(array));
        }

        System.out.println(stop2 - start2);
    }
}
