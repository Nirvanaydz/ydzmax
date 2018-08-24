package methodsList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import pojo.AdminUser;
import pojo.CustomerUser;
import pojo.NetCard;
import pojo.SingleCard;
import pojo.SuperCard;
import pojo.User;

public class Test01 {

	@Test
	public void test01() throws Exception {
		ArrayList<User>  userList01=new ArrayList<User>();
		FileOutputStream fis=new FileOutputStream(new File("用户列表.txt"));
		ObjectOutputStream ois=new ObjectOutputStream(fis);
		User u1=new AdminUser("ad01", "111111");
		User u2=new AdminUser("ad02", "222222");
		User u3=new AdminUser("ad03", "333333");
		User u4=new SuperCard("user01", "123456", "13967918731", 68, 100, 1024, 1000, 100);
		userList01.add(u4);
		userList01.add(u3);
		userList01.add(u2);
		userList01.add(u1);
		ois.writeObject(userList01);
		ois.close();
		fis.close();
	}
	
	
	@Test
	public void test03() throws Exception {
		
		 ArrayList<User>  userList01=new ArrayList<User>();
		 FileInputStream fis=new FileInputStream(new File("用户列表.txt"));
		 ObjectInputStream ois=new ObjectInputStream(fis);
		 userList01=(ArrayList<User>) ois.readObject();
		 ois.close();
		 fis.close();
		 for(int i=0;i<userList01.size();i++){
			 System.out.println(userList01.get(i).getName());
		 }
		 ois.close();
		 fis.close();
	}

}
