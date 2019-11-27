package pojo;

public abstract class CustomerUser extends User{
	//属性
	private String cardId;//卡号
	private double debtMoney;//账单消费额
	private double countMoney;//账户余额
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
	//getter & setter，卡号与消费金额不可设置，由系统和消费者实际情况决定
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
	public void addCountMoney(double adding){//充话费
		countMoney+=adding;
	}
	public void setDebtMoney(double debtMoney) {
		this.debtMoney = debtMoney;
	}
	@Override//for pojo.User
	public void info() {
		System.out.println("*****欢迎使用移动搜搜*****\n用户信息如下：");
		System.out.println("账号："+super.getName()+"\n密码："+super.getPassword()
				+"\n卡号："+getCardId());
	}
	public abstract StringBuffer readDebtMoney();
	public abstract StringBuffer readCountMoney();


}
