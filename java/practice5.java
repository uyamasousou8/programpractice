class Car
{
	int num;
	double gas;
	void show()
	{
		System.out.println("�Ԃ̃i���o�[��" + this.num + "�ł�");
		System.out.println("�K�\�����ʂ�" + this.gas + "�ł�");
	}
	void showCar()
	{
		System.out.println("���ꂩ��Ԃ̏���\�����܂�");
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
