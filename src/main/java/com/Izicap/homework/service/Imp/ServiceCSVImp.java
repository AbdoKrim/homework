package com.Izicap.homework.service.Imp;

import com.Izicap.homework.model.Request;
import com.Izicap.homework.model.Response;
import com.Izicap.homework.service.ServiceCSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class ServiceCSVImp implements ServiceCSV {
    private static int contConv=0;

    public ServiceCSVImp() throws IOException {
    }

    public void addToCsv(Request request, Response response) throws IOException {

        String resp = response.getResponse().replaceAll("[-+.^:,`,\n,`\t,;]"," ");

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/csv/chat.csv",true))) {
            ClassPathResource classPathResource = new ClassPathResource("csv/chat.csv");
            StringBuilder sb = new StringBuilder();
            List<String> lines = Files.readAllLines(Path.of(classPathResource.getURI()));
            System.out.println(lines.size());

            if(lines.size()==0 && contConv==0){
                sb.append("Question");
                sb.append(';');
                sb.append("Answer");
                sb.append('\n');
            }
            sb.append(request.getMessage());
            sb.append(';');
            sb.append(resp);
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
            System.out.println("done!");
            contConv++;


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
 }
