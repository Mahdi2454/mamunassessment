package com.mamun.task.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class DataFlow {

    @JsonProperty("collationtype")
    private String collationType;

    @JsonProperty("download")
    private  Boolean download;

    @JsonProperty("numberlines")
    private  int numberLines;

    @JsonProperty("csv1")
    private List<String> firstFile;

    @JsonProperty("csv2")
    private List<String> secondFile;
}
