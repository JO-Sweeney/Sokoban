package GridTest;


public class Crate extends MoveableObject {

	private boolean mOnDiamond;
	
	public Crate() {
		mOnDiamond = false;
		mImagePath = "Crate.png";
		setImageView(setImage());
	}
	
	public void setPath(String newPath) {
		mImagePath = newPath;
	}
	
	public void onDiamond() {
		mOnDiamond = true;
	}
	
	public void offDiamond() {
		mOnDiamond = false;
	}
	
	
	
}
