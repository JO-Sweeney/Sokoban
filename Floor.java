package GridTest;

public class Floor extends MoveableObject {
	
	private boolean mIsDiamond = true;			//Diamond is just a special type of floor
	
	public Floor() {
		mImagePath = "Floor.png";
		setImageView(setImage());
	}
	
	public Floor(String path) {
		mImagePath = path;
		setImageView(setImage());
	}
	
	public void unsetDiamond() {
		mIsDiamond = false;
	}
	
	public void setDiamond() {
		mIsDiamond = true;
	}
	
	public boolean isDiamond() {
		return mIsDiamond;
	}


}
