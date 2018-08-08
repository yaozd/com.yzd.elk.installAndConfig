call echo mvn package begin
call mvn clean 
call mvn compile 
call mvn install -DskipTests
call echo mvn package end
call pause