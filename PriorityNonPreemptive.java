import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class PriorityProcess {
int pid, burstTime, priority, waitingTime, turnaroundTime;
public PriorityProcess(int pid, int burstTime, int priority) {
this.pid = pid;
this.burstTime = burstTime;
this.priority = priority;
}
}

public class PriorityNonPreemptive {
public static void main(String[] args) 
{
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of processes: ");
int n = sc.nextInt();
PriorityProcess[] processes = new PriorityProcess[n];
for (int i = 0; i < n; i++) {
System.out.print("Enter burst time and priority for process " + (i + 1) + ": ");
int bt = sc.nextInt(); 
int priority = sc.nextInt();
processes[i] = new PriorityProcess(i + 1, bt, priority);
}

System.out.println("\nBefore sorting(Based on input): "); 
for (PriorityProcess p : processes) 
{
    System.out.println("Process P" + p.pid + ": Burst Time = " + p.burstTime + ", Priority = " + p.priority);
}

for (int i = 0; i < n - 1; i++) {
for (int j = 0; j < n - i - 1; j++) {
if (processes[j].priority > processes[j + 1].priority) 
{
PriorityProcess temp = processes[j];
processes[j] = processes[j + 1];
processes[j + 1] = temp; 
} 
}
}

System.out.println("\nAfter sorting based on priority: ");
for (PriorityProcess p : processes) 
{ 
    System.out.println("Process P" + p.pid + ": Burst Time = " + p.burstTime + ", Priority = " + p.priority); 
}

int totalTime = 0, totalWT = 0, totalTAT = 0;
for (PriorityProcess p : processes) 
{
p.waitingTime = totalTime;
totalTime += p.burstTime;
p.turnaroundTime = p.waitingTime + p.burstTime;
totalWT += p.waitingTime;
totalTAT += p.turnaroundTime;
}

System.out.println("\nGantt Chart: ");
for (PriorityProcess p : processes) {
System.out.print("P" + p.pid + " ");
}
System.out.println();

System.out.println("\nProcess\tBurst\tPriorit y\tWaiting\tTurnaround");
for (PriorityProcess p : processes) {
System.out.println("P" + p.pid + "\t" + p.burstTime + "\t" + p.priority + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
}

System.out.println("\nAverage Waiting Time: " + (totalWT / (float) n));
System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));
sc.close(); 
}
}

/*
Enter number of processes: 4
Enter burst time and priority for process 1: 10 2
Enter burst time and priority for process 2: 5 1
Enter burst time and priority for process 3: 2 0
Enter burst time and priority for process 4: 20 3

Before sorting(Based on input): 
Process P1: Burst Time = 10, Priority = 2
Process P2: Burst Time = 5, Priority = 1 
Process P3: Burst Time = 2, Priority = 0 
Process P4: Burst Time = 20, Priority = 3

After sorting based on priority:
Process P3: Burst Time = 2, Priority = 0
Process P2: Burst Time = 5, Priority = 1
Process P1: Burst Time = 10, Priority = 2
Process P4: Burst Time = 20, Priority = 3

Gantt Chart:
P3 P2 P1 P4

Process Burst   Priorit y       Waiting Turnaround
P3      2       0               0               2
P2      5       1               2               7
P1      10      2               7               17
P4      20      3               17              37

Average Waiting Time: 6.5
Average Turnaround Time: 15.75
 */