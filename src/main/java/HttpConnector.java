import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.io.IOException;

public class HttpConnector {

    final private String token = "OkkxhC6b9etJBAq7WTHJ0LhIglO18sip";

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    HttpConnector(Data data){

        MediaType mediaType = MediaType.parse("text/plain");
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("ulica",data.street)
                .addFormDataPart("numer",data.number)
                .addFormDataPart("token",token)
                .build();
        Request request = new Request.Builder()
                .url("https://kiedywywoz.pl/API/harmo_img/")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = response.body().string();
                //System.out.println("Responce: " + responseBody);

                JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

                if (jsonResponse.has("img")) {
                    String imgValue = jsonResponse.get("img").getAsString();
                    //System.out.println(imgValue);

                    PNGDownloader pngDownloader = new PNGDownloader(imgValue);

                } else {
                    System.out.println("Are u sure it is correct address??");
                }

            } else {
                System.out.println("Error: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
