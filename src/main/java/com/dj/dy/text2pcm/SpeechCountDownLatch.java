package com.dj.dy.text2pcm;
import java.util.concurrent.CountDownLatch;


public class SpeechCountDownLatch {
	
	public static CountDownLatch latch;
	
	public static void setLatch(int num)
	{
		latch = new CountDownLatch(num);
	}
	
	public static CountDownLatch getCountDownLatch()
	{
		return latch;
	}
	public static void CountDown()
	{
		latch.countDown();
	}
}
