package com.echovue.checks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
public class ZipCodeApiCheck {
    private static final String API_KEY = "11111111111111";
    private static final String CHECK_NAME = "Zip_Code_API_Check";
    private static final String ELEMENT_FQN = "ip-172-31-17-222";
    private static final int TTL = 360;

    private static final String METRICLY_URL = "https://api.app.netuitive.com/check/";

    private static final Logger log = LoggerFactory.getLogger(ZipCodeApiCheck.class);
 //300000
    @Scheduled(fixedRate = 10000)
    public void performZipCodeCheck() {
        try {
            //{apiId}/{checkName}/{elementFqn}/{ttl}


            log.info("Performing " + CHECK_NAME);

            String url = METRICLY_URL + "/" + API_KEY + "/" + CHECK_NAME + "/" + ELEMENT_FQN + "/" + TTL;
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            log.info("\nSending 'POST' request to URL : " + url);
            log.info("Post parameters : " + urlParameters);
            log.info("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            log.info(response.toString());
        } catch (MalformedURLException mue) {
            log.error("Malformed URL Created");
        } catch (IOException ioe) {
            log.error("I/O Exception");
        }

    }


}
