package com.mycompany.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        getGeneInfo();
        System.exit(0);
    }
         
     /**
     * Get a gene information and print to console
     */
    public static void getGeneInfo() {
        try {
            
            String gene = "cdk2";
            
            String url = "http://mygene.info/v3/query?q=" + gene;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonInfo = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonInfo);

            JsonNode hits = root.path("hits");
            
            JsonNode first = hits.get(0);
            
            String data = mapper.writer().writeValueAsString(first);

            System.out.println("**********GENE DATA********");
            System.out.println("Gene data: " + data);


        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getGeneData");

        }
    }    
}

