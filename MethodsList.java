package methodsList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import pojo.AdminUser;
import pojo.CustomerUser;
import pojo.NetCard;
import pojo.SingleCard;
import pojo.SuperCard;
import pojo.User;

public class MethodsList {
	Scanner input=new Scanner(System.in);
	private ArrayList<User>  userList=new ArrayList<User>();
	
	public MethodsList(){}
	 //添加，存储，序列化存储：以集合<User>存储到“用户列表.txt”
	 public void addUser(User user) throws Exception{
		 if(user!=null){
			 for(int i=0;i<userList.size();i++){
				 if(userList.get(i).getName().equals(user.getName())){
					 userList.remove(i);
				 }
			 }
			 this.userList.add(user);
			 FileOutputStream ops=new FileOutputStream(new File("用户列表.txt"));
			 ObjectOutputStream oos=new ObjectOutputStream(ops);
			 oos.writeObject(this.userList);
			 oos.close();
			 ops.close();
		 }else{
			 FileOutputStream ops=new FileOutputStream(new File("用户列表.txt"));
			 ObjectOutputStream oos=new ObjectOutputStream(ops);
			 oos.writeObject(this.userList);
			 oos.close();
			 ops.close();
		 }
	 }
	 //反序列化读取“用户列表.txt”的数据到集合
	 public void readUser(String string) throws Exception{
		 FileInputStream fis=new FileInputStream(new File(string));
		 ObjectInputStream ois=new ObjectInputStream(fis);
		 this.userList=(ArrayList<User>) ois.readObject();
		 ois.close();
		 fis.close();
	 }

	 //判断用户注册时输入的用户或者密码是否有误
	 public String createName(){
		 boolean flag=true;
		 String name=null;
		 do{
			 System.out.println("请输入您的用户名");
			 name=input.next();
			 if(judgeExist(name)){
				 System.out.println("管理员用户已存在，请重新输入");
				 continue;
			 }
			 if(name.length()<=2){
				 System.out.println("请输入长度超过3的字符");
				 continue;
			 }else{
				 flag=false;
			 }
		 }while(flag);
		 return name;
	 }
	 //判断用户注册时输入的密码是否有误
	 public String createPassword(){
		 boolean flag=true;
		 String password=null;
		 do{
			 System.out.println("请输入您的密码");
			 password=input.next();
			 if(password.length()<=5){
				 System.out.println("请输入长度超过6的字符");
				 continue;
			 }else{
				 flag=false;
			 }
		 }while(flag);
		 return password;
	 }
	 
	//注册管理员方法
	public User createAdminUser() throws Exception {
		User user=new AdminUser();
		String name=createName();
		String password=createPassword();
		user.setName(name);
		user.setPassword(password);
			//将“用户列表.txt”文件中Object对象传入userList集合中
			//判断是否已该存在管理员
		return user;
	}
	//注册新用户方法
	public User createCustomerUser() {
		User user=null;
		boolean flag=false;
		String[] cardIdString=new String[9];
		do{
			System.out.println("品牌序号：1.话唠卡  2.网虫卡  3.超人卡   /n请选择品牌序号：");
			int chooseType=input.nextInt();
			switch(chooseType){
			//1.话唠卡 
			case 1:/*话唠卡注册函数*/user=createSingleCard(cardIdString);break;
			//2.网虫卡 
			case 2:/*网虫卡注册函数*/user=createNetCard(cardIdString);break;
			//3.超人卡 
			case 3:/*超人卡注册函数*/user=createSuperCard(cardIdString);break;
			default:flag=true;System.out.println("输入有误，请重新输入序号：1或2或者3");
			}
		}while(flag);
		return user;
	}
	
	/*话唠卡注册函数*/
	private User createSingleCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("请选择号码序号：");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("请输入要充值的话费额度");
					double huafei=input.nextDouble();
					//套餐类型：话唠套餐通话时长：200分钟 短信条数：50条月资费：58元
					user=new SingleCard(name, password, cardIdString[i], 58, huafei-58, 200, 50);
					flag=false;
				}else{
					System.out.println("请重新输入一个介于1至9之间的整数");
				}
		}while(flag);
		return user;
	}
	/*网虫卡注册函数*/
	private User createNetCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("请选择号码序号：");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("请输入要充值的话费额度");
					double huafei=input.nextDouble();
					//套餐类型：网虫套餐上网流量：5GB月资费：68元
					user=new NetCard(name, password, cardIdString[i], 68, huafei-68, 5*1024);
					flag=false;
				}else{
					System.out.println("请重新输入一个介于1至9之间的整数");
				}
		}while(flag);
		return user;
	}
	/*超人卡注册函数*/
	private User createSuperCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("请选择号码序号：");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("请输入要充值的话费额度");
					double huafei=input.nextDouble();
					//套餐类型：超人套餐通话时长：200分钟 短信条数：100条上网流量：1GB月资费：78元
					user=new SuperCard(name, password, cardIdString[i], 78, huafei-78, 1024, 200, 100);
					flag=false;
				}else{
					System.out.println("请重新输入一个介于1至9之间的整数");
				}
		}while(flag);
		return user;		
	}

	//打印号码
	private void printCardID(String[] cardIdString) {
		for (int i = 0; i < cardIdString.length; i++) {
			System.out.print((i+1)+":"+cardIdString[i]+"\t");
			if((i+1)%3==0){
				System.out.println();
			}
		}		
	}

	//创建号码列表
	private void getCardId(String[] cardIdString) {
		Random rand=new Random();
		for(int i=0;i<9;i++){
			StringBuffer stringBuffer=new StringBuffer("139");
			for(int j=0;j<8;j++){
				int k=rand.nextInt(10);
				stringBuffer.append(k);
			}
			cardIdString[i]=stringBuffer.toString();
		}
	}
		
	//根据name判断是否存在该用户或者管理员
	public boolean judgeExist(String name){
		boolean flag=false;
		try {readUser("用户列表.txt");
		} catch (Exception e) {e.printStackTrace();}
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getName().equals(name)){
				flag=true;
			}
		}
		return flag;
	}

	//根据name判断用户类型，并返回该name对应的管理员或者用户实例对象
		public User getExistUser(String name){
			User user=null;
			if(getAdminUser(name)!=null){
				user=getAdminUser(name);//获得管理员对象
			}else if(getNetCard(name)!=null){
				user=getNetCard(name);//获得用户网虫卡对象
			}else if(getSingleCard(name)!=null){
				user=getSingleCard(name);//获得用户话唠卡对象
			}else if(getSuperCard(name)!=null){
				user=getSuperCard(name);//获得用户超人卡对象
			}
			return user;
		}
		//获得管理员对象
	private AdminUser getAdminUser(String name){
		AdminUser adminUser=null;
		if(judgeExist(name)){
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i) instanceof AdminUser){
					if(userList.get(i).getName().equals(name)){
						adminUser=(AdminUser) userList.get(i);
					}
				}
			}
		}
		return adminUser;
	}
	//获得用户网虫卡对象
	private SuperCard getSuperCard(String name){
		SuperCard superCard=null;
		if(judgeExist(name)){
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i) instanceof SuperCard){
					if(userList.get(i).getName().equals(name)){
						superCard=(SuperCard) userList.get(i);
					}
				}
			}
		}
		return superCard;
	}
	//获得用户话唠卡对象
	private NetCard getNetCard(String name){
		NetCard netCard=null;
		if(judgeExist(name)){
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i) instanceof NetCard){
					if(userList.get(i).getName().equals(name)){
						netCard=(NetCard) userList.get(i);
					}
				}
			}
		}
		return netCard;
	}
	//获得用户超人卡对象
	private SingleCard getSingleCard(String name){
		SingleCard adminUser=null;
		if(judgeExist(name)){
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i) instanceof SingleCard){
					if(userList.get(i).getName().equals(name)){
						adminUser=(SingleCard) userList.get(i);
					}
				}
			}
		}
		return adminUser;
	}
	
	//资费说明
	public void printPrice(String string) throws Exception {
		FileReader fis=new FileReader(new File(string));
		BufferedReader bis=new BufferedReader(fis);
		String str=null;
		while((str=bis.readLine())!=null){
			System.out.println(str);
		}
		bis.close();
		fis.close();
	}
	//判断登录成功
	public boolean login(String name,String password){
		User user=null;
		boolean flag=false;
		if(judgeExist(name)){
			user=getExistUser(name);
			if(user instanceof AdminUser){
				if(user.getName().equals(name)&&user.getPassword().equals(password)){
					flag=true;
				}
			}else if(user instanceof CustomerUser){
				if(user.getName().equals(name)&&user.getPassword().equals(password)){
					flag=true;
				}
			}
		}
		return flag;
	}
	//登录后操作：
	public void loginIn(String name,String password) throws Exception {
		User user=getExistUser(name);
		if(user instanceof AdminUser){
			loginAdminUser();
		}else if(user instanceof CustomerUser){
			loginCustomerUser(name);
		}
		
	}
	private void loginCustomerUser(String name) {
		//得到具体的对象函数：
		boolean flag=true;
		while(flag){
			System.out.println("请选择");
			System.out.println("1、查询当月账单  2、查询套餐余量  3、话费充值  4、使用嗖嗖");
			int choose=input.nextInt();
			switch (choose) {
			case 1:
				if(getSingleCard(name)!=null){
					System.out.println("您的手机号码为："+getSingleCard(name).getCardId());
					System.out.println("本月账单为：");
					System.out.print("当前消费："+getSingleCard(name).getDebtMoney());
					System.out.print("\n账户余额为："+getSingleCard(name).getCountMoney());
					System.out.println("\n基础套餐为话唠套餐：通话时长：200分钟   短信条数：50条");
				}else if(getNetCard(name)!=null){
					System.out.println("您的手机号码为："+getNetCard(name).getCardId());
					System.out.println("本月账单为：");
					System.out.print("当前消费："+getNetCard(name).getDebtMoney());
					System.out.print("\n账户余额为："+getNetCard(name).getCountMoney());
					System.out.println("\n基础套餐为网虫套餐：上网流量：5GB");
				}else if(getSuperCard(name)!=null){
					System.out.println("您的手机号码为："+getSuperCard(name).getCardId());
					System.out.println("本月账单为：");
					System.out.print("当前消费："+getSuperCard(name).getDebtMoney());
					System.out.print("\n账户余额为："+getSuperCard(name).getCountMoney());
					System.out.println("\n基础套餐为超人套餐：通话时长：200分钟  短信条数：100条   上网流量：1GB");
				}
				System.out.println("是否还要继续消费：1、继续；2、返回主页");
				int choose01=input.nextInt();
				if(choose01==1){
					flag=true;
				}
				if(choose01==2){
					flag=false;
				}
				continue;
			case 2:
				if(getSingleCard(name)!=null){
					System.out.println("欢迎使用话唠卡套餐，您的套餐剩余用量为：");
					System.out.println("通话时长剩余："+getSingleCard(name).getTalkNum()+"分钟\n短信剩余："+getSingleCard(name).getMessageNum()+"条");
				}else if(getNetCard(name)!=null){
					System.out.println("欢迎使用网虫卡套餐，您的套餐剩余用量为：");
					System.out.println("流量剩余："+getNetCard(name).getNetNum()+"MB");
				}else if(getSuperCard(name)!=null){
					System.out.println("欢迎使用超人卡套餐，您的套餐剩余用量为：");
					System.out.println("通话时长剩余："+getSuperCard(name).getTalkNum()+"分钟\n短信剩余："+getSuperCard(name).getMessageNum()+"条"
							+"\t\t流量剩余："+getSuperCard(name).getNetNum()+"MB");
				}
				System.out.println("是否还要继续消费：1、继续；2、返回主页");
				int choose02=input.nextInt();
				if(choose02==1){
					flag=true;
				}
				if(choose02==2){
					flag=false;
				}
				continue;
			case 3:
				System.out.println("请输入充值的金额：");
				double adding=input.nextDouble();
				for(int i=0;i<userList.size();i++){
					if(userList.get(i) instanceof CustomerUser){
						if(userList.get(i).getName().equals(name)){
							((CustomerUser)userList.get(i)).addCountMoney(adding);
							try {addUser(null);//将修改过的集合存入集合
							} catch (Exception e) {e.printStackTrace();}
						}
					}
				}
				System.out.println("是否还要继续消费：1、继续；2、返回主页");
				int choose03=input.nextInt();
				if(choose03==1){
					flag=true;
				}
				if(choose03==2){
					flag=false;
				}
				continue;
			case 4:
				boolean flag01=false;
				do{
					try {
						usingSoso(name);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("是否还要继续消费：1、yes；2、no");
					int choose04=input.nextInt();
					if(choose04==1){
						flag01=true;
					}
					if(choose04==2){
						flag01=false;
					}
				}while(flag01);
				System.out.println("是否还要继续消费：1、继续；2、返回主页");
				int choose05=input.nextInt();
				if(choose05==1){
					flag=true;
				}
				if(choose05==2){
					flag=false;
				}
				continue;
			}
		}
		
	}
	
	//使用嗖嗖
	private void usingSoso(String name) throws Exception {
		Random rand=new Random();
//		场景1：问候客户，谁知其如此难缠  通话90分钟
//		场景2：晚上手机在线看韩剧，不小心睡着了，使用流量2G
//		场景3：参与环境保护实施方案问卷调查   发送短信5条
//		场景4：查看微信朋友圈，使用流量30M
//		场景5：和女友视频聊天，使用流量1.5G
//		场景6：询问妈妈身体状况，本地通话30分钟
		int choose=rand.nextInt(6);
		switch (choose+1) {
		case 1:
			useSosoSystem(0, 0, 90, name);
			System.out.println("场景1：问候客户，谁知其如此难缠  通话90分钟");
			break;
		case 2:
			System.out.println("场景2：晚上手机在线看韩剧，不小心睡着了，使用流量2G");
			useSosoSystem(2048,0,0, name);
			break;
		case 3:
			System.out.println("场景3：参与环境保护实施方案问卷调查   发送短信5条");
			useSosoSystem(0,5,0, name);
			break;
		case 4:
			System.out.println("场景4：查看微信朋友圈，使用流量30M");
			useSosoSystem(30, 0, 0, name);
			break;
		case 5:
			System.out.println("场景5：和女友视频聊天，使用流量1.5G");
			useSosoSystem(1536, 0, 0, name);
			break;
		case 6:
			System.out.println("场景6：询问妈妈身体状况，本地通话30分钟");
			useSosoSystem(0, 0, 30, name);
			break;
		}
	}
	
	//客户使用soso进行消费
	private void useSosoSystem(int netNum,int messageNum,int talkNum,String name) throws Exception{
		//
		if(getNetCard(name)!=null){//是网虫卡
			NetCard netCard=getNetCard(name);
			if(netCard.getCountMoney()>0){//余额充足
				if(netCard.getNetNum()>netNum){//套餐内流量充足
					double d1=netCard.getCountMoney()-messageNum*0.1-talkNum*0.2-messageNum*0.1;
					netCard.setDebtMoney(netCard.getCountMoney()+messageNum*0.1+talkNum*0.2);//账单增加
					netCard.setCountMoney(d1);//余额减少
					netCard.setNetNum(netCard.getNetNum()-netNum);//套餐流量减少
					addUser(netCard);//保存数据
				}else{
					double d1=netCard.getCountMoney()-messageNum*0.1-talkNum*0.2-messageNum*0.1-(netNum-netCard.getNetNum())*0.1;
					netCard.setDebtMoney(netCard.getCountMoney()+messageNum*0.1+talkNum*0.2
							+(netNum-netCard.getNetNum())*0.1);//账单增加
					netCard.setCountMoney(d1);//余额减少
					netCard.setNetNum(0);//套餐流量清零
					addUser(netCard);//保存数据
				}
			}else{
				System.out.println("余额不足，请充值！未实现消费");
			}
		}
		if(getSingleCard(name)!=null){//是话唠卡
			SingleCard singleCard=getSingleCard(name);
			if(singleCard.getCountMoney()>0){//余额充足
				if(singleCard.getTalkNum()>talkNum){//套餐内通话时间充足
					if(singleCard.getMessageNum()>messageNum){//通话时间和短信数目都充足
						double d1=singleCard.getCountMoney()-netNum*0.1;//用流量直接扣费
						singleCard.setMessageNum(singleCard.getMessageNum()-messageNum);//套餐内短信减少
						singleCard.setTalkNum(singleCard.getTalkNum()-talkNum);//套餐内通话时间减少
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1);//账单增加
						singleCard.setCountMoney(d1);//余额减少
						addUser(singleCard);//保存数据
					}else{//短信不足；
						double d2=singleCard.getCountMoney()-netNum*0.1-(messageNum-singleCard.getMessageNum())*0.1;
						singleCard.setMessageNum(0);//套餐内短信清零
						singleCard.setTalkNum(singleCard.getTalkNum()-talkNum);//套餐内通话时间减少
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(messageNum-singleCard.getMessageNum())*0.1);//账单增加
						singleCard.setCountMoney(d2);//余额减少
						addUser(singleCard);//保存数据
					}
				}else{//通话时间不足
					if(singleCard.getMessageNum()>messageNum){//短信数目都充足
						double d2=singleCard.getCountMoney()-netNum*0.1-(talkNum-singleCard.getTalkNum())*0.2;//使用通话和流量扣费
						singleCard.setMessageNum(singleCard.getMessageNum()-messageNum);//套餐内短信减少
						singleCard.setTalkNum(0);//套餐内通话时间清零
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(talkNum-singleCard.getTalkNum())*0.2);//账单增加
						singleCard.setCountMoney(d2);//余额减少
						addUser(singleCard);
					}else{//短信不足；
						double d2=singleCard.getCountMoney()-netNum*0.1-(messageNum-singleCard.getMessageNum())*0.1-
								(talkNum-singleCard.getTalkNum())*0.2;//使用流量，通话，短信都扣费
						singleCard.setMessageNum(0);//套餐内短信清零
						singleCard.setTalkNum(0);//套餐内通话时间清零
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(messageNum-singleCard.getMessageNum())*0.1+(talkNum-singleCard.getTalkNum())*0.2);//账单增加
						singleCard.setCountMoney(d2);//余额减少
						addUser(singleCard);//保存数据
					}
				}
			}else{
				System.out.println("余额不足，请充值！未实现消费");
			}
		}
		if(getSuperCard(name)!=null){//是超人卡
			SuperCard superCard=getSuperCard(name);
			if(superCard.getCountMoney()>0){//余额充足
				if(superCard.getTalkNum()>talkNum){//通话时间充足
					if(superCard.getMessageNum()>messageNum){//通话时间和短信充足
						if(superCard.getNetNum()>netNum){//通话时间，短信，流量都充足
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//套餐短信减少
							superCard.setNetNum(superCard.getNetNum()-netNum);//套餐流量减少
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//套餐通话时间减少
							addUser(superCard);//存储数据
						}else{//通话时间，短信，都充足  流量不足
							double d1=superCard.getCountMoney()-(netNum-superCard.getNetNum())*0.1;//使用流量扣费
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//套餐短信减少
							superCard.setNetNum(0);//套餐流量清零
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//套餐通话时间减少
							superCard.setDebtMoney(superCard.getDebtMoney()+(netNum-superCard.getNetNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}
					}else{//通话时间充足，短信不足
						if(superCard.getNetNum()>netNum){//通话时间充足，短信不足，流量充足
							double d1=superCard.getCountMoney()-(messageNum-superCard.getMessageNum())*0.1;//使用短信扣费
							superCard.setMessageNum(0);//套餐短信清零
							superCard.setNetNum(superCard.getNetNum()-netNum);//套餐流量减少
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//套餐通话时间减少
							superCard.setDebtMoney(superCard.getDebtMoney()+(messageNum-superCard.getMessageNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}else{//通话时间充足，短信不足  流量不足
							double d1=superCard.getCountMoney()-(messageNum-superCard.getMessageNum())*0.1
									-(netNum-superCard.getNetNum())*0.1;//使用短信和流量扣费
							superCard.setMessageNum(0);//套餐短信清零
							superCard.setNetNum(0);;//套餐流量清零
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//套餐通话时间减少
							superCard.setDebtMoney(superCard.getDebtMoney()+
									(messageNum-superCard.getMessageNum())*0.1+(netNum-superCard.getNetNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}
					}
				}else{//套餐通话时间不足
					if(superCard.getMessageNum()>messageNum){//通话时间不足和短信充足
						if(superCard.getNetNum()>netNum){//通话时间不足和短信充足，流量充足
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2;//使用通话扣费
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//套餐短信减少
							superCard.setNetNum(superCard.getNetNum()-netNum);;//套餐流量减少
							superCard.setTalkNum(0);//套餐通话时间清零
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}else{//通话时间不足和短信充足  流量不足
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2
									-(netNum-superCard.getNetNum())*0.1;//使用通话和流量扣费
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//套餐短信减少
							superCard.setNetNum(0);;//套餐流量清零
							superCard.setTalkNum(0);//套餐通话时间清零
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2
									+(netNum-superCard.getNetNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}
					}else{//通话时间不足，短信不足
						if(superCard.getNetNum()>netNum){//通话时间不足，短信不足，流量充足
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2-
									(messageNum-superCard.getMessageNum())*0.1;//使用通话和短信扣费
							superCard.setMessageNum(0);//套餐短信清零
							superCard.setNetNum(superCard.getNetNum()-netNum);//套餐流量减少
							superCard.setTalkNum(0);//套餐通话时间清零
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2+
									(messageNum-superCard.getMessageNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}else{//通话时间不足，短信不足  流量不足
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2-
									(messageNum-superCard.getMessageNum())*0.1-(netNum-superCard.getNetNum())*0.1;//使用通话、短信和流量扣费
							superCard.setMessageNum(0);//套餐短信清零
							superCard.setNetNum(0);//套餐流量清零
							superCard.setTalkNum(0);//套餐通话时间清零
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2+
									(messageNum-superCard.getMessageNum())*0.1+(netNum-superCard.getNetNum())*0.1);//账单增加
							superCard.setCountMoney(d1);//话费减少
							addUser(superCard);//存储数据
						}
					}
				}
				
			}else{
				System.out.println("余额不足，请充值！未实现消费");
			}
			
		}
	}
	
	
	//客户端登录后操作
	private void loginAdminUser() throws Exception {
		boolean flag=true;
		while(flag){
			System.out.println("请选择");
			System.out.println("1、查询用户信息    2、办理退网");
			int choose=input.nextInt();
			switch (choose) {
			case 1:
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i) instanceof CustomerUser){
						CustomerUser c1=(CustomerUser)userList.get(i);
						System.out.println("======");
						System.out.println("用户名："+c1.getName()+"\n密码："+c1.getPassword()+"\n电话号码："+c1.getCardId());
					}
				}
				continue;
			case 2:
				System.out.println("请输入退网的电话号码");
				String str=input.next();
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i) instanceof CustomerUser){
						CustomerUser c1=(CustomerUser)userList.get(i);
						if(c1.getCardId().equals(str)){
							userList.remove(i);
							addUser(null);
							System.out.println("号码"+str+"退网成功");
							flag=false;
						}
					}
				}
				if(flag){
					System.out.println("输入号码未注册，是否还要继续？");
					System.out.println("1、继续；2、返回主页");
					int choose001=input.nextInt();
					if(choose001==1){
						flag=true;
					}
					if(choose001==2){
						flag=false;
					}
				}
				break;
			}
		}
		
	}
	
	
	
}
