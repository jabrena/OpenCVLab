package javacv.test;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

import org.bytedeco.javacpp.opencv_core.IplImage;

public class Smoother {

	public static void main(String[] args) {

		String filename="res/rubik.jpg";
		String filename2="res/rubik2.jpg";
		IplImage image = cvLoadImage(filename);
        if (image != null) {
            cvSmooth(image, image);
            cvSaveImage(filename2, image);
            cvReleaseImage(image);
        }
		
	}

}
