Feature: Create Service Request link

 @smoke
 Scenario Outline: verifying Admin can Create Service Request link
 Given User navigates to login page
 When User enters valid email address <email> into email field
 And User enters valid password <pwd> into password field
 And User clicks on Login button 
 Then User should get successfully logged in and Create Service Request link is display
 When user click Create Service Request link
 Then Client Information page is display
 When the user enters the following details:
 | Choose an Account| Branch |Branch Person |Loan Type  |Reference Number    |Loan Amount (INR)|
 | <ChooseAccount>  |<Branch>|<BranchPerson>|<LoanType> | <ReferenceNumber>  | <LoanAmount>    |
 When user clicks on Save&Next
Then success message is dislay and user navigate to Service Details page.
When user Select Sevices and Technical Report Type

When User is on the Add Applicant/Co-Applicant Details section  
And User enters Name as "<name>", selects Customer Type and Applicant Type from dropdowns  
And User enters Mobile Number and Enter Additional Information and Add Address 
And User click Add Address button and fill Required details :
  | Address      |  Pincode  |
  | <Address>    | <Pincode> |
When User navigates to the Add Collateral Details section and verify Collateral Id it should be disabled and selects Collateral Type from dropdown  
And User clicks the "Add Address" button and fills in the required details:
  | Address     |  Pincode  |
  | <Address>   | <Pincode> |
 And  user clicks on Submit Button


  Examples:
  |  email              |    pwd    |ChooseAccount	 |Branch| BranchPerson   |  LoanType        |ReferenceNumber|LoanAmount| name     | Address   |     Pincode          | 
  |testingcord@gfgc.com |Test@12345 |    DMI         |Rewari|Jagdish Prashad |Home Loan Purchase| REF-789456    | 50000    |Abhishek  | Mansarovar|    302022-test master| 


