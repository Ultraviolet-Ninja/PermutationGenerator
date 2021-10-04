package heap;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class PermutationGeneratorMk3<E> {
    public static final int ARRAY_SIZE_LIMIT = 12;

    private final E[] array;
    private Predicate<E[]> condition;

    public PermutationGeneratorMk3(E[] array) {
        if (array.length > ARRAY_SIZE_LIMIT)
            throw new IllegalArgumentException("Array is too large to compute in a reasonable time");
        this.array = array;
        condition = null;
    }

    public Set<E[]> generateAllPermutations() {
        Set<E[]> permutations = new HashSet<>();
        heapPermutation(permutations, array.length);
        return permutations;
    }

    public Set<E[]> generateConditionalPermutations(Predicate<E[]> condition) {
        Set<E[]> permutations = new HashSet<>();
        this.condition = condition;
        heapPermutation(permutations, array.length);
        this.condition = null;
        return permutations;
    }

    private void heapPermutation(Set<E[]> permutationCollection, int size) {
        if (areConditionsMet(size)) {
            permutationCollection.add(array.clone());
            return;
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(permutationCollection, size - 1);
            int indexToSwap = size % 2 == 1 ? 0 : i;

            E temp = array[indexToSwap];
            array[indexToSwap] = array[size - 1];
            array[size - 1] = temp;
        }
    }

    private boolean areConditionsMet(int size) {
        if (size == 1) {
            if (condition == null) return true;
            return condition.test(array);
        }
        return false;
    }
}
