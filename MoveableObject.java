package GridTest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableObject {
	
	protected String mImagePath;
	protected ImageView mImageView;
	
	public MoveableObject(){
	}
	
	//Sets the Image using filepath and returns it (to run the function and place its resulting image straight into mImageView)
		public Image setImage() {
			Image img = new Image(getClass().getResource(mImagePath).toString());
			return img;
		}
		
		//Sets the GUI element imageview using image - used in constructor because it seperates out components of setting imageview
		public void setImageView(Image img) {
			mImageView = new ImageView(img);
		}
		
		//Sets the imageview - used when a crate is either placed on a diamond or coming off a diamond
		public void setImageView() {
			Image img = new Image(getClass().getResource(mImagePath).toString());
			mImageView = new ImageView(img);
		}
		
		public void setPath(String path) {
			mImagePath = path;
		}
		
		//returns the imageview
		public ImageView getImageView() {
			return mImageView;
		}
		
}
