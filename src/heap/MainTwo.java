package heap;

public class MainTwo {
    public static void main(String[] args) {
        PermutationGeneratorMK2<String> generator = new PermutationGeneratorMK2<>();
        String[] splitter = "asdwfjtyloum".split("");
        long start = System.nanoTime();
        generator.heapPermutation(splitter);
        long stop = System.nanoTime();
        System.out.println(stop - start);
    }
}
