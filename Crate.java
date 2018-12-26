package GridTest;

public class Crate extends MoveableObject {

	public Crate() {
		mImagePath = "Crate.png";
		setImageView(setImage());
	}
	
	public void setPath(String newPath) {
		mImagePath = newPath;
	}
	
	
	
}
