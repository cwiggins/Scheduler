/* Scheduler.java
 * Operating Systems
 * Fall 2013
 * Curtiss J Wiggins
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Scheduler {
	LinkedList<Process> rqueue;

	public Scheduler (LinkedList<Process> rqueue){
		this.rqueue = rqueue;
	}

	public Scheduler(){rqueue = new LinkedList<Process>();}

	public void arriving(LinkedList<Process> nrqueue, double CPUTime, double Atime){

		int i = 0;
		if(CPUTime == Atime){
			while(nrqueue.size() > 0){
				if(nrqueue.get(i).getAtime() != Atime+1){
				this.rqueue.add(nrqueue.remove());

				}else{
					break;
				}
			}
		}
	}

	public Process remove(int i){
		return this.rqueue.remove(i);
	}
	public Process get(int i){ return rqueue.get(i);}

	public int size(){return rqueue.size();}

	public static void main (String[] args){
		int Wtime,Tat,Twt,Trt = 0;
		double CPUTime;
		int i = 0;
		Scheduler rqueue = new Scheduler();
		LinkedList<Process> nrqueue = new LinkedList<Process> ();
        getProcesses.getProcesses(rqueue.rqueue,nrqueue);

			/*
			 * Time to start executing some processes.
			 */
		for (i=0;i<rqueue.size();i++)
			Trt += rqueue.get(i).getBtime();

			CPUTime = 0.0;
			Process executing = rqueue.remove(0);
		   double runtime = 0.0, starttime = CPUTime, endtime = 0.0, time_remaining = 0.0;
			i = 1;
			double j = executing.getBtime();
			while (CPUTime < Trt){
				if(CPUTime == i && nrqueue.size() > 0){
					rqueue.arriving(nrqueue,CPUTime,i);
					Collections.sort(rqueue.rqueue, new Process.ProcessComparator());
					i++;
				}else{
					if(runtime != j){
						CPUTime = round.round(CPUTime+.1);
						runtime = round.round(runtime+.1);
					}
					if(runtime ==j && rqueue.size() > 0){
						System.out.println("This " + runtime);
						endtime = CPUTime;
						System.out.println(executing.getPID() + "executed from " + starttime + " to " + endtime +".");
						executing = rqueue.remove(0);
						j = executing.getBtime();
						starttime = CPUTime;
						runtime = 0.0;
						System.out.println(executing.getPID() + " is executing.");
						CPUTime = round.round(CPUTime+.1);
					}

				}
			}
			for(i=0;i<rqueue.size();i++)
				System.out.println(rqueue.get(i).getPID());
			System.out.println("Done.");
	}
}

/*class Dispatcher extends Thread {
	LinkedList<Process> rqueue = new LinkedList<Process>();
	Process executing = new Process();

	public Dispatcher (LinkedList<Process> rqueue, Process executing){
		this.rqueue = rqueue;
		this.executing = executing;
	}

	public Dispatcher () {
		//default constructor
	}

	public void preempt(LinkedList<Process> rqueue, Process executing){
		Process temp = executing;
		executing = rqueue.remove(0);
		rqueue.add(temp);
	}

	public Process dispatch(LinkedList<Process> rqueue){
		return rqueue.remove(0);
	}

	@Override
	public void run () {
		preempt(rqueue, executing);
	}
}*/

@SuppressWarnings({"unchecked", "rawtypes"})
class Process implements Comparable {
	private int Atime, Btime;
	private String pid;
    public Process (int Atime, int Btime, String pid) {
		this.Atime=Atime;
		this.Btime=Btime;
		this.pid = pid;
	}

	public Process (){
		// default constructor
	}

	@Override
	public int compareTo(Object o){
		if(!(o instanceof Process))
			throw new ClassCastException();
		Process P1 = (Process) o;
		String str1 = String.valueOf(getBtime());
		String str2 = String.valueOf(P1.getBtime());
		return str1.compareTo(str2);
	}

	static class ProcessComparator implements Comparator {
		public int compare(Object o1, Object o2){
			if (!(o1 instanceof Process) || !(o2 instanceof Process))
				throw new ClassCastException();
			Process P1 = (Process) o1;
			Process P2 = (Process) o2;
			return P1.Btime - P2.Btime;
		}
	}

	public int getAtime(){
		return Atime;
	}

	public int getBtime(){
		return Btime;
	}

	public String getPID(){
		return pid;
	}
}
class round {
	public static double round(double val){
		DecimalFormat  df = new DecimalFormat ("#.#");
		val = Double.valueOf(df.format(val));
		return val;
	}
}

//user enters number of Process(numOfProcesses, Arrival Time(Atime), and Burst
//time(Btime) on keyboard.
class getProcesses {
	public static void getProcesses(LinkedList<Process> rqueue, LinkedList<Process> nrqueue){
		int Atime, Btime ,numOfProcesses = 0, i = 0;
        Scanner sc = new Scanner (System.in);
		System.out.println("Please input number of Processes.");
		numOfProcesses = sc.nextInt();

		while (i < numOfProcesses){
			System.out.println("Please input Arrival Time:");
			Atime = sc.nextInt();
			System.out.println("Please input Burst Time:");
			Btime = sc.nextInt();
			Process task = new Process (Atime, Btime, "P"+(i+1));
			if (task.getAtime() == 0){
				rqueue.add(task);
			}
			else{
				nrqueue.add(task);
			}
			i++;
		}
	}
}

