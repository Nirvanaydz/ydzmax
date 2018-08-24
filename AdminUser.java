package pojo;

public class AdminUser extends User{

	//constructor
	public AdminUser(){}
	
	public AdminUser(String name, String password) {
		super(name, password);
	}

	@Override//for User
	public void info() {
		System.out.println("管理员信息如下");
		System.out.println("账号："+super.getName()+"\n密码："+super.getPassword());
	}

}
