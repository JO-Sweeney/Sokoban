package GridTest;

public class Floor extends MapObject {
	
	
	public Floor() {
		mImagePath = "Floor.png";
		setImageView(setImage());
	}
	
	public Floor(String path) {
		mImagePath = path;
		setImageView(setImage());
	}
	
}
