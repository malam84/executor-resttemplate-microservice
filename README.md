# executor-resttemplate-microservice
This application describes  in detail to call microservices rest api parallely using executorservice

Please follow below steps to recreate this demo in your own system

Clone the code using $git clone git@github.com:malam84/executor-resttemplate-microservice.git

<b>account-info Services: </b>
<br/><br/>
Step1: Please access account-info folder and provide correct mysql root, password and database name inside application.properties file and also repeat for flyway.
<br/><br/>
Step2: Run moven command to run application<br/>
       $ mvn -DskipTests clean install<br/>
       $ mvn spring-boot:run
<br/><br/>
Step3: Test these two api in postman <br/>
       1. accinfobyaccno<br/>
       2. accinfobycustid

        
       


