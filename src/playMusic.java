
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Dax Amburgy
 *
 */
public class playMusic {

	File musicSource;
	Clip clip;
	/**
	 * @return the musicSource
	 */
	public File getMusicSource() {
		return musicSource;
	}

	/**
	 * @param musicSource the musicSource to set
	 */
	public void setMusicSource(String musicSource) {
		File source = new File(musicSource);
		this.musicSource = source;
	}
	/**
	 * a method to start playing music
	 * @param source - the file of music we want to play. MUST BE .WAV
	 */
	public void startMusic(File source) {
		
		
		try {
			//set a file to the source
			File musicPath = source;
			
			//determine if the file was successfully added
			if(musicPath.exists()) {
			
				//get an audio input stream from the file
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			
			//read the data from the audio stream
			clip = AudioSystem.getClip();
			
			//open the audio stream
			clip.open(audioInput);
			//start the music
			clip.start();
			//loop the music indefinetly
			clip.loop(clip.LOOP_CONTINUOUSLY);
			
			//generate a stop point in the music
			long clipTimePosition = clip.getMicrosecondPosition();
			
			
			}
			else {
				System.out.println("No music file found");
			}
		}
		catch(Exception e){
			JOptionPane.showInputDialog("No music file found");
		}
		
	}
	
	/**
	 * a method to stop playing music
	 */
	public void stopMusic() {
		clip.stop();
		clip.close();
	}
	
}
