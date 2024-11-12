import java.util.*;
public class lru
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int numofframes = scanner.nextInt();
        System.out.print("Enter the number of pages: ");
        int numofpages = scanner.nextInt();
        System.out.print("Enter the page reference string(space-seprated): ");
        int[] pagerefstring = new int[numofpages];
        for(int i = 0;i<numofpages;i++)
        {
            pagerefstring[i] = scanner.nextInt();
        }
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;
        for(int page: pagerefstring)
        {
            if(!frames.contains(page))
            {
                if(frames.size()>=numofframes)
                {
                    frames.removeFirst();
                }
                frames.addLast(page);
                pageFaults++;
            }else{
                frames.remove(frames.indexOf(page));
                frames.addLast(page);
            }
            System.out.print("Frames: ");
            for(int frame: frames){
                System.out.print(frame+ " ");
            }
            System.out.println();   
        }
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Page Fault Ratio :"+pageFaults+":"+numofpages);
        
    }
}

/*
Enter the number of frames: 3
Enter the number of pages: 7
Enter the page reference string(space-seprated): 1 0 1 2 3 5 3
Frames: 1 
Frames: 1 0 
Frames: 0 1 
Frames: 0 1 2        
Frames: 1 2 3        
Frames: 2 3 5        
Frames: 2 5 3        
Total Page Faults: 5 
Page Fault Ratio :5:7 
*/