package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class ChronalCalibrator
{
    public static void main(String[] args)
    {
        finalFrequency();
        firstRepeat();
    }
    
    private static void finalFrequency()
    {
        File numbers = new File("chronal numbers.txt");
        try
        {
            Scanner scanner = new Scanner(numbers);
            int sum = 0;
            while(scanner.hasNextLine())
            {
                sum = sumChanger(scanner.nextLine(), sum);
            }
            System.out.println("The final value is " + sum);
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Please make sure the file \"numbers.txt\" is located in the directory");
        }
    }
    
    private static void firstRepeat()
    {
        int sum = 0;
        HashSet<Integer> freqList = new HashSet<>();
        File numbers = new File("chronal numbers.txt");
        try
        {
            Scanner scanner = new Scanner(numbers);
            while(true)
            {
                while(scanner.hasNextLine())
                {
                    sum = sumChanger(scanner.nextLine(), sum);
                    
                    if(freqList.contains(sum))
                    {
                        System.out.println("The first repeated frequency is " + sum);
                        return;
                    }else
                    {
                        freqList.add(sum);
                    }
                }
                scanner = new Scanner(numbers);
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Please make sure the file \"numbers.txt\" is located in the directory");
        }
    }
    
    private static int sumChanger(String current, int sum)
    {
        if(current.charAt(0) == '-')
        {
            return sum - Integer.parseInt(current.substring(1));
        }else
        {
            return sum + Integer.parseInt(current.substring(1));
        }
    }
}
