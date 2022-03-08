package nachos.proj1;

import java.util.Vector;

import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.OpenFile;
import nachos.machine.Timer;
import nachos.threads.KThread;

public class MainSystem {

	private FileSystem file = Machine.stubFileSystem();
	private MyConsole console = new MyConsole();
	private Vector<Cookie> cookieList = new Vector<Cookie>();
	
	public void addCookie() {
		String name, size;
		Integer price;
		
		do {
			console.write("Input Cookie Name [must ends with 'Cookie']: ");
			name = console.read();
		}while(!name.endsWith("Cookie"));
		
		do {
			console.write("Input Cookie Size [Small | Medium | Large] (case sensitive): ");
			size = console.read();
		}while(!size.equals("Small") && !size.equals("Medium") && !size.equals("Large"));
		
		do {
			console.write("Input Cookie Price [20000 - 100000]: ");
			price = console.readInt();
		}while(price < 20000 || price > 100000);
		
		cookieList.add(new Cookie(name, size, price));
		
		String format = "";
		for(int a = 0; a < cookieList.size(); a++) {
			format += cookieList.get(a).getName() + "#" + cookieList.get(a).getSize() + "#" + cookieList.get(a).getPrice() + "\n";
		}
		
		OpenFile ofile = file.open("CookieList.txt", true);
		byte[] messageBytes = format.getBytes();
		ofile.write(messageBytes, 0, messageBytes.length);
		
		console.writeLn("New cookie has been added successfully!");
		console.writeLn("Press enter to continue...");
		console.read();
	}
	
	public void removeCookie() {
		if(cookieList.isEmpty()) {
			console.writeLn("There are no cookies yet!");
		}else {
			console.writeLn("Your Cookies:");

			for(int a = 0; a < cookieList.size(); a++) {
				console.writeLn("===================");
				console.writeLn("No. " + (a+1) + " - " + cookieList.get(a).getName());
				console.writeLn("===================");
				console.writeLn("Size  : " + cookieList.get(a).getSize());
				console.writeLn("Price : " + cookieList.get(a).getPrice());
				console.writeLn("");
			}
			
			int index = 0;
			do {
				console.write("Input Cookie's index to remove [1 - " + cookieList.size()  + "]: ");
				index = console.readInt();
			}while(index < 1 || index > cookieList.size());
			
			cookieList.remove(index-1);
			String format = "";
			for(int a = 0; a < cookieList.size(); a++) {
				format += cookieList.get(a).getName() + "#" + cookieList.get(a).getSize() + "#" + cookieList.get(a).getPrice() + "\n";
			}
			OpenFile ofile = file.open("CookieList.txt", true);
			byte[] messageBytes = format.getBytes();
			ofile.write(messageBytes, 0, messageBytes.length);
		
			console.writeLn("Cookie removed successfully!");
			console.writeLn("Press enter to continue...");
			console.read();
			
			if(cookieList.isEmpty()) {
				console.writeLn("There are no cookies yet!");
			}else {
				console.writeLn("Your Cookies:");
				for(int a = 0; a < cookieList.size(); a++) {
					console.writeLn("===================");
					console.writeLn("No. " + (a+1) + " - " + cookieList.get(a).getName());
					console.writeLn("===================");
					console.writeLn("Size  : " + cookieList.get(a).getSize());
					console.writeLn("Price : " + cookieList.get(a).getPrice());
					console.writeLn("");
				}
			}
		}
		console.writeLn("Press enter to continue...");
		console.read();

	}
	
	public void viewAllCookie() {
		if(cookieList.isEmpty()) {
			console.writeLn("There are no cookies to display!");
		}else {
			console.writeLn("Your Cookie:");
			int index = 1;
			while(!cookieList.isEmpty()) {
				console.writeLn("===================");
				console.write("No. " + index);
				index++;
				new KThread(cookieList.remove(0)).fork();
			}
			readFile();
		}
		
		console.writeLn("Press enter to continue...");
		console.read();
	}
	
	public void readFile() {
		String format = "";
		String name, size;
		Integer price;
		
		OpenFile ofile = file.open("CookieList.txt", false);
		if(ofile != null) {
			byte[] messageBytes = new byte[ofile.length()];
			ofile.read(messageBytes, 0, messageBytes.length);
			format = new String(messageBytes);
			String[] arr = format.split("\n");
			for(int a = 0; a < arr.length; a++) {
				String[] arr2 = arr[a].split("#");
				cookieList.add(new Cookie(arr2[0], arr2[1], Integer.valueOf(arr2[2])));
			}
		}
	}
	
	public MainSystem() {
		// TODO Auto-generated constructor stub
		
		Timer timer = Machine.timer();
		Runnable handler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		};
		timer.setInterruptHandler(handler);
		
		Integer choose = 0;
		readFile();
		
		do {
			console.cls();
			console.writeLn("+====================+");
			console.writeLn("|     FAmouz amoz    |");
			console.writeLn("+====================+");
			console.writeLn("|1. Add Cookie       |");
			console.writeLn("|2. Remove Cookie    |");
			console.writeLn("|3. View All Cookie  |");
			console.writeLn("|4. Exit             |");
			console.writeLn("+====================+");
			console.write(">> ");
			choose = console.readInt();
			
			switch (choose) {
			case 1:
				addCookie();
				break;
				
			case 2:
				removeCookie();
				break;
				
			case 3:
				viewAllCookie();
				break;
			
			case 4:
				console.writeLn("Tick of time: " + timer.getTime());
				return;
			}
		}while(choose != 4);
	}

}
