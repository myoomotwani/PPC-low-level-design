package ppc;


import ppc.model.ProcessedDataModel;

public class PPCProgram {



    public static void main(String[] args) {
        EventsProcessor eventsProcessor = new EventsProcessor("/Users/mayankmotwani/Documents/ppcdatatext.txt");
        ProcessedDataModel processedData =  eventsProcessor.performProcessingTask();

        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplay.printResults(processedData);

    }



}
