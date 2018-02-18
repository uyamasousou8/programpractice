class Car
{
	int num;
	double gas;
	void show()
	{
		System.out.println("車のナンバーは" + this.num + "です");
		System.out.println("ガソリン量は" + this.gas + "です");
	}
	void showCar()
	{
		System.out.println("これから車の情報を表示します");
		this.show();
	}
}

public class practice5 {
	public static void main(String args[])
	{
		Car car1;
		car1 = new Car();
		car1.num = 1234;
		car1.gas = 20.5;
		
		car1.showCar();
	}
}
