package couch.forrest.csv;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        csvReader.readCSV();
    }

    public List<List<String>> readCSV() {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File("/Users/parks/Documents/GitHub/7th-for_rest-be/src/main/java/couch/forrest/places_COUCH.csv");
        BufferedReader br = null;
        String line = "";

        String a = "숲속아기동물농장COUCH_SEP,COUCH_SEP,속초COUCH_SEP";
        String[] il = a.split("COUCH_SEP,");
        for (String s : il) {
            System.out.println("s = " + s);
        }


        try {
            br = new BufferedReader(new FileReader(csv));
            line = br.readLine();
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();




                line = line.replaceAll("COUCH_SEP\",\"", "COUCH_SEP,");
                line = line.replaceAll("COUCH_SEP,\"", "COUCH_SEP,");
                line = line.replaceAll("COUCH_SEP\",", "COUCH_SEP,");

                line += ",";

                line = line.replaceAll("COUCH_SEP\n", "COUCH_SEP,");
//                System.out.println("line3 = " + line);
                line = line.replaceAll("COUCH_RETURN", "\n");
//                System.out.println("line4 = " + line);
//                System.out.println("line = " + line);
                String[] lineArr = line.split("COUCH_SEP,"); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                System.out.println("lineArr = " + lineArr.length);
//                for (String s : lineArr) {
//                    if ()
//                }

                for (String s : lineArr) {
                    if (s == "EMPTY") {
                        aLine.add("");
                    } else {
                        aLine.add(s);
                    }
                }

//                aLine = Arrays.asList(lineArr);

                csvList.add(aLine);


//            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
//                List<String> aLine = new ArrayList<String>();
//
////                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
//                String[] lineContents = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
//                aLine = Arrays.asList(lineContents);
//                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}