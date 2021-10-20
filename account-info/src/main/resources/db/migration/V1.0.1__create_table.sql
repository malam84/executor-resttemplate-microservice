/************Table creation ************************************/
CREATE TABLE tbl_acc (id int NOT NULL AUTO_INCREMENT, acc_no int not null, acc_amt int not null,acc_branch varchar(50), acc_status TINYINT NOT NULL, acc_type varchar(50), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, cust_id varchar(50), PRIMARY KEY (id));
/*CREATE TABLE tbl_task2 (id int NOT NULL AUTO_INCREMENT, task_id varchar(20), task_name varchar(50), PRIMARY KEY (id));*/

