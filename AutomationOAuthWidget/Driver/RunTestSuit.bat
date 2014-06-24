@echo off
copy ..\src\testng.xml ..\bin\testng.xml
copy ..\data\TestCases.XML ..\CustomizeResult\EndResult.XML
copy .\ZFunctionLibrary.class ..\bin\FunctionLib\ZFunctionLibrary.class
set classpath=.;..\bin;..\jarFiles\junit-4.1.jar;..\jarFiles\org.testng.eclipse_5.8.0.1.jar;..\jarFiles\poi.jar;..\jarFiles\selenium-java-client-driver.jar;..\jarFiles\selenium-java-client-driver-sources.jar;..\jarFiles\selenium-java-client-driver-tests.jar;..\jarFiles\selenium-java-client-driver-test-sources.jar;..\jarFiles\selenium-server.jar;..\jarFiles\testng-6.1.1.jar;
java org.testng.TestNG -D ../test-output ../bin/testng.xml
pause