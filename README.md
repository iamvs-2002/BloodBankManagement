# Blood Bank Management System
The Blood Management System is an application through which any person who is interested in donating blood can register himself. 
Moreover, if any general consumer wants to make a request for blood online he can also take the help of this application. 
The Blood Management System in java is planned to collect blood from many donors in short from various sources and distribute that blood to needy people who require blood. 


### Tables:
- user
  * user(UserID,username,emailID,Address,phonenumber,password,post,Date);

- donor
  * donor(UserID,BloodGrp,AnyDisease,Quantity,Disease,Date,phonenumber);
  * FK_1: UserID references user(UserID);

- bloodbank
  * bloodbank(BloodGrp,Quantity);

- receiver
  * receiver(PatientID,PateintName,PateintAge,StaffID,BloodGrp,volume,contactNumber,Date);

- staff_transaction
  * staff_transaction(StaffID,Date,status);
