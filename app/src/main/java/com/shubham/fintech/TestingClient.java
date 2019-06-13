package com.shubham.fintech;

import com.indus.apiFunction.ApiMethod;

public class TestingClient {

 public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	 ApiMethod apiMethod = new ApiMethod();
	 
	 /*Login 
	 MethodName is login
	 Input is aadhar ,Sample Addhar to be used is 787612346756(For Savings and Loan accounts) 
	 OR 908712345647(For Only Savings Account) or 908709098923(For Only Loan Accounts )*/
	 
	 String loginResponse= apiMethod.login("787612346756"); 
	 System.out.println("The response for Login Request is   "+ loginResponse);
	 
	/* Savings Account Inquiry 
	 Method Name is savingsAccountInquiry
	  Inputs Are Aadhar and Saving Account. Sample data should be 100044334452 account and 787612346756 as aadhar 
	 OR  100034523123 as account and 908712345647 as aadhar 
	 */
	 String savingsAccountsInquiryResponse = apiMethod.savingsAccountInquiry("100034523123", "908712345647");
	 System.out.println("The response for SavingsAccountInquiry method is   "+ savingsAccountsInquiryResponse); 
	
	/* Loan Account Inquiry 
	 Method Name is loanAccountInquiry
	  Inputs Are Aadhar and Loan Account. Sample data should be 543781200321 account and 787612346756 as aadhar 
	 OR  543216542345 as account and 908709098923 as aadhar 
	*/
	 String loanAccountsInquiryResponse = apiMethod.loanAccountInquiry("543216542345", "908709098923");
	 System.out.println("The response for LoanAccountInquiry method is   "+ loanAccountsInquiryResponse); 
	
	/*  Cash Withdrawal
	 Method Name is balWithdraw
	 Input is Addhar,Savings Account Number , Currency ,Amount to be debited , Debit Narration,Cresit Narration,Current Balance of the account.
	 Sample data should be 100044334452 account and 787612346756 as aadhar OR  100034523123 as account and 908712345647 as aadhar 
	  In output participant would be getting new balance .Participant has to make sure  that this balance will be the new balance of the account
	 and has to be reflected throughout the customer journey
	 */
	 String balWithdrawResponse = apiMethod.balWithdraw("787612346756", "100044334452", "INR",500.00,"dbt","cdt",1500.50);
	 System.out.println("The response for balWithdraw method is   "+ balWithdrawResponse);
	 
	/*  Cash Deposit 
	 Method Name is fundDeposit
	 Input is Addhar,Savings Account Number , Currency ,Amount to be debited , Debit Narration,Cresit Narration,Current Balance of the account.
	 Sample data should be 100044334452 account and 787612346756 as aadhar    OR  100034523123 as account and 908712345647 as aadhar 
	  In output participant would be getting new balance .Participant has to make sure  that this balance will be the new balance of the account
	 and has to be reflected throughout the customer journey
	 */
	 
	 String fundDepositResponse = apiMethod.fundDeposit("787612346756", "100044334452", "INR",500.00,"dbt","cdt",1500.50);
	 System.out.println("The response for fundDeposit method is   "+ fundDepositResponse);
	 
	 
		/*  Payment (Any Payment i.e. Bill,EMI,Recharge,Grocery,Household Item) 
	 Method Name is fundDeposit
	 Input is Addhar,Savings Account Number , Currency ,Amount to be debited , Debit Narration,Cresit Narration,Current Balance of the account.
	 Sample data should be 100044334452 as Fromaccount and 787612346756 as aadhar    OR  100034523123 as Fromaccount and 908712345647 as aadhar 
	  In output participant would be getting new balance .Participant has to make sure  that this balance will be the new balance of the account
	 and has to be reflected throughout the customer journey
	 */
	 
	 String paymentResponse = apiMethod.payment("787612346756", "100044334452", "8000102","Savings",500.00,"dbt","cdt",1500.50,"ANOPB2312R");
	 System.out.println("The response for payment method is   "+ paymentResponse);
	 
	 
	/* Bill Fetch API
	 Method Name is billFetch
	 Input is billType for eg Utility , Mobile Number , Biller Name for e.g. Adani ,Vodafone .. , Customer Account number for that biller .
	 Here the most imprtant input is mobile number and sample data for mobile number is 9892065298,9819975645 or 9819975696
	 */
	 String billFetchResponse = apiMethod.billFetch("Utility", "9892065298", "Adani Energy", "1000101001");
	 System.out.println("The response for billFetch method is   "+ billFetchResponse);
	 
	 
	 	
}
}

