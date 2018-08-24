package pojo;

import service.MessageService;
import service.TalkService;

public class SingleCard extends CustomerUser implements MessageService,TalkService{
	
	private int talkNum;
	private int messageNum;
	//constructor
public SingleCard(){}
	
	public SingleCard(String name, String password, String cardId,
			double debtMoney, double countMoney,int talkNum,int messageNum) {
		super(name, password, cardId, debtMoney, countMoney);
		this.messageNum=messageNum;
		this.talkNum=talkNum;
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
