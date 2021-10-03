package heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PermutationGeneratorMK2<E> {
    public List<E[]> heapPermutation(E[] initialArray) {
        List<E[]> permutationList = new ArrayList<>();
        List<byte[]> indexPermutationList = new ArrayList<>();
        byte[] indexArray = createByteArray(initialArray.length);

        if (initialArray.length <= PermutationTask.SEQUENTIAL_WORK_THRESHOLD) {
            heapPermutation(indexArray, initialArray.length, indexPermutationList);
            return;
        }

        ForkJoinPool pool = new ForkJoinPool(2);
        PermutationTask<E> thread = new PermutationTask<>(initialArray, -1);
        permutationList.addAll(pool.invoke(thread));
        return permutationList;
    }

    private byte[] createByteArray(int arrayLength) {
        byte[] array = new byte[arrayLength];

        for (byte b = 0; b < array.length; b++)
            array[b] = b;

        return array;
    }

    private void heapPermutation(byte[] array, int size, List<byte[]> permutationList) {
        if (size == 1) {
            permutationList.add(array.clone());
            return;
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(array, size - 1, permutationList);
            int indexToSwap = size % 2 == 1 ? 0 : i;

            byte temp = array[indexToSwap];
            array[indexToSwap] = array[size - 1];
            array[size - 1] = temp;
        }
    }
}

class PermutationTaskMK2 extends RecursiveTask<List<byte[]>> {

    @Override
    protected List<byte[]> compute() {
        return null;
    }
}