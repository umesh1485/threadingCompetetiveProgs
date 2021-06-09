package com;

public class DeadLock {

	public static void main(String[] args) throws InterruptedException {
		String resourc1 = "resource1";
		String resourc2 = "resource2";
		try {
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					synchronized (resourc1) {
						System.out.println("resourc1 is locked by t1");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (resourc2) {
							System.out.println("resourc2 is locked by t1");
						}
					}
				}
			});

			Thread t2 = new Thread(new Runnable() {
				public void run() {
					synchronized (resourc2) {
						System.out.println("resourc2 is locked by t2");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (resourc1) {
							System.out.println("resourc1 is locked by t2");
						}
					}
				}
			});

			t1.start();
			t2.start();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
