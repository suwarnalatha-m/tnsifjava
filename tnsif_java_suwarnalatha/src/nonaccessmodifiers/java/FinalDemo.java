package nonaccessmodifiers.java;

class FinalDemo {
    int Var = 100;
	
	void display() {
		int Max = 100;
		System.out.println("show the value of max: "+Max);
	}

	public static void main(String[] args) {
		FinalDemo fd = new FinalDemo();
		fd.display();
		fd.Var = 100;

	}

}
class Child extends FinalDemo{
	void display() {
		int Max = 100;
		System.out.println("show the value of max: "+Max);
	}
	
	

}
