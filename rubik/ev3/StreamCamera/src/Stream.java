import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.video.Video;

/**
 * 
 * @author Sylvie91
 * @description: This class has been designed to stream a image 
 * from a webcam plugged in a EV3 brick.
 */
public class Stream {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 120;
    private static final String HOST = "10.0.1.1";
    private static final int PORT = 1234;
 
    public static void main(String[] args) throws IOException  {
 
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        Video video = ev3.getVideo();
        video.open(WIDTH, HEIGHT);  
        byte[] frame = video.createFrame();
        ServerSocket server = new ServerSocket(PORT);
        Socket sock = server.accept();
      //  Socket sock = new Socket(HOST, PORT);
        System.out.println("CONNECTED");
        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
        long start = System.currentTimeMillis();
        int frames = 0;
        LCD.drawString("fps:", 0, 2);
 
        while(Button.ESCAPE.isUp()) {
            try {
                video.grabFrame(frame);
                LCD.drawString("" + (++frames * 1000f/(System.currentTimeMillis() - start)), 5,2);
 
                bos.write(frame);
                bos.flush();
            } catch (IOException e) {
                break;
            }
        }
 
        bos.close();
        sock.close();
        video.close();
    }
}