package com.dj.dy.ffmepg;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.dj.dy.pcmtowav.PcmToWav;
import com.dj.dy.pg.AnimatedGifEncoder;
import com.dj.dy.pg.WaterMarkUtils;
import com.dj.dy.text2pcm.SpeechCountDownLatch;
import com.dj.dy.text2pcm.TtsSpeechView;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.util.Version;


public class DJShow {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String id = UUID.randomUUID().toString();
		String originalImagePath = "E:\\Video\\pic";
		String imagePath = "E:\\Video\\pict";
		String pcmPath = "E:\\Video\\pcm";
		String soundPath = "E:\\Video\\sound";
		String singleVideoPath = "E:\\Video\\single";
		String mergeVideoPath = "E:\\Video\\merge";
		String ffmpegPath = "C:\\ffmpeg\\ffmpeg-20141225-git-1515bfb-win64-shared\\bin";
		
//		File originalImageDir = new File(originalImagePath,id);
		File originalImageDir = new File(originalImagePath);
		File imagePathDir = new File(imagePath,id);
		File soundPathDir = new File(soundPath,id);
		File singleVideoDir = new File(singleVideoPath,id);
		File mergeVideoDir = new File(mergeVideoPath,id);
		
		if(!imagePathDir.exists())
		{
			imagePathDir.mkdirs();
		}
		if(!soundPathDir.exists())
		{
			soundPathDir.mkdirs();
		}
		
		if(!singleVideoDir.exists())
		{
			singleVideoDir.mkdirs();
		}
		
		if(!mergeVideoDir.exists())
		{
			mergeVideoDir.mkdirs();
		}
		
		File[] fs = originalImageDir.listFiles();
		String pic[] = new String[fs.length];
		String text[] =new String[fs.length];
		
		PcmToWav pcmToWav = new PcmToWav();
		text[0]="你知道你为什么找不到女朋友吗";
		text[1]="不知道吧";
		text[2]="因为你太丑了";
		SpeechUtility.createUtility("appid=" + Version.getAppid());

		TtsSpeechView ttsSpeechView= new  TtsSpeechView();
		for(int i=0; i<fs.length; i++)
		{
			String newPath = imagePathDir.getAbsolutePath() + File.separator+ fs[i].getName();
			new WaterMarkUtils().mark(fs[i].getAbsolutePath(), newPath, Color.WHITE, text[i]);  
			pic[i] = newPath;
			try {
				SpeechCountDownLatch.setLatch(1);
				ttsSpeechView.getTtsSpeech(text[i], pcmPath + File.separator + id + File.separator + i + ".pcm");
				SpeechCountDownLatch.getCountDownLatch().await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SpeechCountDownLatch.getCountDownLatch().await();
		for(int i=0; i<fs.length; i++)
		{
			pcmToWav.convertAudioFiles(pcmPath + File.separator + id + File.separator + i + ".pcm", soundPath + File.separator + id + File.separator + i + ".wav");
		}
	
		ConvertToVideo convertToVideo = new ConvertToVideo();
		convertToVideo.setFfmpegPath(ffmpegPath);
		convertToVideo.setId(id);
		convertToVideo.setImagePath(imagePath);
		convertToVideo.setMediaLength(fs.length);
		convertToVideo.setMergeVideoPath(mergeVideoPath);
		convertToVideo.setSingleVideoPath(singleVideoPath);
		convertToVideo.setSoundPath(soundPath);
		convertToVideo.doConvert();
	}
	
	public static void convert(String id, String originalImagePath,
			String imagePath, String pcmPath, String soundPath,
			String singleVideoPath, String mergeVideoPath, String ffmpegPath, String[] speechText)
			throws Exception {
		
//		File originalImageDir = new File(originalImagePath,id);
		//just for prestation
		File originalImageDir = new File(originalImagePath);
		File imagePathDir = new File(imagePath,id);
		File soundPathDir = new File(soundPath,id);
		File singleVideoDir = new File(singleVideoPath,id);
		File mergeVideoDir = new File(mergeVideoPath,id);
		
		if(!imagePathDir.exists())
		{
			imagePathDir.mkdirs();
		}
		if(!soundPathDir.exists())
		{
			soundPathDir.mkdirs();
		}
		
		if(!singleVideoDir.exists())
		{
			singleVideoDir.mkdirs();
		}
		
		if(!mergeVideoDir.exists())
		{
			mergeVideoDir.mkdirs();
		}
		
		File[] fs = originalImageDir.listFiles();
		String pic[] = new String[fs.length];
		String text[] =new String[fs.length];
		
		PcmToWav pcmToWav = new PcmToWav();
		text[0]=speechText[0];
		text[1]=speechText[1];
		text[2]=speechText[2];
		SpeechUtility.createUtility("appid=" + Version.getAppid());

		TtsSpeechView ttsSpeechView= new  TtsSpeechView();
		for(int i=0; i<fs.length; i++)
		{
			String newPath = imagePathDir.getAbsolutePath() + File.separator+ fs[i].getName();
			
			//给图片添加文字
			new WaterMarkUtils().mark(fs[i].getAbsolutePath(), newPath, Color.WHITE, text[i]);  
			pic[i] = newPath;
			try {
				SpeechCountDownLatch.setLatch(1);
				//获取文字语音
				ttsSpeechView.getTtsSpeech(text[i], pcmPath + File.separator + id + File.separator + i + ".pcm");
				SpeechCountDownLatch.getCountDownLatch().await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SpeechCountDownLatch.getCountDownLatch().await();
		for(int i=0; i<fs.length; i++)
		{
			pcmToWav.convertAudioFiles(pcmPath + File.separator + id + File.separator + i + ".pcm", soundPath + File.separator + id + File.separator + i + ".wav");
		}
	
		ConvertToVideo convertToVideo = new ConvertToVideo();
		convertToVideo.setFfmpegPath(ffmpegPath);
		convertToVideo.setId(id);
		convertToVideo.setImagePath(imagePath);
		convertToVideo.setMediaLength(fs.length);
		convertToVideo.setMergeVideoPath(mergeVideoPath);
		convertToVideo.setSingleVideoPath(singleVideoPath);
		convertToVideo.setSoundPath(soundPath);
		convertToVideo.doConvert();
	}

	public static void convertToGif() throws IOException
	{
		 BufferedImage src1 = ImageIO.read(new File("Img221785570.jpg"));
	     BufferedImage src2 = ImageIO.read(new File("W.gif"));
	        //BufferedImage src3 = ImageIO.read(new File("c:/ship3.jpg")); 
	     AnimatedGifEncoder e = new AnimatedGifEncoder(); 
	        e.setRepeat(0); 
	        e.start("laoma.gif"); 
	        e.setDelay(300); // 1 frame per sec 
	        e.addFrame(src1); 
	        e.setDelay(100); 
	        e.addFrame(src2); 
	        e.setDelay(100); 
	    //  e.addFrame(src2); 
	        e.finish();
	}
}

