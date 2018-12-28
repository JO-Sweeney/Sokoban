package GridTest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveableObject {
	
	protected String mImagePath;
	protected ImageView mImageView;
	private boolean mIsDiamond = true;			//Diamond is just a special type of floor
	
	public MoveableObject(){
	}
	
	//Sets the Image using filepath and returns it (to run the function and place its resulting image straight into mImageView)
		public Image setImage() {
			Image img = new Image(getClass().getResource(mImagePath).toString());
			return img;
		}
		
		public Image setImage(String path) {
			Image img = new Image(getClass().getResource(mImagePath).toString());
			return img;
		}
		
		//Sets the GUI element imageview using image
		public void setImageView(Image img) {
			mImageView = new ImageView(img);
		}
		
		//returns the imageview
		public ImageView getImageView() {
			return mImageView;
		}
		
		public boolean isDiamond() {
			return mIsDiamond;
		}
		
}
