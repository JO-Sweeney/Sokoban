package GridTest;

public class Diamond extends MoveableObject {

	private boolean mHasCrate;
	
	public Diamond() {
		mHasCrate = false;
		mImagePath = "Diamond.png";
		setImageView(setImage());
	}
	
	public void setHasCrate(){
		mHasCrate = true;
	}
	
	public void setNoCrate() {
		mHasCrate = false;
	}
	
	public boolean getHasCrate() {
		return mHasCrate;
	}
	
}
