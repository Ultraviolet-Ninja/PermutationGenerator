package heap;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

public class MainFour {
    public static void main(String[] args) {
        DecimalFormat dc = new DecimalFormat("###,###,###,###,###");
        String[] splitter = "adwarawart7j".split("");
        PermutationGeneratorMk4<String> obj = new PermutationGeneratorMk4<>(splitter);
        long start = System.nanoTime();
        Set<List<String>> output = obj.generateConditionalPermutations(
                array -> array[1].equals(array[3])
        );
        long stop = System.nanoTime();

//        for (List<String> permutation : output) {
//            System.out.println(permutation);
//        }
//        output.forEach(permutation -> {
//            boolean result = permutation.get(1).equals(permutation.get(3));
//            if (result) {
//                System.out.println(true);
//            } else {
//                System.err.println(false);
//            }
//        });

        System.out.println(output.size());
        System.out.println(dc.format(stop - start));
    }
}
