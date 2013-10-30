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
public class Scheduler implements Runnable {

	public static void arriving(LinkedList<Process> rqueue,
		LinkedList<Process> nrqueue, double CPUTime, double Atime){

		int i = 0;
		if(CPUTime == Atime){
			while(nrqueue.size() > 0){
				if(nrqueue.get(i).getAtime() != Atime+1){
				rqueue.add(nrqueue.remove());

				}else{
					break;
				}
			}
		}
	}

	public static void executing(Process executing){
        double j = executing.getBtime();
		double CPUTime = 0.0, runtime = 0.0, starttime = 0.0, endtime = 0.0, time_remaining = 0.0;
		int i = 1;


	}

	@Override
	public void run () {
		//todo
	}

	public void sortQueue()



	public static void main (String[] args){
		int Wtime,Tat,Twt;
		double CPUTime;
		int NumOfProcesses = 0, i = 0;
        LinkedList<Process> rqueue = new LinkedList<Process> ();
		LinkedList<Process> nrqueue = new LinkedList<Process> ();
        getProcesses.getProcesses(rqueue,nrqueue);
		System.out.println(NumOfProcesses);

			/*
			 * Time to start executing some processes.
			 */
			CPUTime = 0.0;
            Dispatcher dispatch = new Dispatcher();
			Process executing = dispatch.dispatch(rqueue);
		   /* double runtime = 0.0, starttime = CPUTime, endtime = 0.0, time_remaining = 0.0;
			i = 1;
		    Process executing = rqueue.remove(0);
			double j = executing.getBtime();
			boolean notdone = true;
		    while (notdone){
				if (runtime != j){
					if(CPUTime == nrqueue.get(0).getAtime()){
						arriving(rqueue, nrqueue, CPUTime, i);
						Collections.sort(rqueue, new Process.ProcessComparator());
						if(rqueue.size() > 0){
						if(rqueue.get(0).getBtime() < time_remaining){
								endtime = CPUTime;
								System.out.println("Process " + executing.getPID() + " executed from " + starttime + " to " + endtime + ".");
								notdone = true;
								Process temp = executing;
								executing = rqueue.remove(0);
								rqueue.add(temp);
								j = executing.getBtime();
								starttime = CPUTime;
                                runtime = 0.0;
								Collections.sort(rqueue, new Process.ProcessComparator());
						}
						}
						i++;
						System.out.println("Value of i: " + i);
					}
					if(runtime == 0){
					System.out.println("Process " + executing.getPID() + " is executing.");
					}
					CPUTime = round.round(CPUTime+.1);
					runtime = round.round(runtime+.1);
					time_remaining = executing.getBtime() - runtime;
					}else{
						if(rqueue.size() > 0){
							Collections.sort(rqueue, new Process.ProcessComparator());
							endtime = CPUTime;
							System.out.println("Process " + executing.getPID() + " executed from " + starttime + " to " + endtime + ".");
                            notdone = true;
							executing = rqueue.remove(0);
							j = executing.getBtime();
							starttime = CPUTime;
							runtime = 0.0;
						}else{
						endtime = CPUTime;
						System.out.println("Process " + executing.getPID() + " executed from " + starttime + " to " + endtime + ".");
					notdone = false;
						}
					}
				}*/
			dispatch = new Dispatcher(rqueue, executing);
			Thread t = new Thread (dispatch);
			t.start();
			for (i=0; i<5; i++)
				System.out.println("Process Done.");
			}
}

class Dispatcher implements Runnable {
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
}

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
