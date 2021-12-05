package com.mamun.task.assessment.contorller;


import com.mamun.task.assessment.model.DataFlow;
import com.mamun.task.assessment.services.DataflowService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@Controller
public class DataFlowController {

    @Autowired
    DataflowService dataflowService;

    @MessageMapping("/flow")
    @SendTo("/topic/flow")
    public String flowData(DataFlow message) throws Exception {
        Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();
        System.out.println("Time in milliseconds using Date class: " + timeMilli);
        System.out.println("Controller Work fine !" + message.toString());
        List<String> fileData =dataflowService.flowNow(message.getFirstFile(),message.getSecondFile(),message.getNumberLines(),message.getCollationType());
        File file = dataflowService.createCsvfromDAta(fileData,String.valueOf(timeMilli) );
        FileInputStream input = new FileInputStream(file);
        //Default call for the Embebed apache on the Tomcat Server
        String base64String = Base64.encodeBase64String( org.apache.commons.io.IOUtils.toByteArray(input));
        return base64String ;
    }
}
