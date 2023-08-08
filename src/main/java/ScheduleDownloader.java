import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ScheduleDownloader  {

    ScheduleDownloader(Data data) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(data);

            System.out.println(requestBody);

            URL url = new URL("https://mpo.krakow.pl/pl/harmonogram");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(requestBody);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);


        /*if (responseCode == HttpsURLConnection.HTTP_OK) {
            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream("output.png");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            System.out.println("Plik PNG został pobrany i zapisany jako 'output.png'");
        } else {
            System.out.println("Wystąpił błąd podczas pobierania pliku PNG.");
        }*/


            try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);
                }
            }

            conn.disconnect();

            EmailSender emailSender = new EmailSender();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
