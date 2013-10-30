/* Scheduler.java
 * Operating Systems
 * Fall 2013
 * Curtiss J Wiggins
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Scheduler {

	public static void arriving(LinkedList<Process> rqueue,
		LinkedList<Process> nrqueue, double CPUTime, double Atime){

		int i = 0;
		if(CPUTime == Atime){
			while(nrqueue.size() > 0){
				if(nrqueue.get(i).Atime != Atime+1){
				rqueue.add(nrqueue.remove());

				}else{
					break;
				}
			}
		}
	}

	public static void executing(Process process){
        double j = process.Btime, runtime = 0;
	}
	public static void main (String[] args){
		double Atime,Wtime,Btime,Tat,Twt,CPUTime;
		int NumOfProcesses = 0, i = 0;
        LinkedList<Process> rqueue = new LinkedList<Process> ();
		LinkedList<Process> nrqueue = new LinkedList<Process> ();

		Scanner sc = new Scanner (System.in);
		System.out.println("Please input number of Processes.");
		NumOfProcesses = sc.nextInt();
		System.out.println(NumOfProcesses);

		/*
		 * Loop for Adding Processes
		 */
		while (i < NumOfProcesses){
			System.out.println("Please input Arrival Time:");
			Atime = sc.nextDouble();
			System.out.println("Please input Burst Time:");
			Btime = sc.nextDouble();
			Process task = new Process (Atime, Btime, "P"+(i+1));
			if (task.Atime == 0){
				rqueue.add(task);
			}
			else{
				nrqueue.add(task);
			}
			i++;
		}
			System.out.println("In Ready Queue: " + rqueue.get(0).pid);

			/*
			 * Printing out processes in not ready queue. These processes
			 *  technically haven't arrived yet.
			 */
			i = 0;
			System.out.print("In Not Ready Queue:");
			while (i < nrqueue.size()){
				System.out.println(nrqueue.get(i).pid);
				i++;
			}

			/*
			 * Time to start executing some processes.
			 */
			CPUTime = 0.0;
			i = 1;
			double j = rqueue.get(i-1).Btime;
			Process executing = rqueue.remove(i-1);
			boolean notdone = true;
		    while (notdone){
				if (CPUTime != j){
					if(CPUTime == i){
						arriving(rqueue, nrqueue, CPUTime, i);
						i++;
					}
					CPUTime = round.round(CPUTime+.1);
				}
				else{
					notdone = false;
				}
			}
            System.out.println("Process Done.");
			System.out.println("In Ready Queue Now:");
			for(i=0;i<rqueue.size();i++){
				System.out.println(rqueue.get(i).pid);
			}
	}
}

class Process {
	public double Atime, Btime;
	public String pid;
    public Process (double Atime, double Btime, String pid) {
		this.Atime=Atime;
		this.Btime=Btime;
		this.pid = pid;
	}
}
class round {
public static double round(double val){
	DecimalFormat  df = new DecimalFormat ("#.#");
	val = Double.valueOf(df.format(val));
	return val;
}
}
