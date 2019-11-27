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
			System.out.println("******��ӭʹ�����ƶ�ҵ��*******");  
			System.out.println("1.ע�����û�");
			System.out.println("2.��¼��");
			System.out.println("3.�ʷ�˵��");
			System.out.println("4.�˳�ϵͳ");
			System.out.println("��ѡ�����:");
			menuChoose=input.nextInt();
			
			switch(menuChoose){
			case 1://ע�����û�����
				boolean flag=true;
				User newUser=null;//����user���ղ��������û�
				do{
					System.out.println("�û�ע�᣺");
					System.out.println("1������Ա    2�����ƶ��û�"+"\n����������ѡ��");
					int chooseCreate=input.nextInt();
					if(chooseCreate==1){
						newUser=methodsList.createAdminUser();//����һ������Աʵ������
						System.out.println("�¹���Աע��ɹ�����Ϣ����");
						System.out.println("�û�����"+newUser.getName()+"\t\t���룺"+newUser.getPassword());
						methodsList.addUser(newUser);//�洢��"�û��б�.txt"
						flag=false;
					}else if(chooseCreate==2){
						newUser=methodsList.createCustomerUser();//������һ���û�ʵ��
						CustomerUser ctu=(CustomerUser)newUser;
						if(newUser instanceof SingleCard){
							System.out.println("ע���˻��뿨����Ϣ���£�");
							ctu.info();
						}else if(newUser instanceof NetCard){
							System.out.println("ע�������濨����Ϣ���£�");
							ctu.info();
//							methodsList.addUser((pojo.NetCard)ctu);
						}else if(newUser instanceof SuperCard){
							System.out.println("ע���˳��˿�����Ϣ���£�");
							ctu.info();
//							methodsList.addUser((pojo.SuperCard)ctu);
						}
						methodsList.addUser(newUser);//�洢��"�û��б�.txt"
						flag=false;
					}
				}while(flag);
				continue;
			case 2:
				System.out.println("�������û���");
				String name=input.next();
				System.out.println("����������");
				String password=input.next();
				if(methodsList.login(name,password)){
					System.out.println("��¼�ɹ�");
					methodsList.loginIn(name,password);
				}else{
					System.out.print("��¼ʧ�ܣ�ԭ��Ϊ��");
					if(methodsList.judgeExist(name)){
						System.out.println("�����������");
					}else{
						System.out.println("�û�����δע�ᣬ����ע�ᣡ");
					}
				}continue;
			case 3:methodsList.printPrice("�ײ��ʷ�˵��.txt");//�ʷ�˵��
			case 4:
			default:
			}
		}while(menuChoose!=4);
		
	}

}
