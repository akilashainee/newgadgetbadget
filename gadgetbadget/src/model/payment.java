package model;

public class payment {


	private int payNo;
	private String fName;
	private String cardNo;
	private String cardType;
	private String expMonth;
	private String expYr;
	private String cvvNo;
	private String amount;
	private String status;
	private int Id;		
	
	
public Payment() {
		
	}

	//overload constuctor
	public Payment(int payNo, String fName, String cardNo,String cardType, String expMonth, String expYr,
			String cvvNo, String amount, String status, int Id) {
		super();
		this.payNo = payNo;
		this.fName = fName;
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.expMonth = expMonth;
		this.expYr = expYr;
		this.cvvNo = cvvNo;
		this.amount = amount;
		this.status = status;
		this.Id = Id;
	}


	public int getPayNo() {
		return payNo;
	}


	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	
	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public String getExpMonth() {
		return expMonth;
	}


	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}


	public String getExpYr() {
		return expYr;
	}


	public void setExpYr(String expYr) {
		this.expYr = expYr;
	}


	public String getCvvNo() {
		return cvvNo;
	}


	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getId() {
		return Id;
	}


	public void setId(int Id) {
		this.Id = Id;
	}

}
