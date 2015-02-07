package com.dj.dy.ffmepg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConvertToVideo {
	
	private String id;
	
	private String imagePath;
	
	private String soundPath;
	
	private String singleVideoPath;
	
	private String mergeVideoPath;
	
	private int mediaLength;
	
	private String ffmpegPath;

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setSoundPath(String soundPath) {
		this.soundPath = soundPath;
	}


	public void setMediaLength(int mediaLength) {
		this.mediaLength = mediaLength;
	}

	public void setSingleVideoPath(String singleVideoPath) {
		this.singleVideoPath = singleVideoPath;
	}


	public void setMergeVideoPath(String mergeVideoPath) {
		this.mergeVideoPath = mergeVideoPath;
	}


	public void setFfmpegPath(String ffmpegPath) {
		this.ffmpegPath = ffmpegPath;
	}

	
	public void doConvert( ) throws IOException, InterruptedException
	{
		String cmd= null;
		
		for (int i = 0; i < mediaLength; i++) {
			cmd = new String("cmd.exe /c" +
					"ffmpeg -y -f image2 -i "
					+ imagePath + File.separator + id + File.separator + i + ".jpg -i "
					+ soundPath + File.separator + id + File.separator + i + ".wav -absf aac_adtstoasc "
					+ singleVideoPath + File.separator + id + File.separator + File.separator+ i +".mp4");
			Process process = Runtime.getRuntime().exec(cmd, null, new File(ffmpegPath));
			try {
				StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "Error");  
				StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "Output");  
				errorGobbler.start();  
				outputGobbler.start();  
				process.waitFor();
			}
			catch (Exception e) {
				try
				{
					process.getInputStream().close();
					process.getErrorStream().close();
				}
				catch (IOException ee)
				{
				}
			}
		}
		
		File videoFile = new File(mergeVideoPath+File.separator+id,"listVideo.txt");
		File singleFile = new File(singleVideoPath,id);
		if(!videoFile.exists())
		{
			videoFile.createNewFile();
		}
		
		File[] fs = singleFile.listFiles();
		BufferedWriter output = new BufferedWriter(new FileWriter(videoFile));
		try {
			String path = null;
			for (int i = 0; i < fs.length; i++) {
				path = fs[i].getAbsolutePath();
				path = path.replace("\\", "\\\\"); 
				System.out.println(path);
				output.write("file " + path);
				output.write("\r\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			output.close();
		}

		cmd = new String("cmd.exe /c" +
				"ffmpeg -y -f concat -i "
				+ videoFile.getCanonicalPath() + " -c copy "
				+ mergeVideoPath + File.separator + id + File.separator + id +".mp4");
		System.out.println(cmd);
		Process process1 = Runtime.getRuntime().exec(cmd, null, new File(ffmpegPath));
		try {
			StreamGobbler errorGobbler = new StreamGobbler(process1.getErrorStream(), "Error");  
			StreamGobbler outputGobbler = new StreamGobbler(process1.getInputStream(), "Output");  
			errorGobbler.start();  
			outputGobbler.start();  
			process1.waitFor();
		} 
		catch (Exception e) {
			try
			{
				process1.getInputStream().close();
				process1.getErrorStream().close();
				process1.getOutputStream().close();  
			}
			catch (IOException ee)
			{
			}
		}
	}
	
	public class StreamGobbler extends Thread {  
		  
	    InputStream is;  
	    String type;  
	  
	    public StreamGobbler(InputStream is, String type) {  
	        this.is = is;  
	        this.type = type;  
	    }  
	  
	    public void run() {  
	        try {  
	            InputStreamReader isr = new InputStreamReader(is);  
	            BufferedReader br = new BufferedReader(isr);  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	                if (type.equals("Error")) {  
	                    System.out.println("Error   :" + line);  
	                } else {  
	                    System.out.println("Debug:" + line);  
	                }  
	            }  
	        } catch (IOException ioe) {  
	            ioe.printStackTrace();  
	        }  
	    }  
	}  
}
