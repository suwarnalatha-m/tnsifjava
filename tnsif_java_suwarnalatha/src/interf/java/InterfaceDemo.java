package interf.java;
interface Shape{
	 
	 //declaration
	void draw();
}

public class InterfaceDemo implements Shape {
	//implementation or explanation
		public void draw() {
			System.out.println("Drawing a circle in an interface");
		}

	public static void main(String[] args) {
		InterfaceDemo id = new InterfaceDemo();
		id.draw();

	}

}
