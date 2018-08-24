package pojo;

import service.MessageService;
import service.NetService;
import service.TalkService;

public class SuperCard extends CustomerUser implements MessageService,NetService,TalkService{
	private int netNum;
	private int talkNum;
	private int messageNum;
	//constructor
	public SuperCard(){}
	
	public SuperCard(String name, String password, String cardId,
			double debtMoney, double countMoney,int netNum,int talkNum,int messageNum) {
		super(name, password, cardId, debtMoney, countMoney);
		this.messageNum=messageNum;
		this.netNum=netNum;
		this.talkNum=talkNum;
	}

	public int getNetNum() {
		return netNum;
	}
	public void setNetNum(int netNum) {
		this.netNum = netNum;
	}
	public int getTalkNum() {
		return talkNum;
	}
	public void setTalkNum(int talkNum) {
		this.talkNum = talkNum;
	}
	public int getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	@Override
	public void talkService(int talkNum) throws Exception {
		System.out.println("����"+talkNum+"ͨ��ʱ�䡣");
	}
	@Override
	public void netService(int netNum) throws Exception {
		System.out.println("����"+netNum+"MB����������");
	}
	@Override
	public void messageService(int messageNum) throws Exception {
		System.out.println("����"+messageNum+"�����š�");
	}
	@Override
	public StringBuffer readDebtMoney() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StringBuffer readCountMoney() {
		// TODO Auto-generated method stub
		return null;
	}

}
