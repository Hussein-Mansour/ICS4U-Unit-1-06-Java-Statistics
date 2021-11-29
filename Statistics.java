/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Hussein
* @version 1.0
* @since   2020-11-27
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers) {
        // sum of the array
        int sum = 0;
        // Advanced for loop
        for (int num : arrayOfIntegers) {
            sum = sum + num;
        }
        // number of items in array
        final int size = arrayOfIntegers.length;
        // mean calculation
        final int calculation = sum / size;
        // return statment
        return calculation;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers) {
        // rearrange the array smallest to largest
        Arrays.sort(arrayOfIntegers);
        // mid of the array
        final int mid;
        if (arrayOfIntegers.length % 2 == 0) {
            mid = ((int) arrayOfIntegers[arrayOfIntegers.length / 2]
                  + (int) arrayOfIntegers[arrayOfIntegers.length / 2 - 1]) / 2;
        } else {
            mid = (int) arrayOfIntegers[arrayOfIntegers.length / 2];
        }
        // return statment
        return mid;
    }

    /**
    * The mode() function.
    *
    * @param numbers the collection of integers
    * @return the mode of the integers
    */
    public static Set<Integer> mode(final Integer[] numbers) {
        final Set<Integer> modes = new HashSet<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j] && i != j) {
                    // duplicate element found
                    modes.add(numbers[i]);
                    break;
                }
            }
        }
        return modes;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("/home/ubuntu", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers);
        final double median = median(arrayOfNumbers);
        final Set<Integer> mode = mode(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                            + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}
