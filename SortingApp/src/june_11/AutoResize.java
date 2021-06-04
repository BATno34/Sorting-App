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
	
	//Constructor
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
	
	/**
	 * finding the new value of x, fitting the new frame size
	 * @return new value of x
	 */
	public int newX() {
		return (int)(frameNewWidth * initialX / initialFrameWidth);
	}
	
	/**
	 * finding new value of y, fitting the new frame size
	 * @return new value of y
	 */
	public int newY() {
		return (int)(frameNewHeight * initialY / initialFrameHeight);
	}
	
	/**
	 * finding new width of the component, fitting the new frame size
	 * @return new width
	 */
	public int newWidth() {
		return (int)(frameNewWidth * initialWidth / initialFrameWidth);
	}
	
	/**
	 * finding new height of the component, fitting the new frame size
	 * @return new height
	 */
	public int newHeight() {
		return (int)(frameNewHeight * initialHeight / initialFrameHeight);
	}
	
	/**
	 * finding new font size, fitting the new frame size
	 * @return new font size
	 */
	public int newFontSize() {
		return (int)(initialFontSize * newWidth() / initialWidth);
	}
	
}//end of the class

