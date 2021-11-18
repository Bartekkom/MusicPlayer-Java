import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {
	String[] list = {"1_Devil.wav","2_Wodospady.wav","3_Dwutakt.wav"};
	String response = "";
	int choice = 0;
	String filename;
	Clip clip;
	File file;
	Scanner scanner = new Scanner(System.in);
	Random rand = new Random();
	
	String SongList() {
		
		System.out.println("Choose your song(enter integer from 1 to 3): ");
		
		for (String i : list) {
			System.out.println(i);
		}
		choice = scanner.nextInt();
		if(choice>3 || choice<1) {
			System.out.println("Invalid entry, random track will be choosen");
			choice = rand.nextInt(3) + 1;
		}
		return list[choice - 1];
		
	}
	void chooseSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		//this.filename = scanner.next();
		this.file = new File(SongList());
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		this.clip = AudioSystem.getClip();
		clip.open(audioStream);
	}
	
	void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(clip == null) {
			chooseSong();
		}
		while(!response.equals("Q")) {
			System.out.println("P = play, S = Stop, C = Song select, R = Reset, Q = Quit");
			System.out.println("Enter your choice: ");
			
			response = scanner.next();
			response = response.toUpperCase();
			
			
			switch(response) {
				case("P"): clip.start();
				break;
				case("S"): clip.stop();
				break;
				case("R"): clip.setMicrosecondPosition(0);
				break;
				case("C"): chooseSong();
				break;
				case("Q"): clip.close();
				break;
				default: System.out.println("Not a valid response");
				
			}
		}
	}

}
