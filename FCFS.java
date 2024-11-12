import java.util.Scanner;
class MProcess {
int pid, burstTime, arrivalTime, waitingTime, turnaroundTime;
public MProcess(int pid, int burstTime, int arrivalTime) 
{
this.pid = pid;
this.burstTime = burstTime;
this.arrivalTime = arrivalTime;
}
}

public class FCFS {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of processes: ");
int n = sc.nextInt();

MProcess[] processes = new MProcess[n];
for (int i = 0; i < n; i++) {
System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
int at = sc.nextInt();
int bt = sc.nextInt();
processes[i] = new MProcess(i + 1, bt, at);
}

// Sort processes based on arrival time
for (int i = 0; i < n - 1; i++) {
for (int j = 0; j < n - i - 1; j++) {
if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
MProcess temp = processes[j];
processes[j] = processes[j + 1];
processes[j + 1] = temp;
}
}
}

// Calculate waiting time and turnaround time
int totalTime = 0, totalWT = 0, totalTAT = 0;

for (MProcess p : processes) {
p.waitingTime = totalTime - p.arrivalTime;
totalTime += p.burstTime;
p.turnaroundTime = p.waitingTime + p.burstTime;
totalWT += p.waitingTime;
totalTAT += p.turnaroundTime;
}

// Print Gantt Chart
System.out.println("Gantt Chart: ");
for (MProcess p : processes) {
System.out.print("P" + p.pid + " ");
}
System.out.println("\n");

// Print process details
System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
for (MProcess p : processes) {
System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
p.waitingTime + "\t" + p.turnaroundTime);
}

System.out.println("Average Waiting Time: " + (totalWT / (float) n));
System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));
sc.close();
}
}


/*
 Enter number of processes: 5
Enter arrival time and burst time for process 1: 0 4
Enter arrival time and burst time for process 2: 1 3
Enter arrival time and burst time for process 3: 2 1
Enter arrival time and burst time for process 4: 3 2
Enter arrival time and burst time for process 5: 4 5
Gantt Chart: 
P1 P2 P3 P4 P5 

Process Arrival Burst   Waiting Turnaround
P1      0       4       0       4
P2      1       3       3       6
P3      2       1       5       6
P4      3       2       5       7
P5      4       5       6       11
Average Waiting Time: 3.8
Average Turnaround Time: 6.8
 */