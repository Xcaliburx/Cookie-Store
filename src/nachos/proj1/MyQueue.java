package nachos.proj1;

import java.util.Vector;

import nachos.threads.KThread;
import nachos.threads.ThreadQueue;

public class MyQueue extends ThreadQueue {

	private Vector<KThread> threadList = new Vector<>();
	
	public MyQueue() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void waitForAccess(KThread thread) {
		// TODO Auto-generated method stub
		threadList.add(thread);
	}

	@Override
	public KThread nextThread() {
		// TODO Auto-generated method stub
		if(threadList.isEmpty()) return null;
		
		return threadList.get(0);
	}

	@Override
	public void acquire(KThread thread) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
