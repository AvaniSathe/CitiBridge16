#Alter session
ALTER SESSION SET "_ORACLE_SCRIPT" = true;

#Creating a User
CREATE USER pooja IDENTIFIED BY "pooja@22";

#The Grant Statement
GRANT CONNECT, RESOURCE, DBA TO pooja;

#Assigning Privileges
GRANT CREATE SESSION GRANT ANY PRIVILEGE TO pooja;

GRANT ANY PRIVILEGE TO pooja;


#create UserLogin table
create table UserLogin(userID Int primary key ,username varchar(20),password varchar(50));
insert into UserLogin values(1," Bhakti","d1/JelKcDMpQqTiDs2y3pg==");
insert into UserLogin values(2,"Deepshri","YyhtZ9b+gkFbslnK4cvmvA==");
insert into UserLogin values(3,"shivani","jNWKqjk32vmqDpoSLmQ/TA==");
insert into UserLogin values(4,"avani","Fmrmv+vpmPcyRxO6Tx2NSw==");
insert into UserLogin values(5,"pooja","aixZR7esI2zuDjjamRYQag==");


#create UserWishlist table
create table UserWishlist(userID Int not null,companyName varchar(50),arbitrage dec(10,5),quantity Int,currentMarketPrice dec(10,5),transactionDate date,nse dec(9,4),
bse dec(9,4),foreign key(userID) references userLogin(userID));
