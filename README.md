# executor-resttemplate-microservice
This application describes  in detail to call microservices rest api parallely using executorservice

Please follow below steps to recreate this demo in your own system

Clone the code using $git clone git@github.com:malam84/executor-resttemplate-microservice.git

account-info Services:
Step1: Please access account-info folder and provide correct mysql root, password and database name inside application.properties file and also repeat for flyway.
Step2: Run moven command to run application
       $ mvn -DskipTests clean install
       $ mvn spring-boot:run
Step3: Test these two api in postman 
       1. accinfobyaccno
       2. accinfobycustid

        
       


