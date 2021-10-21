# executor-resttemplate-microservice
This application describes  in detail to call microservices rest api parallelly using executorservice

Please follow below steps to recreate this demo in your own system

Clone the code using $git clone git@github.com:malam84/executor-resttemplate-microservice.git

<b>account-info Services: </b>
<br/><br/>
<b>Step1:</b> Please access account-info folder and provide correct mysql root, password and database name inside application.properties file and also repeat for flyway.
<br/><br/>
       <b>Step2:</b> Run below maven command <br/>
       $ mvn -DskipTests clean install<br/>
       $ mvn spring-boot:run
<br/><br/>
<b>Step3:</b> Test these two api in postman <br/>
       1. accinfobyaccno<br/>
       2. accinfobycustid <br/><br/>
       [](url)
<img width="1054" alt="test1" src="https://user-images.githubusercontent.com/42507151/138188907-6246939b-60b2-406b-8695-3a877a17e579.png">

<br/>
<br/>
<b>customer-info Services: </b>
<hr>
<br/>
<b>Step1:</b> Please go inside customer-info folder<br/><br/>
<b>Step2:</b> Run below maven command <br>
$ mvn -DskipTests clean install<br>
$ mvn spring-boot:run
<br/><br/>
<b>Step3:</b> Test these api in postman <br/>
1. accountexecutorsev <br>
   accountexecutorsev api calling account-info multipe time parallelly without blocking other task and catch error if exception occur <br/>
   <img width="1030" alt="test2" src="https://user-images.githubusercontent.com/42507151/138189957-6cf38a54-7ec9-4d3a-b4aa-eedd1e6f6d08.png">
 
2. customerdetail api fetch account infomration synchronously <br>
   <b> http://localhost:8080/api/customerdetail?custId=cust102 </b>

