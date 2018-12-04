package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FabricOrganizer
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Overlapping area: " + discoverOverlaps());
            System.out.println("Claim with no overlaps: " + findNonOverlapping());
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found. Please ensure your data is in a file called \"claims.txt\".");
        }
    }

    private static int discoverOverlaps() throws FileNotFoundException
    {
        int counter = 0;
        Scanner s = new Scanner(new File("claims.txt"));
        HashMap<String, Integer> coordinateClaimed = new HashMap<>();
        String claim, coordinates;
        int startingX, startingY, width, height;
        while(s.hasNextLine())
        {
            claim = s.nextLine();
            claim = claim.replace(" ", "").split("@")[1];
            startingX = Integer.parseInt(claim.split(",")[0]);
            claim = claim.split(",")[1];
            startingY = Integer.parseInt(claim.split(":")[0]);
            claim = claim.split(":")[1];
            width = Integer.parseInt(claim.split("x")[0]);
            height = Integer.parseInt(claim.split("x")[1]);
            for(int i = 1; i <= width; i++)
            {
                for(int j = 0; j < height; j++)
                {
                    coordinates = "(" + (startingX+i) + "," + (startingY+j) + ")";
                    if(coordinateClaimed.containsKey(coordinates))
                    {
                        if(coordinateClaimed.get(coordinates) == 1)
                        {
                            counter++;
                            coordinateClaimed.put(coordinates, 2);
                        }
                    }
                    else
                    {
                        coordinateClaimed.put(coordinates, 1);
                    }
                }
            }
        }
        return counter;
    }

    private static int findNonOverlapping() throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("claims.txt"));
        HashMap<String, Integer> coordinateClaimed = new HashMap<>();
        String claim, coordinates;
        int startingX, startingY, width, height;
        while(s.hasNextLine())
        {
            claim = s.nextLine();
            claim = claim.replace(" ", "").split("@")[1];
            startingX = Integer.parseInt(claim.split(",")[0]);
            claim = claim.split(",")[1];
            startingY = Integer.parseInt(claim.split(":")[0]);
            claim = claim.split(":")[1];
            width = Integer.parseInt(claim.split("x")[0]);
            height = Integer.parseInt(claim.split("x")[1]);
            for(int i = 1; i <= width; i++)
            {
                for(int j = 0; j < height; j++)
                {
                    coordinates = "(" + (startingX + i) + "," + (startingY + j) + ")";
                    if(coordinateClaimed.containsKey(coordinates))
                    {
                        if(coordinateClaimed.get(coordinates) == 1)
                        {
                            coordinateClaimed.put(coordinates, 2);
                        }
                    }else
                    {
                        coordinateClaimed.put(coordinates, 1);
                    }
                }
            }
        }
        s = new Scanner(new File("claims.txt"));
        int claimNumber = 0;
        boolean overlapping;
        while(s.hasNextLine())
        {
            claim = s.nextLine();
            claimNumber = Integer.parseInt(claim.replace("#", "").replace(" ", "").split("@")[0]);
            claim = claim.replace(" ", "").split("@")[1];
            startingX = Integer.parseInt(claim.split(",")[0]);
            claim = claim.split(",")[1];
            startingY = Integer.parseInt(claim.split(":")[0]);
            claim = claim.split(":")[1];
            width = Integer.parseInt(claim.split("x")[0]);
            height = Integer.parseInt(claim.split("x")[1]);
            overlapping = false;
            for(int i = 1; i <= width; i++)
            {
                for(int j = 0; j < height; j++)
                {
                    coordinates = "(" + (startingX + i) + "," + (startingY + j) + ")";
                    if(coordinateClaimed.get(coordinates) > 1)
                    {
                        overlapping = true;
                    }
                }
            }
            if(!overlapping)
            {
                return claimNumber;
            }
        }
        return claimNumber;
    }
}
