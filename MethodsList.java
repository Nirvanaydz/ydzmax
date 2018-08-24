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
	 //��ӣ��洢�����л��洢���Լ���<User>�洢�����û��б�.txt��
	 public void addUser(User user) throws Exception{
		 if(user!=null){
			 for(int i=0;i<userList.size();i++){
				 if(userList.get(i).getName().equals(user.getName())){
					 userList.remove(i);
				 }
			 }
			 this.userList.add(user);
			 FileOutputStream ops=new FileOutputStream(new File("�û��б�.txt"));
			 ObjectOutputStream oos=new ObjectOutputStream(ops);
			 oos.writeObject(this.userList);
			 oos.close();
			 ops.close();
		 }else{
			 FileOutputStream ops=new FileOutputStream(new File("�û��б�.txt"));
			 ObjectOutputStream oos=new ObjectOutputStream(ops);
			 oos.writeObject(this.userList);
			 oos.close();
			 ops.close();
		 }
	 }
	 //�����л���ȡ���û��б�.txt�������ݵ�����
	 public void readUser(String string) throws Exception{
		 FileInputStream fis=new FileInputStream(new File(string));
		 ObjectInputStream ois=new ObjectInputStream(fis);
		 this.userList=(ArrayList<User>) ois.readObject();
		 ois.close();
		 fis.close();
	 }

	 //�ж��û�ע��ʱ������û����������Ƿ�����
	 public String createName(){
		 boolean flag=true;
		 String name=null;
		 do{
			 System.out.println("�����������û���");
			 name=input.next();
			 if(judgeExist(name)){
				 System.out.println("����Ա�û��Ѵ��ڣ�����������");
				 continue;
			 }
			 if(name.length()<=2){
				 System.out.println("�����볤�ȳ���3���ַ�");
				 continue;
			 }else{
				 flag=false;
			 }
		 }while(flag);
		 return name;
	 }
	 //�ж��û�ע��ʱ����������Ƿ�����
	 public String createPassword(){
		 boolean flag=true;
		 String password=null;
		 do{
			 System.out.println("��������������");
			 password=input.next();
			 if(password.length()<=5){
				 System.out.println("�����볤�ȳ���6���ַ�");
				 continue;
			 }else{
				 flag=false;
			 }
		 }while(flag);
		 return password;
	 }
	 
	//ע�����Ա����
	public User createAdminUser() throws Exception {
		User user=new AdminUser();
		String name=createName();
		String password=createPassword();
		user.setName(name);
		user.setPassword(password);
			//�����û��б�.txt���ļ���Object������userList������
			//�ж��Ƿ��Ѹô��ڹ���Ա
		return user;
	}
	//ע�����û�����
	public User createCustomerUser() {
		User user=null;
		boolean flag=false;
		String[] cardIdString=new String[9];
		do{
			System.out.println("Ʒ����ţ�1.���뿨  2.���濨  3.���˿�   /n��ѡ��Ʒ����ţ�");
			int chooseType=input.nextInt();
			switch(chooseType){
			//1.���뿨 
			case 1:/*���뿨ע�ắ��*/user=createSingleCard(cardIdString);break;
			//2.���濨 
			case 2:/*���濨ע�ắ��*/user=createNetCard(cardIdString);break;
			//3.���˿� 
			case 3:/*���˿�ע�ắ��*/user=createSuperCard(cardIdString);break;
			default:flag=true;System.out.println("��������������������ţ�1��2����3");
			}
		}while(flag);
		return user;
	}
	
	/*���뿨ע�ắ��*/
	private User createSingleCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("��ѡ�������ţ�");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("������Ҫ��ֵ�Ļ��Ѷ��");
					double huafei=input.nextDouble();
					//�ײ����ͣ������ײ�ͨ��ʱ����200���� ����������50�����ʷѣ�58Ԫ
					user=new SingleCard(name, password, cardIdString[i], 58, huafei-58, 200, 50);
					flag=false;
				}else{
					System.out.println("����������һ������1��9֮�������");
				}
		}while(flag);
		return user;
	}
	/*���濨ע�ắ��*/
	private User createNetCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("��ѡ�������ţ�");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("������Ҫ��ֵ�Ļ��Ѷ��");
					double huafei=input.nextDouble();
					//�ײ����ͣ������ײ�����������5GB���ʷѣ�68Ԫ
					user=new NetCard(name, password, cardIdString[i], 68, huafei-68, 5*1024);
					flag=false;
				}else{
					System.out.println("����������һ������1��9֮�������");
				}
		}while(flag);
		return user;
	}
	/*���˿�ע�ắ��*/
	private User createSuperCard(String[] cardIdString) {
		User user=null;
		boolean flag=true;
		getCardId(cardIdString);
		printCardID(cardIdString);
		do{
			System.out.println("��ѡ�������ţ�");
			int i=input.nextInt();
				if(i>=1&&i<=9){
					String name=createName();
					String password=createPassword();
					System.out.println("������Ҫ��ֵ�Ļ��Ѷ��");
					double huafei=input.nextDouble();
					//�ײ����ͣ������ײ�ͨ��ʱ����200���� ����������100������������1GB���ʷѣ�78Ԫ
					user=new SuperCard(name, password, cardIdString[i], 78, huafei-78, 1024, 200, 100);
					flag=false;
				}else{
					System.out.println("����������һ������1��9֮�������");
				}
		}while(flag);
		return user;		
	}

	//��ӡ����
	private void printCardID(String[] cardIdString) {
		for (int i = 0; i < cardIdString.length; i++) {
			System.out.print((i+1)+":"+cardIdString[i]+"\t");
			if((i+1)%3==0){
				System.out.println();
			}
		}		
	}

	//���������б�
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
		
	//����name�ж��Ƿ���ڸ��û����߹���Ա
	public boolean judgeExist(String name){
		boolean flag=false;
		try {readUser("�û��б�.txt");
		} catch (Exception e) {e.printStackTrace();}
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getName().equals(name)){
				flag=true;
			}
		}
		return flag;
	}

	//����name�ж��û����ͣ������ظ�name��Ӧ�Ĺ���Ա�����û�ʵ������
		public User getExistUser(String name){
			User user=null;
			if(getAdminUser(name)!=null){
				user=getAdminUser(name);//��ù���Ա����
			}else if(getNetCard(name)!=null){
				user=getNetCard(name);//����û����濨����
			}else if(getSingleCard(name)!=null){
				user=getSingleCard(name);//����û����뿨����
			}else if(getSuperCard(name)!=null){
				user=getSuperCard(name);//����û����˿�����
			}
			return user;
		}
		//��ù���Ա����
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
	//����û����濨����
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
	//����û����뿨����
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
	//����û����˿�����
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
	
	//�ʷ�˵��
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
	//�жϵ�¼�ɹ�
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
	//��¼�������
	public void loginIn(String name,String password) throws Exception {
		User user=getExistUser(name);
		if(user instanceof AdminUser){
			loginAdminUser();
		}else if(user instanceof CustomerUser){
			loginCustomerUser(name);
		}
		
	}
	private void loginCustomerUser(String name) {
		//�õ�����Ķ�������
		boolean flag=true;
		while(flag){
			System.out.println("��ѡ��");
			System.out.println("1����ѯ�����˵�  2����ѯ�ײ�����  3�����ѳ�ֵ  4��ʹ����");
			int choose=input.nextInt();
			switch (choose) {
			case 1:
				if(getSingleCard(name)!=null){
					System.out.println("�����ֻ�����Ϊ��"+getSingleCard(name).getCardId());
					System.out.println("�����˵�Ϊ��");
					System.out.print("��ǰ���ѣ�"+getSingleCard(name).getDebtMoney());
					System.out.print("\n�˻����Ϊ��"+getSingleCard(name).getCountMoney());
					System.out.println("\n�����ײ�Ϊ�����ײͣ�ͨ��ʱ����200����   ����������50��");
				}else if(getNetCard(name)!=null){
					System.out.println("�����ֻ�����Ϊ��"+getNetCard(name).getCardId());
					System.out.println("�����˵�Ϊ��");
					System.out.print("��ǰ���ѣ�"+getNetCard(name).getDebtMoney());
					System.out.print("\n�˻����Ϊ��"+getNetCard(name).getCountMoney());
					System.out.println("\n�����ײ�Ϊ�����ײͣ�����������5GB");
				}else if(getSuperCard(name)!=null){
					System.out.println("�����ֻ�����Ϊ��"+getSuperCard(name).getCardId());
					System.out.println("�����˵�Ϊ��");
					System.out.print("��ǰ���ѣ�"+getSuperCard(name).getDebtMoney());
					System.out.print("\n�˻����Ϊ��"+getSuperCard(name).getCountMoney());
					System.out.println("\n�����ײ�Ϊ�����ײͣ�ͨ��ʱ����200����  ����������100��   ����������1GB");
				}
				System.out.println("�Ƿ�Ҫ�������ѣ�1��������2��������ҳ");
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
					System.out.println("��ӭʹ�û��뿨�ײͣ������ײ�ʣ������Ϊ��");
					System.out.println("ͨ��ʱ��ʣ�ࣺ"+getSingleCard(name).getTalkNum()+"����\n����ʣ�ࣺ"+getSingleCard(name).getMessageNum()+"��");
				}else if(getNetCard(name)!=null){
					System.out.println("��ӭʹ�����濨�ײͣ������ײ�ʣ������Ϊ��");
					System.out.println("����ʣ�ࣺ"+getNetCard(name).getNetNum()+"MB");
				}else if(getSuperCard(name)!=null){
					System.out.println("��ӭʹ�ó��˿��ײͣ������ײ�ʣ������Ϊ��");
					System.out.println("ͨ��ʱ��ʣ�ࣺ"+getSuperCard(name).getTalkNum()+"����\n����ʣ�ࣺ"+getSuperCard(name).getMessageNum()+"��"
							+"\t\t����ʣ�ࣺ"+getSuperCard(name).getNetNum()+"MB");
				}
				System.out.println("�Ƿ�Ҫ�������ѣ�1��������2��������ҳ");
				int choose02=input.nextInt();
				if(choose02==1){
					flag=true;
				}
				if(choose02==2){
					flag=false;
				}
				continue;
			case 3:
				System.out.println("�������ֵ�Ľ�");
				double adding=input.nextDouble();
				for(int i=0;i<userList.size();i++){
					if(userList.get(i) instanceof CustomerUser){
						if(userList.get(i).getName().equals(name)){
							((CustomerUser)userList.get(i)).addCountMoney(adding);
							try {addUser(null);//���޸Ĺ��ļ��ϴ��뼯��
							} catch (Exception e) {e.printStackTrace();}
						}
					}
				}
				System.out.println("�Ƿ�Ҫ�������ѣ�1��������2��������ҳ");
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
					System.out.println("�Ƿ�Ҫ�������ѣ�1��yes��2��no");
					int choose04=input.nextInt();
					if(choose04==1){
						flag01=true;
					}
					if(choose04==2){
						flag01=false;
					}
				}while(flag01);
				System.out.println("�Ƿ�Ҫ�������ѣ�1��������2��������ҳ");
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
	
	//ʹ����
	private void usingSoso(String name) throws Exception {
		Random rand=new Random();
//		����1���ʺ�ͻ���˭֪������Ѳ�  ͨ��90����
//		����2�������ֻ����߿����磬��С��˯���ˣ�ʹ������2G
//		����3�����뻷������ʵʩ�����ʾ����   ���Ͷ���5��
//		����4���鿴΢������Ȧ��ʹ������30M
//		����5����Ů����Ƶ���죬ʹ������1.5G
//		����6��ѯ����������״��������ͨ��30����
		int choose=rand.nextInt(6);
		switch (choose+1) {
		case 1:
			useSosoSystem(0, 0, 90, name);
			System.out.println("����1���ʺ�ͻ���˭֪������Ѳ�  ͨ��90����");
			break;
		case 2:
			System.out.println("����2�������ֻ����߿����磬��С��˯���ˣ�ʹ������2G");
			useSosoSystem(2048,0,0, name);
			break;
		case 3:
			System.out.println("����3�����뻷������ʵʩ�����ʾ����   ���Ͷ���5��");
			useSosoSystem(0,5,0, name);
			break;
		case 4:
			System.out.println("����4���鿴΢������Ȧ��ʹ������30M");
			useSosoSystem(30, 0, 0, name);
			break;
		case 5:
			System.out.println("����5����Ů����Ƶ���죬ʹ������1.5G");
			useSosoSystem(1536, 0, 0, name);
			break;
		case 6:
			System.out.println("����6��ѯ����������״��������ͨ��30����");
			useSosoSystem(0, 0, 30, name);
			break;
		}
	}
	
	//�ͻ�ʹ��soso��������
	private void useSosoSystem(int netNum,int messageNum,int talkNum,String name) throws Exception{
		//
		if(getNetCard(name)!=null){//�����濨
			NetCard netCard=getNetCard(name);
			if(netCard.getCountMoney()>0){//������
				if(netCard.getNetNum()>netNum){//�ײ�����������
					double d1=netCard.getCountMoney()-messageNum*0.1-talkNum*0.2-messageNum*0.1;
					netCard.setDebtMoney(netCard.getCountMoney()+messageNum*0.1+talkNum*0.2);//�˵�����
					netCard.setCountMoney(d1);//������
					netCard.setNetNum(netCard.getNetNum()-netNum);//�ײ���������
					addUser(netCard);//��������
				}else{
					double d1=netCard.getCountMoney()-messageNum*0.1-talkNum*0.2-messageNum*0.1-(netNum-netCard.getNetNum())*0.1;
					netCard.setDebtMoney(netCard.getCountMoney()+messageNum*0.1+talkNum*0.2
							+(netNum-netCard.getNetNum())*0.1);//�˵�����
					netCard.setCountMoney(d1);//������
					netCard.setNetNum(0);//�ײ���������
					addUser(netCard);//��������
				}
			}else{
				System.out.println("���㣬���ֵ��δʵ������");
			}
		}
		if(getSingleCard(name)!=null){//�ǻ��뿨
			SingleCard singleCard=getSingleCard(name);
			if(singleCard.getCountMoney()>0){//������
				if(singleCard.getTalkNum()>talkNum){//�ײ���ͨ��ʱ�����
					if(singleCard.getMessageNum()>messageNum){//ͨ��ʱ��Ͷ�����Ŀ������
						double d1=singleCard.getCountMoney()-netNum*0.1;//������ֱ�ӿ۷�
						singleCard.setMessageNum(singleCard.getMessageNum()-messageNum);//�ײ��ڶ��ż���
						singleCard.setTalkNum(singleCard.getTalkNum()-talkNum);//�ײ���ͨ��ʱ�����
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1);//�˵�����
						singleCard.setCountMoney(d1);//������
						addUser(singleCard);//��������
					}else{//���Ų��㣻
						double d2=singleCard.getCountMoney()-netNum*0.1-(messageNum-singleCard.getMessageNum())*0.1;
						singleCard.setMessageNum(0);//�ײ��ڶ�������
						singleCard.setTalkNum(singleCard.getTalkNum()-talkNum);//�ײ���ͨ��ʱ�����
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(messageNum-singleCard.getMessageNum())*0.1);//�˵�����
						singleCard.setCountMoney(d2);//������
						addUser(singleCard);//��������
					}
				}else{//ͨ��ʱ�䲻��
					if(singleCard.getMessageNum()>messageNum){//������Ŀ������
						double d2=singleCard.getCountMoney()-netNum*0.1-(talkNum-singleCard.getTalkNum())*0.2;//ʹ��ͨ���������۷�
						singleCard.setMessageNum(singleCard.getMessageNum()-messageNum);//�ײ��ڶ��ż���
						singleCard.setTalkNum(0);//�ײ���ͨ��ʱ������
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(talkNum-singleCard.getTalkNum())*0.2);//�˵�����
						singleCard.setCountMoney(d2);//������
						addUser(singleCard);
					}else{//���Ų��㣻
						double d2=singleCard.getCountMoney()-netNum*0.1-(messageNum-singleCard.getMessageNum())*0.1-
								(talkNum-singleCard.getTalkNum())*0.2;//ʹ��������ͨ�������Ŷ��۷�
						singleCard.setMessageNum(0);//�ײ��ڶ�������
						singleCard.setTalkNum(0);//�ײ���ͨ��ʱ������
						singleCard.setDebtMoney(singleCard.getDebtMoney()+netNum*0.1+
								(messageNum-singleCard.getMessageNum())*0.1+(talkNum-singleCard.getTalkNum())*0.2);//�˵�����
						singleCard.setCountMoney(d2);//������
						addUser(singleCard);//��������
					}
				}
			}else{
				System.out.println("���㣬���ֵ��δʵ������");
			}
		}
		if(getSuperCard(name)!=null){//�ǳ��˿�
			SuperCard superCard=getSuperCard(name);
			if(superCard.getCountMoney()>0){//������
				if(superCard.getTalkNum()>talkNum){//ͨ��ʱ�����
					if(superCard.getMessageNum()>messageNum){//ͨ��ʱ��Ͷ��ų���
						if(superCard.getNetNum()>netNum){//ͨ��ʱ�䣬���ţ�����������
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//�ײͶ��ż���
							superCard.setNetNum(superCard.getNetNum()-netNum);//�ײ���������
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//�ײ�ͨ��ʱ�����
							addUser(superCard);//�洢����
						}else{//ͨ��ʱ�䣬���ţ�������  ��������
							double d1=superCard.getCountMoney()-(netNum-superCard.getNetNum())*0.1;//ʹ�������۷�
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//�ײͶ��ż���
							superCard.setNetNum(0);//�ײ���������
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//�ײ�ͨ��ʱ�����
							superCard.setDebtMoney(superCard.getDebtMoney()+(netNum-superCard.getNetNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}
					}else{//ͨ��ʱ����㣬���Ų���
						if(superCard.getNetNum()>netNum){//ͨ��ʱ����㣬���Ų��㣬��������
							double d1=superCard.getCountMoney()-(messageNum-superCard.getMessageNum())*0.1;//ʹ�ö��ſ۷�
							superCard.setMessageNum(0);//�ײͶ�������
							superCard.setNetNum(superCard.getNetNum()-netNum);//�ײ���������
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//�ײ�ͨ��ʱ�����
							superCard.setDebtMoney(superCard.getDebtMoney()+(messageNum-superCard.getMessageNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}else{//ͨ��ʱ����㣬���Ų���  ��������
							double d1=superCard.getCountMoney()-(messageNum-superCard.getMessageNum())*0.1
									-(netNum-superCard.getNetNum())*0.1;//ʹ�ö��ź������۷�
							superCard.setMessageNum(0);//�ײͶ�������
							superCard.setNetNum(0);;//�ײ���������
							superCard.setTalkNum(superCard.getTalkNum()-talkNum);//�ײ�ͨ��ʱ�����
							superCard.setDebtMoney(superCard.getDebtMoney()+
									(messageNum-superCard.getMessageNum())*0.1+(netNum-superCard.getNetNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}
					}
				}else{//�ײ�ͨ��ʱ�䲻��
					if(superCard.getMessageNum()>messageNum){//ͨ��ʱ�䲻��Ͷ��ų���
						if(superCard.getNetNum()>netNum){//ͨ��ʱ�䲻��Ͷ��ų��㣬��������
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2;//ʹ��ͨ���۷�
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//�ײͶ��ż���
							superCard.setNetNum(superCard.getNetNum()-netNum);;//�ײ���������
							superCard.setTalkNum(0);//�ײ�ͨ��ʱ������
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}else{//ͨ��ʱ�䲻��Ͷ��ų���  ��������
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2
									-(netNum-superCard.getNetNum())*0.1;//ʹ��ͨ���������۷�
							superCard.setMessageNum(superCard.getMessageNum()-messageNum);//�ײͶ��ż���
							superCard.setNetNum(0);;//�ײ���������
							superCard.setTalkNum(0);//�ײ�ͨ��ʱ������
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2
									+(netNum-superCard.getNetNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}
					}else{//ͨ��ʱ�䲻�㣬���Ų���
						if(superCard.getNetNum()>netNum){//ͨ��ʱ�䲻�㣬���Ų��㣬��������
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2-
									(messageNum-superCard.getMessageNum())*0.1;//ʹ��ͨ���Ͷ��ſ۷�
							superCard.setMessageNum(0);//�ײͶ�������
							superCard.setNetNum(superCard.getNetNum()-netNum);//�ײ���������
							superCard.setTalkNum(0);//�ײ�ͨ��ʱ������
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2+
									(messageNum-superCard.getMessageNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}else{//ͨ��ʱ�䲻�㣬���Ų���  ��������
							double d1=superCard.getCountMoney()-(talkNum-superCard.getTalkNum())*0.2-
									(messageNum-superCard.getMessageNum())*0.1-(netNum-superCard.getNetNum())*0.1;//ʹ��ͨ�������ź������۷�
							superCard.setMessageNum(0);//�ײͶ�������
							superCard.setNetNum(0);//�ײ���������
							superCard.setTalkNum(0);//�ײ�ͨ��ʱ������
							superCard.setDebtMoney(superCard.getDebtMoney()+(talkNum-superCard.getTalkNum())*0.2+
									(messageNum-superCard.getMessageNum())*0.1+(netNum-superCard.getNetNum())*0.1);//�˵�����
							superCard.setCountMoney(d1);//���Ѽ���
							addUser(superCard);//�洢����
						}
					}
				}
				
			}else{
				System.out.println("���㣬���ֵ��δʵ������");
			}
			
		}
	}
	
	
	//�ͻ��˵�¼�����
	private void loginAdminUser() throws Exception {
		boolean flag=true;
		while(flag){
			System.out.println("��ѡ��");
			System.out.println("1����ѯ�û���Ϣ    2����������");
			int choose=input.nextInt();
			switch (choose) {
			case 1:
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i) instanceof CustomerUser){
						CustomerUser c1=(CustomerUser)userList.get(i);
						System.out.println("======");
						System.out.println("�û�����"+c1.getName()+"\n���룺"+c1.getPassword()+"\n�绰���룺"+c1.getCardId());
					}
				}
				continue;
			case 2:
				System.out.println("�����������ĵ绰����");
				String str=input.next();
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i) instanceof CustomerUser){
						CustomerUser c1=(CustomerUser)userList.get(i);
						if(c1.getCardId().equals(str)){
							userList.remove(i);
							addUser(null);
							System.out.println("����"+str+"�����ɹ�");
							flag=false;
						}
					}
				}
				if(flag){
					System.out.println("�������δע�ᣬ�Ƿ�Ҫ������");
					System.out.println("1��������2��������ҳ");
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
