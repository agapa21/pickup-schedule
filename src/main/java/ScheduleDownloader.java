import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

public class ScheduleDownloader  {

    ScheduleDownloader(Data data) {

        try {
            var ulica = "56968";
            var numer = "703346";
            var token = "OkkxhC6b9etJBAq7WTHJ0LhIglO18sip";

            //System.out.println(requestBody);
            URL url = new URL("https://kiedywywoz.pl/API/harmo_img/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");

            String LINE_FEED = "\r\n";
            String SEPARATOR = "--";
            String BOUNDARY = "------Boundary" + new BigInteger(128, new SecureRandom()).toString(32);

            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());

            dataOut.writeBytes("Content-Disposition: form-data; name=\"options\"" + LINE_FEED);
            dataOut.writeBytes(LINE_FEED);
            dataOut.writeBytes(
                    "{ \"ulica\" : \"" + ulica + "\", \"numer\" : \"" + numer + "\", \"token\" : \"" + token + "\" }");
            dataOut.writeBytes(LINE_FEED);
            dataOut.writeBytes(SEPARATOR + BOUNDARY + LINE_FEED);
            dataOut.flush();
            dataOut.close();

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
