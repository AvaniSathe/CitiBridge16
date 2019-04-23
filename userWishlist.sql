create table UserWishlist(userID Int not null,companyName varchar(50),arbitrage dec(10,5),quantity Int,currentMarketPrice dec(10,5),transactionDate date,nse dec(9,4),
bse dec(9,4),foreign key(userID) references userLogin(userID));
