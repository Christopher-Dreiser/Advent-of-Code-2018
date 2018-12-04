package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class GuardScheduler
{
    public static void main(String[] args)
    {
        try
        {
            sortSchedule();
            int sleepingGuard = findSleepiestGuard();
            System.out.println("The guard with the most time sleeping is #" + sleepingGuard + ". The minute when the guard sleeps the most is " + findSleepiestTime(sleepingGuard));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Please ensure your input is in a file named \"guard schedule.txt\".");
        }
    }

    private static void sortSchedule() throws FileNotFoundException
    {
        ArrayList<String> textList = new ArrayList<>();
        Scanner s = new Scanner(new File("guard schedule.txt"));
        while(s.hasNextLine())
        {
            textList.add(s.nextLine());
        }
        textList.sort(Comparator.naturalOrder());
        PrintWriter pw = new PrintWriter(new FileOutputStream("sorted guard schedule.txt"));
        for(String line : textList)
        {
            pw.println(line);
        }
        pw.close();
    }

    private static int findSleepiestGuard() throws FileNotFoundException
    {
        int elfID = 0;
        Scanner s = new Scanner(new File("sorted guard schedule.txt"));
        HashMap<Integer, Integer> hoursAsleep = new HashMap<>();
        int timeAsleep = 0, timeAwake;
        int currentElf = 0;
        String currentLog;
        while(s.hasNextLine())
        {
            currentLog = s.nextLine();
            currentLog = currentLog.substring(12);
            if(currentLog.contains("shift"))
            {
                currentElf = Integer.parseInt(currentLog.split("#")[1].split(" ")[0]);
            }
            else if(currentLog.contains("sleep"))
            {
                timeAsleep = Integer.parseInt(currentLog.split(":")[1].split("]")[0]);
            }
            else if(currentLog.contains("wake"))
            {
                timeAwake = Integer.parseInt(currentLog.split(":")[1].split("]")[0]);
                if(hoursAsleep.containsKey(currentElf))
                {
                    hoursAsleep.put(currentElf, hoursAsleep.get(currentElf) + timeAwake - timeAsleep);
                }
                else
                {
                    hoursAsleep.put(currentElf, timeAwake - timeAsleep);
                }
            }
        }
        int currentBest = 0;
        for(int currentElfID : hoursAsleep.keySet())
        {
            if(hoursAsleep.get(currentElfID) > currentBest)
            {
                elfID = currentElfID;
                currentBest = hoursAsleep.get(currentElfID);
            }
        }
        return elfID;
    }

    private static int findSleepiestTime(int elfID) throws FileNotFoundException
    {
        int minutes = 0;
        Scanner s = new Scanner(new File("sorted guard schedule.txt"));
        HashMap<Integer, Integer> minuteCount = new HashMap<>();
        int currentElf = 0;
        int asleepMinute = 0, awakeMinute;
        String currentLog;
        while(s.hasNextLine())
        {
            currentLog = s.nextLine();
            if(currentLog.contains("#"))
            {
                currentElf = Integer.parseInt(currentLog.split("#")[1].split(" ")[0]);
            }
            else if(currentElf == elfID && currentLog.contains("sleep"))
            {
                asleepMinute = Integer.parseInt(currentLog.split(":")[1].split("]")[0]);
            }
            else if(currentElf == elfID && currentLog.contains("wake"))
            {
                awakeMinute = Integer.parseInt(currentLog.split(":")[1].split("]")[0]);
                for(int i = asleepMinute; i < awakeMinute; i++)
                {
                    if(minuteCount.containsKey(i))
                    {
                        minuteCount.put(i, minuteCount.get(i) + 1);
                    }
                    else
                    {
                        minuteCount.put(i, 1);
                    }
                }
            }
        }
        int greatestMinutes = 0;
        for(int minute : minuteCount.keySet())
        {
            if(minuteCount.get(minute) > greatestMinutes)
            {
                minutes = minute;
            }
        }
        return minutes;
    }
}
