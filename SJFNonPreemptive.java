import java.util.Arrays;
import java.util.Scanner;

class SJFNProcess {
    int pid, burstTime, arrivalTime, waitingTime = 0, turnaroundTime = 0;
    boolean isCompleted = false;
    int startTime = 0; // New: to store start time
    int endTime = 0;   // New: to store end time

    public SJFNProcess(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class SJFNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        SJFNProcess[] processes = new SJFNProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new SJFNProcess(i + 1, bt, at);
        }

        // Sort processes based on arrival time initially
        Arrays.sort(processes, (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));

        int totalTime = 0, completedProcesses = 0;
        float totalWT = 0, totalTAT = 0;

        while (completedProcesses < n) {
            int shortestJobIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;

            // Find the process with the shortest burst time among those that have arrived
            for (int i = 0; i < n; i++) {
                if (!processes[i].isCompleted && processes[i].arrivalTime <= totalTime) {
                    if (processes[i].burstTime < minBurstTime) {
                        minBurstTime = processes[i].burstTime;
                        shortestJobIndex = i;
                    }
                }
            }

            if (shortestJobIndex == -1) {
                // If no process has arrived yet, move time forward
                totalTime++;
                continue;
            }

            // Process the selected job
            SJFNProcess currentProcess = processes[shortestJobIndex];
            currentProcess.startTime = totalTime; // Set start time
            currentProcess.waitingTime = totalTime - currentProcess.arrivalTime;
            totalTime += currentProcess.burstTime;
            currentProcess.endTime = totalTime; // Set end time
            currentProcess.turnaroundTime = currentProcess.waitingTime + currentProcess.burstTime;
            currentProcess.isCompleted = true;
            completedProcesses++;

            // Accumulate total waiting and turnaround times
            totalWT += currentProcess.waitingTime;
            totalTAT += currentProcess.turnaroundTime;
        }

        // Print Gantt Chart with start and end times
        System.out.println("\nGantt Chart:");
        for (SJFNProcess p : processes) {
            System.out.print("| P" + p.pid + " ");
        }
        System.out.println("|");
        for (SJFNProcess p : processes) {
            System.out.print(p.startTime + "\t ");
        }
        System.out.print(totalTime + "\n");

        // Print process details
        System.out.println("\nProcess\tArrival\tBurst\tWaiting\tTurnaround");
        for (SJFNProcess p : processes) {
            System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                               p.waitingTime + "\t" + p.turnaroundTime);
        }

        // Print average waiting time and turnaround time
        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Turnaround Time: " + (totalTAT / n));

        sc.close();
    }
}

/*
Enter number of processes: 3
Enter arrival time and burst time for process 1: 1 2
Enter arrival time and burst time for process 2: 0 5
Enter arrival time and burst time for process 3: 2 3

Gantt Chart:
| P2 | P1 | P3 |
0        5       7       10

Process Arrival Burst   Waiting Turnaround
P2      0       5       0       5
P1      1       2       4       6
P3      2       3       5       8
Average Waiting Time: 3.0
Average Turnaround Time: 6.3333335  
 */