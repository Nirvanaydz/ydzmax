package pojo;

public abstract class CustomerUser extends User{
	//����
	private String cardId;//����
	private double debtMoney;//�˵����Ѷ�
	private double countMoney;//�˻����
	public CustomerUser(){}
	//constructor
	public CustomerUser(String name, String password, String cardId,
			double debtMoney, double countMoney) {
		super(name, password);
		this.cardId = cardId;
		this.debtMoney = debtMoney;
		this.countMoney = countMoney;
	}
	public CustomerUser(String name, String password, String cardId,
			 double countMoney) {
		super(name, password);
		this.cardId = cardId;
		this.countMoney = countMoney;
	}
	//hashCode(),equals()
	//getter & setter�����������ѽ������ã���ϵͳ��������ʵ���������
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(countMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(debtMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerUser other = (CustomerUser) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (Double.doubleToLongBits(countMoney) != Double
				.doubleToLongBits(other.countMoney))
			return false;
		if (Double.doubleToLongBits(debtMoney) != Double
				.doubleToLongBits(other.debtMoney))
			return false;
		return true;
	}
	public String getCardId() {
		return cardId;
	}
	public double getDebtMoney() {
		return debtMoney;
	}
	public void setCountMoney(double countMoney) {
		this.countMoney = countMoney;
	}
	public double getCountMoney() {
		return countMoney;
	}
	public void addCountMoney(double adding){//�仰��
		countMoney+=adding;
	}
	public void setDebtMoney(double debtMoney) {
		this.debtMoney = debtMoney;
	}
	@Override//for pojo.User
	public void info() {
		System.out.println("*****��ӭʹ���ƶ�����*****\n�û���Ϣ���£�");
		System.out.println("�˺ţ�"+super.getName()+"\n���룺"+super.getPassword()
				+"\n���ţ�"+getCardId());
	}
	public abstract StringBuffer readDebtMoney();
	public abstract StringBuffer readCountMoney();


}
