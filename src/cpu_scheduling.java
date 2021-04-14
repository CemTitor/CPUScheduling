
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Cem Yılmaz 
 */

public class cpu_scheduling {
    public static String inputPath;

    public static void main(String[] args) throws IOException {

        inputPath = args[0];

        BufferedReader oku = new BufferedReader(new FileReader(inputPath));
        String lineReader;
        String[] lineList;

        int averageTurnAroundTime;
        int averageWaitingTime;
//        sumOfCpuBurst
        int ioBurstSum = 0;
        int cpuBurstSum = 0;
        int burstTime = 0;
        int processCount = 0;
        int processNumber = 0;


        int tuppleCount = 0;

        ArrayList<Integer> listOfTuppleCounts = new<Integer> ArrayList();
        ArrayList <Integer>listOfCPUBurstOfProcess = new<Integer> ArrayList();

        ArrayList <Integer> listOfIOBurstOfProcess = new<Integer> ArrayList();

        ArrayList <Integer>listOfturnAroundTime =new <Integer>ArrayList();
        ArrayList <Integer>listOfBurstTime =new <Integer>ArrayList();
        int turnAroundCounter = 0;


        int returnValue = 0;
        int countCpu = 0;
        int countIO = 0;
        while ((lineReader = oku.readLine()) != null) {

            ioBurstSum = 0;
            cpuBurstSum = 0;
            burstTime = 0;
            processNumber++;
            /**
             * seperate every line to array
             */
            lineList = lineReader.split("[ (),:;#]+");
            /**
             * travel arrays line by line
             */

            for (int i = 0; i < lineList.length; i += 2) {

                /**
                 * if i is even so it is IO Burst
                 */
                if (i % 2 == 0 && i != 0) {
                    /**
                     * if index value is -1 then dont add to sum.
                     */
                    if (Integer.parseInt(lineList[i]) != -1) {
//                        System.out.println(readerList[j]);

                        ioBurstSum += Integer.parseInt(lineList[i]);
                    }
                    /**
                     * if i is odd so it is CPU Burst
                     */
                }
                if ((i - 1) % 2 == 1) {
//                    System.out.println(readerList[j]);
                    cpuBurstSum += Integer.parseInt(lineList[i - 1]);

                    /**
                     * if i is 0 so it is process id
                     * we need to store process count for average times.
                     */}
                else if (i == 0) {
                    processCount++;
                }
                if (i != 0) {
                    returnValue = Integer.parseInt(lineList[i]) + Integer.parseInt(lineList[i - 1]);
                }
                burstTime = ioBurstSum + cpuBurstSum;
//                System.out.println("burst time is "+burstTime);


            }


            for (int i = 0; i < lineList.length; i++) {


                /**
                 * if i is even so it is IO Burst
                 */
                if (i % 2 == 0 && i != 0) {
                    if (Integer.parseInt(lineList[i]) != -1) {
//                        System.out.println(readerList[j]);
                        listOfIOBurstOfProcess.add(countIO, Integer.valueOf(lineList[i]));
                        ioBurstSum += Integer.parseInt(lineList[i]);
                        countIO++;
                    }

                }
                /**
                 * if i is odd so it is CPU Burst
                 */

                if (i % 2 == 1) {
//                    System.out.println(readerList[j]);
                    listOfCPUBurstOfProcess.add(countCpu, Integer.valueOf(lineList[i]));
                    cpuBurstSum += Integer.parseInt(lineList[i]);
                    countCpu++;
                }

            }
            /**
             * her process için kaç touple olduğunu tutma listesi
             */


//            System.out.println(processNumber + ".process touple sayısı" + tuppleCount);
//            System.out.println("return is " + returnValue);
//            System.out.println(processNumber + ". process  bursttime " + burstTime);
            listOfBurstTime.add(burstTime);
//            System.out.println(processNumber + ". process's cpu burst" + cpuBurstSum);
//            System.out.println(processNumber + ". process's io burst" + ioBurstSum);
            tuppleCount = lineList.length / 2;
            listOfTuppleCounts.add(processCount-1, tuppleCount);
        }

//        System.out.println("cpu burst sayısı"+listOfCPUBurstOfProcess.size());
//        System.out.println("io burst sayısı"+listOfIOBurstOfProcess.size());
        for (int i = 0; i < listOfCPUBurstOfProcess.size(); i++) {
//            System.out.println(i+1 + ". cpu burstu" + listOfCPUBurstOfProcess.get(i));
        }
        for (int i = 0; i < listOfIOBurstOfProcess.size(); i++) {
//            System.out.println((i+1) + ". io burstu" + listOfIOBurstOfProcess.get(i));
        }

//        for (int i = 0; i < processCount; i++) {
//            listOfTuppleCounts.add(i, tuppleCount);
//        }

        /**
         * //her döngüde bir proccesin turnaround timeını bul
         */

//        for (int i = 0; i < listOfTuppleCounts.size()-1; i++) {
//            int currentTurnAroundTime = 0;
//
//           if(listOfTuppleCounts.get(i) <listOfTuppleCounts.get(i+1)){
//               //touple sayısı küçük olan proccesin touple sayısının bir eksiği kadarki bütün process cpu burstlerini topla
//
//               for(int j=0;j<listOfTuppleCounts.get(i)-1;j++){
//                   currentTurnAroundTime+=listOfCPUBurstOfProcess.get(j);
//               }
//               //ek olarak da küçük olan proccesin son toupleındaki cpu+io yu ekle
//               currentTurnAroundTime+=listOfCPUBurstOfProcess.get(i)+listOfIOBurstOfProcess.get(i);
//
//                }
//            listOfturnAroundTime.add(turnAroundCounter,currentTurnAroundTime);
////            turnAroundCounter++;
//        }


        int currentTurnAroundTime2 = 0;
        for (int i = 0; i < listOfTuppleCounts.size(); i++) {
            int countOfCount=processCount;
            //cpu listesini process process gezsin
            for (int k = -1; k < listOfCPUBurstOfProcess.size(); k += processCount + 1) {

                //ilk 3(process count)lüyü ekle
                for (int j = k; j <countOfCount+1; j++) {
                    currentTurnAroundTime2 += listOfCPUBurstOfProcess.get(j + 1);

                }

                countOfCount+=processCount;

            }

            listOfturnAroundTime.add(turnAroundCounter, currentTurnAroundTime2);
            turnAroundCounter++;
            currentTurnAroundTime2=0;

        }


        for (int i = 0; i < listOfTuppleCounts.size(); i++) {
//            System.out.println((i + 1) + ".processin şu kadar toupleı " + listOfTuppleCounts.get(i));
        }
        int sumOfTaT = 0;
        int sumOfBurst=0;
        for(int i=0;i<listOfturnAroundTime.size();i++){

            sumOfTaT+=listOfturnAroundTime.get(i);
            sumOfBurst+=listOfBurstTime.get(i);
        }



        averageTurnAroundTime = sumOfTaT / processCount;
        averageWaitingTime= (sumOfTaT-sumOfBurst)/processCount;

//        System.out.println(sumOfBurst);



        System.out.println("Average turnaround time " + averageTurnAroundTime);
        System.out.println("Average waiting time is " + averageWaitingTime);


    }


}
