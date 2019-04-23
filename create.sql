#Alter session
ALTER SESSION SET "_ORACLE_SCRIPT" = true;

#Creating a User
CREATE USER pooja IDENTIFIED BY "pooja@22";

#The Grant Statement
GRANT CONNECT, RESOURCE, DBA TO pooja;

#Assigning Privileges
GRANT CREATE SESSION GRANT ANY PRIVILEGE TO pooja;

GRANT ANY PRIVILEGE TO pooja;
