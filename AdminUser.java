package pojo;

public class AdminUser extends User{

	//constructor
	public AdminUser(){}
	
	public AdminUser(String name, String password) {
		super(name, password);
	}

	@Override//for User
	public void info() {
		System.out.println("����Ա��Ϣ����");
		System.out.println("�˺ţ�"+super.getName()+"\n���룺"+super.getPassword());
	}

}
