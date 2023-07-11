# LEAVE MANAGEMENT SYSTEM


## ABOUT THE PROJECT 

This project is a leave manager that enables users to take leave.

Leave approvals follow 2 options either manually by an ADMIN user 
or the system auto-approves based on the current day been equal to leave start date.


## STACKS USED 
1. Spring boot with Java for API Development
2. Mongodb DB for data storage 
3. Mockito for testing

## SYSTEM FUNCTIONALITY
1. Registration
2. Authentication using username and password. Token provision for authenticated resources.
3. Leave request
4. Deletion of requested leave
5. Automated leave approval 
6. Email Notification for registration and leave requests
7. Appropriate Error responses

## FUTURE IMPROVEMENT 
1. Complete the implementation of the system bases tests 
2. Add more functionalities such as update of a booking if not approved 
3. Refinement of the user Roles and Authorities 
4. Replacement of Roles with Enum
5. Replacement of Leave status with Enums

## CHALLENGES
1. Implementation of spring security


# NB:
1. Roles ```ADMIN OR USER```
2. Leave status ```PENDING OR CONFIRMED```
3. THE ADMIN RECEIVING NOTIFICATION IS IN CODE BASE  ```EmailConstant.java``` REFERENCE IS THE USERNAME AND PASSWORD 

## PERSONAL EXPERIENCE 
The project was assigned to me by Peswa. I am highly motivated for the lessons learnt. 
1. Learnt the new way of securing spring applications 
2. Took a short course developing a spring application with mongodb as In have never used mongodb in any spring application

