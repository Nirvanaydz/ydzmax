import java.util.Scanner;

import pojo.CustomerUser;
import pojo.NetCard;
import pojo.SingleCard;
import pojo.SuperCard;
import pojo.User;
import methodsList.MethodsList;

public class SosoTest {
	
	MethodsList methodsList=new MethodsList();
	Scanner input=new Scanner(System.in);
	private Object String;
	public static void main(String[] args) throws Exception {
		SosoTest s=new SosoTest();
		s.start();
	}
	private void start() throws Exception {
		int menuChoose=0;
		do{
			System.out.println("******欢迎使用嗖嗖移动业务*******");  
			System.out.println("1.注册新用户");
			System.out.println("2.登录嗖嗖");
			System.out.println("3.资费说明");
			System.out.println("4.退出系统");
			System.out.println("请选择序号:");
			menuChoose=input.nextInt();
			
			switch(menuChoose){
			case 1://注册新用户功能
				boolean flag=true;
				User newUser=null;//创建user接收产生的新用户
				do{
					System.out.println("用户注册：");
					System.out.println("1、管理员    2、嗖嗖移动用户"+"\n请输入您的选择：");
					int chooseCreate=input.nextInt();
					if(chooseCreate==1){
						newUser=methodsList.createAdminUser();//创建一个管理员实例对象
						System.out.println("新管理员注册成功，信息如下");
						System.out.println("用户名："+newUser.getName()+"\t\t密码："+newUser.getPassword());
						methodsList.addUser(newUser);//存储至"用户列表.txt"
						flag=false;
					}else if(chooseCreate==2){
						newUser=methodsList.createCustomerUser();//创建了一个用户实例
						CustomerUser ctu=(CustomerUser)newUser;
						if(newUser instanceof SingleCard){
							System.out.println("注册了话唠卡，信息如下：");
							ctu.info();
						}else if(newUser instanceof NetCard){
							System.out.println("注册了网虫卡，信息如下：");
							ctu.info();
//							methodsList.addUser((pojo.NetCard)ctu);
						}else if(newUser instanceof SuperCard){
							System.out.println("注册了超人卡，信息如下：");
							ctu.info();
//							methodsList.addUser((pojo.SuperCard)ctu);
						}
						methodsList.addUser(newUser);//存储至"用户列表.txt"
						flag=false;
					}
				}while(flag);
				continue;
			case 2:
				System.out.println("请输入用户名");
				String name=input.next();
				System.out.println("请输入密码");
				String password=input.next();
				if(methodsList.login(name,password)){
					System.out.println("登录成功");
					methodsList.loginIn(name,password);
				}else{
					System.out.print("登录失败，原因为：");
					if(methodsList.judgeExist(name)){
						System.out.println("密码输入错误");
					}else{
						System.out.println("用户名还未注册，请先注册！");
					}
				}continue;
			case 3:methodsList.printPrice("套餐资费说明.txt");//资费说明
			case 4:
			default:
			}
		}while(menuChoose!=4);
		
	}

}
