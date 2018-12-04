package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryManagement
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Checksum: " + findChecksum());
            System.out.println(findBox());
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found. Ensure a file named \"IDList.txt\" is within this program's directory. ");
        }
    }

    private static int findChecksum() throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("IDList.txt"));
        HashMap<String, Integer> letterCount;
        String currentID;
        int doubleCount = 0, tripleCount = 0;
        while(s.hasNextLine())
        {
            letterCount = new HashMap<>();
            currentID = s.nextLine();
            for(String letter : currentID.split("(?!^)"))
            {
                if(letterCount.containsKey(letter))
                {
                    letterCount.put(letter, letterCount.get(letter) + 1);
                }
                else
                {
                    letterCount.put(letter, 1);
                }
            }
            for(String letter : letterCount.keySet())
            {
                if(letterCount.get(letter) == 2)
                {
                    doubleCount++;
                    break;
                }
            }
            for(String letter : letterCount.keySet())
            {
                if(letterCount.get(letter) == 3)
                {
                    tripleCount++;
                    break;
                }
            }
        }
        return doubleCount * tripleCount;
    }

    private static String findBox() throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("IDList.txt"));
        Scanner c;
        String current, compared;
        StringBuilder currentCompared = new StringBuilder();
        while(s.hasNextLine())
        {
            current = s.nextLine();
            c = new Scanner(new File("IDList.txt"));
            while(c.hasNextLine())
            {
                compared = c.nextLine();
                currentCompared = new StringBuilder();
                if(current.length() == compared.length())
                {
                    for(int i = 0; i < current.length(); i++)
                    {
                        if(current.charAt(i) == compared.charAt(i))
                        {
                            currentCompared.append(current.charAt(i));
                        }
                    }
                }
                if(currentCompared.length() == current.length() - 1)
                {
                    return "Correct boxes contain: " + currentCompared;
                }
            }
        }
        return "Correct boxes not found.";
    }
}
