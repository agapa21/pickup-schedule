import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;

public class ScheduleDownloader  {

    ScheduleDownloader(Data data) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(data);

            var ulica = 56968; // Ustaw wartość odpowiednią dla Twojego przypadku
            var numer = 703346; // Ustaw wartość odpowiednią dla Twojego przypadku
            var token = "OkkxhC6b9etJBAq7WTHJ0LhIglO18sip";

            //System.out.println(requestBody);
            URL url = new URL("https://kiedywywoz.pl/API/harmo_img/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data");

            String boundary = "boundary";
            String CRLF = "\r\n";

            try (DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream())) {

                outputStream.writeBytes("Content-Disposition: form-data; ulica=\"" + ulica + "\"; numer=\"" + numer + "\"; token=\"" + token + "\""  + CRLF);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
        }
            conn.disconnect();
            //EmailSender emailSender = new EmailSender();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
