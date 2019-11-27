import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import pojo.AdminUser;
import pojo.NetCard;
import pojo.SingleCard;
import pojo.SuperCard;
import pojo.User;

public class TestObject {

	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream(new File("用户列表.txt"));
		 ObjectInputStream ois=new ObjectInputStream(fis);
		 ArrayList<User> userList01=new ArrayList<User>();
		 while(fis.available()>0){
			 User user=(User) ois.readObject();
			 if(user instanceof AdminUser){
				 userList01.add((AdminUser)user);
			 }else if(user instanceof NetCard){
				 userList01.add((NetCard)user);
			 }else if(user instanceof SingleCard){
				 userList01.add((SingleCard)user);
			 }else if(user instanceof SuperCard){
				 userList01.add((SuperCard)user);
			 }
		 }
		 for(int i=0;i<userList01.size();i++){
			 System.out.println(userList01.get(i).getName());
		 }
	}
}
