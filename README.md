# PPC-low-level-design

PPC is a Payroll solution provider company who manages the payroll of the various companies from
small scale to large scale company.
PPC accepts the employees data from the client in either plain text format (.txt) or csv (.csv) format to
manage the employee life cycle starting from date of onboarding to date of exit.

# Architecture

![image](https://user-images.githubusercontent.com/13993416/218489001-3d8cbe0e-4a27-47d0-bbe1-725b08414721.png)

PPCProgram class is the main executable file. PPCProgram invokes the method processEvents on EventsProcessor.
EventsProcessor class is responsible for connecting to file system and pre process results. It wraps results in a model ProcessedEventModel. 
ProcessedEventModel is returned to PPCProgram.
PPCProgram passes this object to ResultDisplay class which prints the results to console. 



