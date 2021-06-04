package io.github.Santeeisweird9;

public class LoopThread extends Thread {
	String url = Main.url;
	int port = Main.port;
	@Override
	public void run() {
		while (true) {
			Main.pingHost(url, port, 5000);
		}
	}
}
