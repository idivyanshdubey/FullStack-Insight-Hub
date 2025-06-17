@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.6.7-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

:: Start first application
cd /d C:\Users\10827937\OneDrive - LTIMindtree\Desktop\WorkSpaceProper\j\target\
set /p PORT="Enter the port number to run the application: "
start cmd /k java -jar j-0.0.1-SNAPSHOT.jar --server.port=%PORT%

:: Start Admin application
cd /d C:\Users\10827937\OneDrive - LTIMindtree\Desktop\admin\target\
set /p ADMIN_PORT="Enter the port number for the admin application: "
start cmd /k java -jar Admin-0.0.1-SNAPSHOT.jar --server.port=%ADMIN_PORT%

pause