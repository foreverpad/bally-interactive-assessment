import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomNumberGenerator {
    // size of random numbers
    private static final int ONE_MILLION = 1000000;
    public static void main(String[] args) throws IOException {
        // specify the path of the output file
        File tempDir = new File(System.getProperty("java.io.tmpdir") + "/bally-interactive-take-home-assessment-chris-jin");
        // check if directory already exists, if not create a new directory
        if (!tempDir.exists()) {
            if (!tempDir.mkdir()) {
                System.out.println("Unable to create tmp folder!");
                return;
            }
        }

        Random random = new Random();
        // create a million random numbers
        List<Integer> randomNums = random.ints(0, Integer.MAX_VALUE).limit(ONE_MILLION).boxed().collect(Collectors.toList());

        // create output file for unsorted random numbers
        File randomNumUnsorted = new File(tempDir, "unsorted_randoms.txt");
        BufferedWriter bufferedWriterUnsorted = new BufferedWriter(new FileWriter(randomNumUnsorted));
        doWrite(bufferedWriterUnsorted, randomNums);

        // sort using built-in functions
        Collections.sort(randomNums);

        // create output file for sorted random numbers
        File randomNumSorted = new File(tempDir, "sorted_randoms.txt");
        BufferedWriter bufferedWriterSorted = new BufferedWriter(new FileWriter(randomNumSorted));
        doWrite(bufferedWriterSorted, randomNums);
    }

    protected static void doWrite(BufferedWriter bufferedWriter, List<Integer> nums) throws IOException {
        ListIterator<Integer> listIterator = nums.listIterator();
        // write the first number to the output file
        if (listIterator.hasNext()) {
            Integer firstNum = listIterator.next();
            bufferedWriter.write(firstNum.toString());
        }

        // iterate through and write each number to the output file
        // comma for formatting
        while (listIterator.hasNext()) {
            bufferedWriter.write("," + listIterator.next());
        }

        bufferedWriter.close();
    }
}
