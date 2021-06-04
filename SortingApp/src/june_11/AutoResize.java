package june_11;

public class AutoResize {
	
	public static int initialX;
	public static int initialY;
	public static int initialWidth;
	public static int initialHeight;
	public static int initialFontSize;
	public static int initialFrameWidth;
	public static int initialFrameHeight;
	public static int frameNewWidth;
	public static int frameNewHeight;
	
	public AutoResize (int initialX, int initialY, int initialWidth, int initialHeight, int initialFontSize, int initialFrameWidth, int initialFrameHeight, int frameNewWidth, int frameNewHeight) {
		this.initialX = initialX;
		this.initialY = initialY;
		this.initialWidth =initialWidth;
		this.initialHeight = initialHeight;
		this.initialFontSize = initialFontSize;
		this.initialFrameWidth = initialFrameWidth;
		this.initialFrameHeight = initialFrameHeight;
		this.frameNewWidth = frameNewWidth;
		this.frameNewHeight = frameNewHeight;
	}
	
	public int newX() {
		return (int)(frameNewWidth * initialX / initialFrameWidth);
	}
	
	public int newY() {
		return (int)(frameNewHeight * initialY / initialFrameHeight);
	}
	
	public int newWidth() {
		return (int)(frameNewWidth * initialWidth / initialFrameWidth);
	}
	
	public int newHeight() {
		return (int)(frameNewHeight * initialHeight / initialFrameHeight);
	}
	
	public int newFontSize() {
		return (int)(initialFontSize * newWidth() / initialWidth);
	}
}

