package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class PermutationGeneratorMk4<E> {
    public static final int FULL_PERMUTATION_ARRAY_SIZE_LIMIT = 12;

    private final E[] elements;
    private Predicate<E[]> condition;

    public PermutationGeneratorMk4(E[] elements) {
        this.elements = elements;
        condition = null;
    }

    public Set<List<E>> generateAllPermutations() {
        if (elements.length > FULL_PERMUTATION_ARRAY_SIZE_LIMIT)
            throw new IllegalArgumentException("Array is too large to compute");
        Set<List<E>> output = new HashSet<>();
        heapPermutation(output, elements.length);
        return output;
    }

    public Set<List<E>> generateConditionalPermutations(Predicate<E[]> condition) {
        if (elements.length >= FULL_PERMUTATION_ARRAY_SIZE_LIMIT) {
            ForkJoinPool pool = new ForkJoinPool(2);
            ConditionalPermutationTask<E> thread = new ConditionalPermutationTask<>(elements, -1, condition);
            return pool.invoke(thread);
        }

        Set<List<E>> output = new HashSet<>();
        this.condition = condition;
        heapPermutation(output, elements.length);
        this.condition = null;
        return output;
    }

    private void heapPermutation(Set<List<E>> permutationCollection, int size) {
        if (areConditionsMet(size)) {
            permutationCollection.add(Arrays.asList(elements.clone()));
            return;
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(permutationCollection, size - 1);
            int indexToSwap = size % 2 == 1 ? 0 : i;

            E temp = elements[indexToSwap];
            elements[indexToSwap] = elements[size - 1];
            elements[size - 1] = temp;
        }
    }

    private boolean areConditionsMet(int size) {
        if (condition != null) {
            return size == 1 && condition.test(elements);
        }
        return size == 1;
    }
}

class ConditionalPermutationTask<E> extends RecursiveTask<Set<List<E>>> {
    private static final byte MAX_GUARANTEED_INDICES = 2;

    private final Predicate<E[]> condition;
    private final int guaranteedIndex;
    private E[] array;

    public ConditionalPermutationTask(E[] array, int guaranteedIndex, Predicate<E[]> condition) {
        this.condition = condition;
        this.guaranteedIndex = guaranteedIndex;
        this.array = array;
    }

    @Override
    protected Set<List<E>> compute() {
        Set<List<E>> output = new HashSet<>();

        if (guaranteedIndex == -1 || guaranteedIndex < MAX_GUARANTEED_INDICES) {
            int newIndex = guaranteedIndex + 1;
            List<ConditionalPermutationTask<E>> threadList = new ArrayList<>();
            for (int i = 0; i < array.length - newIndex; i++) {
                threadList.add(new ConditionalPermutationTask<>(array.clone(), newIndex, condition));
                rotateArray(newIndex);
            }
            array = null;

            threadList.forEach(thread -> {
                thread.fork();
                output.addAll(thread.join());
            });
            return output;
        }

        sequentialHeapGeneration(array, array.length, output);
        return output;
    }

    private void rotateArray(int start) {
        E temp = array[start];
        if (array.length - 1 - start >= 0)
            System.arraycopy(array, start + 1, array, start, array.length - 1 - start);
        array[array.length - 1] = temp;
    }

    private void sequentialHeapGeneration(E[] array, int size, Set<List<E>> set) {
        int startingIndex = 1 + guaranteedIndex;
        if (areConditionsMet(size, startingIndex)) {
            set.add(Arrays.asList(array));
            return;
        }

        for (int i = 0; i < size - startingIndex; i++) {
            sequentialHeapGeneration(array, size - 1, set);
            int indexToSwap = size % 2 == 1 ? startingIndex : i;

            E temp = array[indexToSwap];
            array[indexToSwap] = array[size - 1];
            array[size - 1] = temp;
        }
    }

    private boolean areConditionsMet(int size, int startingIndex) {
        boolean isSizeOne = size - startingIndex == 1;
        if (condition != null) {
            return isSizeOne && condition.test(array);
        }
        return isSizeOne;
    }
}
