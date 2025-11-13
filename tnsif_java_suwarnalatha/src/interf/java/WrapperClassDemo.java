package interf.java;

public class WrapperClassDemo {

	public static void main(String[] args) {
		         //autoboxing - primitive to object
				//unboxing - object to primitive
				
				int x = 9;
				//autoboxing
				Integer y = Integer.valueOf(x);
				
				//unboxing 
				
				int z = y;
				
				System.out.println(y +" "+ z);
	}

}
