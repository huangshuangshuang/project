import java.util.Scanner;

public class TestJava{
	public static void main(String[] args){
		TestJava java=new TestJava();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please inter a int type i:");
		int i=scanner.nextInt();
		System.out.println(java.isANeedNumber(i));
	}


	private boolean isANeedNumber(int i){
		if (i<=0){
			return false;
		}
		return (i&-i)==i;
	}
}
