package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole {

	private SerialConsole sc = Machine.console();
	private Semaphore sem = new Semaphore(0);
	private Character chr;
	
	public MyConsole() {
		// TODO Auto-generated constructor stub
		
		Runnable receive = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				chr = (char) sc.readByte();
				sem.V();
			}
		};
		Runnable send = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sem.V();
			}
		};
		
		sc.setInterruptHandlers(receive, send);
	}
	
	public void write(String s) {
		for(int i = 0; i < s.length(); i++) {
			sc.writeByte(s.charAt(i));
			sem.P();
		}
	}
	
	public void writeLn(String s) {
		write(s + "\n");
	}
	
	public void cls() {
		for(int i = 0; i < 20; i++) {
			writeLn("");
		}
	}
	
	public String read() {
		String s = "";
		
		do {
			sem.P();
			if(chr == '\n') break;
			s += chr;
		}while(true);
		
		return s;
	}

	public Integer readInt() {
		String s = read();
		Integer i = 0;
		
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
		
		return i;
	}

}
