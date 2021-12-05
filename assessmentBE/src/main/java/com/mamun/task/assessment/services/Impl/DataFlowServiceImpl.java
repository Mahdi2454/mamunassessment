package com.mamun.task.assessment.services.Impl;

import com.mamun.task.assessment.services.DataflowService;
import com.opencsv.CSVWriter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFlowServiceImpl implements DataflowService {


    @Override
    public List<String> reduceFile(int nbrToTreat, List<String> file) {
        return  file.stream().limit(nbrToTreat).collect(Collectors.toList());
    }

    @Override
    public List<String> inversFile(List<String> file) {
        Collections.reverse(file);
        return file;
    }

    @Override
    public List<String> fullJoin(List<String> firstFile, List<String> secondFile, int nbrToTreat) {

        //We assume that one of the  List size is n for full join because the return should be n and
        //it don't cover when the tow lists are < n
        List<String> newList = new ArrayList<>();

        if(firstFile.size()==nbrToTreat&&secondFile.size()==nbrToTreat){
            for(int i=0; i<nbrToTreat;i++)
            {
                //in order to have the second list in the first column
                newList.add(secondFile.get(i)+","+firstFile.get(i));
            }
            return  newList;
        }
        //case one the first one is shorter
        else if (firstFile.size()<nbrToTreat){
            for(int i=0; i<firstFile.size();i++)
            {
                //in order to have the second list in the first column
                newList.add(secondFile.get(i)+","+firstFile.get(i));
            }
            for (int i=firstFile.size();i<secondFile.size();i++){
                newList.add(secondFile.get(i)+","+ "" );
            }
            return newList;
        }
        //case when the second one is shorter
        else {
            for(int i=0; i<secondFile.size();i++)
            {
                //in order to have the second list in the first column
                newList.add(secondFile.get(i)+","+firstFile.get(i));
            }
            for (int i=firstFile.size();i<secondFile.size();i++){
                newList.add(""+","+ firstFile.get(i) );
            }
            return newList;
        }
    }


    @Override
    public List<String> normalJoin(List<String> firstFile, List<String> secondFile) {
        List<String> newList = new ArrayList<>();

        if (firstFile.size() > secondFile.size() == true) {

            for (int i=0;i< secondFile.size();i++){
                //in order to have the second list in the first column
                newList.add(secondFile.get(i)+","+firstFile.get(i));
            }
            return newList;
        } else if (firstFile.size() < secondFile.size() == true) {
            for (int i=0;i< firstFile.size();i++){
                //in order to have the second list in the first column
                newList.add(secondFile.get(i)+","+firstFile.get(i));
            }
            return  newList;
        }
        else newList=this.fullJoin(firstFile,secondFile,firstFile.size());
        return newList;
    }

    @Override
    public List<String> flowNow(List<String> firstFile, List<String> secondFile, int nbrToTreat, String collationType) {

        //prepare the data for the Collation
        secondFile = inversFile(reduceFile(nbrToTreat,secondFile));
        firstFile = reduceFile(nbrToTreat,firstFile);

        switch (collationType){
            case "Normal": return normalJoin(firstFile, secondFile);
            case "Full" : return  fullJoin(firstFile,secondFile,nbrToTreat);
            //in order to cover compilation error
            default: return  normalJoin(firstFile,secondFile);
        }



    }

    @Override
    public File createCsvfromDAta(List<String> fileData, String filePath) throws IOException {

        String uri = ResourceLoader.CLASSPATH_URL_PREFIX;

        File file = new File("filedir/DataflowResultFor"+filePath+".csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { fileData.get(0) };
            writer.writeNext(header);

            for (int i=1;i<fileData.size();i++){
                // add data to csv
                String[] data = { fileData.get(i)};
                writer.writeNext(data);
            }

            // closing writer connection
            writer.close();
            //create the actual file
            file.createNewFile();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  file;
    }

}
