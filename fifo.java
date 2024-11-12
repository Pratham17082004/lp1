import java.util.*;
public class fifo{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of frames: ");
        int numofframes = scanner.nextInt();
        System.out.println("Enter number of pages: ");
        int numofpages = scanner.nextInt();
        System.out.println("Enter the page refernce string(space-separted): ");
        int[] pagerefstring = new int[numofpages];
        for(int i=0;i<numofpages;i++)
        {
            pagerefstring[i]=scanner.nextInt();
        }

        int[] frames = new int[numofframes];
        Arrays.fill(frames,-1);
        int pageFaults = 0;
        int currentIndex = 0;
        for(int page: pagerefstring)
        {
            boolean pageHit = false;
            for(int frame:frames)
            {
                if(frame==page)
                {
                    pageHit=true;
                    break;
                }
            }
            if(!pageHit)
            {
                frames[currentIndex]=page;
                currentIndex=(currentIndex+1)%numofframes;
                pageFaults++;
            }
            System.out.print("Frames: ");
            for(int frame : frames)
            {
                System.out.print(frame+ " ");
            }
            System.out.println();
        }
        System.out.println("Tota Page Faults: " +pageFaults);
        System.out.println("Page Fault ratio: " +pageFaults+":"+numofpages);
        scanner.close();
    }
}

/*
Enter the number of frames: 
3 
Enter number of pages: 
15
Enter the page refernce string(space-separted): 
7 0 1 2 0 3 0 4 2 3 0 3 1 2 0
Frames: 7 -1 -1 
Frames: 7 0 -1  
Frames: 7 0 1   
Frames: 2 0 1   
Frames: 2 0 1   
Frames: 2 3 1   
Frames: 2 3 0   
Frames: 4 3 0   
Frames: 4 2 0
Frames: 4 2 3
Frames: 0 2 3
Frames: 0 2 3
Frames: 0 1 3
Frames: 0 1 2
Frames: 0 1 2
Tota Page Faults: 12
Page Fault ratio: 12:15
 */