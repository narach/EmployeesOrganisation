1. How to run the task
   1.1) Create executable jar file with maven: mvn clean install
   1.2) Run java -jar $path_to_executable_jar $path_to_csv $path_to_connfig
   Notes: $path_to_config is optional parameter used to configure settings: (lowSalaryFactor, highSalaryFactor, reporitingLineLength).
   If not provided - the default values(1.2, 1.5, 4) from the task description will be used.

2. Assumptions for test data:
   2.1) Multiple CEOs (Employee lines with managerId=null) is correct
   2.2) Employee can be a manager for himself( so called contractors)
