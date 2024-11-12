import java.util.Scanner;
public class RoundRobinNonPreemptive
{ 
    public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of processes: ");
int n = sc.nextInt();
int[] burstTime = new int[n];
int[] remainingTime = new int[n];
int[] waitingTime = new int[n];
int[] turnaroundTime = new int[n];
System.out.print("Enter time quantum:"); 
int quantum = sc.nextInt();
for (int i = 0; i < n; i++) {
System.out.print("Enter burst time for process " + (i + 1) + ": ");
burstTime[i] = sc.nextInt();
remainingTime[i] = burstTime[i]; // Initialize remaining time
}
int time = 0;
boolean allCompleted = false;
String ganttChart = "";
while (!allCompleted) {
allCompleted = true; 
for (int i = 0; i < n; i++) {
if (remainingTime[i] > 0) {
allCompleted = false; // At least one process is still pending
if (remainingTime[i] > quantum) {
time += quantum;
remainingTime[i] -= quantum;
ganttChart += "P" + (i + 1) + " ";
} else {
time += remainingTime[i]; 
waitingTime[i] = time -burstTime[i]; 
turnaroundTime[i] = waitingTime[i] + burstTime[i]; 
ganttChart += "P" + (i + 1) + " ";
remainingTime[i] = 0; 
}
}
}
if (allCompleted) {
ganttChart += "idle ";
time++; 
} 
}
System.out.println("Gantt Chart: " + ganttChart.trim());
int totalWT = 0, totalTAT = 0;
System.out.println("Process\t\tBurst\t\tWaiting \t\tTurnaround");
for (int i = 0; i < n; i++) {
totalWT += waitingTime[i];
totalTAT += turnaroundTime[i];
System.out.println("P" + (i + 1) + "\t\t" +burstTime[i] + "\t\t" + waitingTime[i] + "\t\t\t" +turnaroundTime[i]);
}
System.out.printf("Average Waiting Time: %.2f\n", (totalWT / (float) n));
System.out.printf("Average TurnaroundTime: %.2f\n", (totalTAT / (float) n));
sc.close();
  }
}

/*
 Enter number of processes: 3
Enter time quantum:2
Enter burst time for process 1: 5
Enter burst time for process 2: 6
Enter burst time for process 3: 3
Gantt Chart: P1 P2 P3 P1 P2 P3 P1 P2 idle
Process         Burst           Waiting                 Turnaround
P1              5               7                       12
P2              6               8                       14
P3              3               8                       11
Average Waiting Time: 7.67
Average TurnaroundTime: 12.33
 */
