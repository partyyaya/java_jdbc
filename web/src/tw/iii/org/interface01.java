package tw.iii.org;

public class interface01 {

	public static void main(String[] args) {
		p1 obj1=new in01();//obj1�Lm2��k,�N����
		p1 obj3=new in02();//obj3�Lm3��k,�N����
		in01 obj2 = (in01)obj1;//�૬��~�|�������k,�]�㦳in01���H
		
		main2(obj3);//�I�s�ɷ|�۰ʥh�եι��骺m1��k�P�ӱM����k
	}
	
	static void main2(p1 an){
		an.m1();
		if(an instanceof in01){
			((in01)an).m2();
		}else if(an instanceof in02){
			((in02)an).m3();
		}
	}
}

interface p1{
	void m1();
}

class in01 implements p1{
	public void m1(){System.out.println("a");};
	public void m2(){System.out.println("a1");};
}

class in02 implements p1{
	public void m1(){System.out.println("b");};
	public void m3(){System.out.println("b1");};
}
