package heap;

import java.util.List;
import java.util.Set;

public class MainFive {
    public static void main(String[] args) {
        String[] splitter = "arkcgo".split("");
        PermutationGeneratorMk5<String> permutationGenerator = new PermutationGeneratorMk5<>(splitter);

        Set<List<String>> fullSet;

        long start = System.nanoTime();
        fullSet = permutationGenerator.generateConditionalPermutations(array -> array[2].equals("o"));
        long stop = System.nanoTime();

        System.out.println(fullSet.size());
        System.out.println((stop - start)/1_000_000.0);

        fullSet.forEach(System.out::println);
    }
}
