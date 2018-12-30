package GridTest;

public class Floor extends MoveableObject {
	
	
	public Floor() {
		mImagePath = "Floor.png";
		setImageView(setImage());
	}
	
	public Floor(String path) {
		mImagePath = path;
		setImageView(setImage());
	}
	
}
