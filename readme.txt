

### README for XLSX, CSV - XML Parser

1) How to run program?

To run the code You need to first build the project using `mvn clean package` command.
Then - You need to run the JAR file. The .jar file has to be run with appropriate arguments.

Template: java -jar [name of jar file with appropiate path] [path of xlsx/csv file] [output]

Example: java -jar main-1.0-jar-with-dependencies.jar /Users/user/testxlsx.xlsx out.xml

2) Worth notice:

This command will print in the Terminal window the result xml file's content.