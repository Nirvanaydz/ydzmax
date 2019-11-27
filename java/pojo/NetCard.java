package pojo;

import service.NetService;

public class NetCard extends CustomerUser implements NetService {
	
	private int netNum;
	
	//constructor
public NetCard(){}
	
	public int getNetNum() {
	return netNum;
}
public void setNetNum(int netNum) {
	this.netNum = netNum;
}

	public NetCard(String name, String password, String cardId,
			double debtMoney, double countMoney,int netNum) {
		super(name, password, cardId, debtMoney, countMoney);
		this.netNum=netNum;
	}

	@Override
	public void netService(int netNum) throws Exception {
		System.out.println("用了"+netNum+"上网流量。");
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
