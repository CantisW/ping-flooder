package io.github.Santeeisweird9;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
//import java.net.InetAddress;
import java.util.Scanner;

public class Main {

	static Scanner input = new Scanner(System.in);
	static String url;
	static int port = 80;
	static int threads;

	public static void selection() {
		System.out.println("Input an IP (properly formatted!):");
		url = input.nextLine();
		System.out.println("Port (blank for default of 80):");
		String i = input.nextLine();
		if (i.contentEquals("")) {
			port = 80;
		} else {
			port = Integer.valueOf(i);
		}
		System.out.println("IP: "+url+" at Port "+port);
		confirm();
	}

	public static void confirm() {
		char selection;
		System.out.println("Are you sure? (Y/N)");
		selection = input.next().charAt(0);
		Character.toLowerCase(selection);
		if (selection == 'y') {
			System.out.println("How many instances?");
			try {
				threads = input.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Checking if host is reachable before continuing...");
			if (pingHost(url, port, 5000)) {
				for(int i = threads;i>0;i--) {
					createThread();
				}
			} else {
				System.out.println("Restart program.");
			}
			
		} else if (selection == 'n') {
			//System.out.println("Press enter.");
			input.nextLine();
			selection();
		} else {
			System.out.println("Invalid character!");
			confirm();
		}
	}

	public static boolean pingHost(String host, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(host, port), timeout);
			System.out.println("Reached host!");
			return true;
		} catch (IOException e) {
			System.out.println("Unreachable!");
			return false;
		}
	}
	
	private static void createThread() {
		LoopThread t = new LoopThread();
		t.start();
	}

	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println(
				"[Ping Flooder]: DO NOT USE THIS PROGRAM MALICIOUSLY. YOU MAY USE IT TO STRESS TEST YOUR OWN WEBSITE!");
		System.out.println("To get an IP from a site in Windows: ping the site via Command Prompt");
		System.out.println(
				"We are not responsible for anything you do maliciously. If you agree to this, you may proceed.");
		System.out.println("DO NOT DISTRIBUTE THIS PROGRAM WITHOUT PERMISSION!");
		System.out.println("-------------------------------------------------------------------------------------------------");

		System.out.println();
		selection();
	}

}
