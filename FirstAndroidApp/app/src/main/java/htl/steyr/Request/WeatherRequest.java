package htl.steyr.Request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WeatherRequest {
    private static final String API_KEY = "21123c650b8c6e1711c298ee618f6991";
    private static final String API_REQUEST_URL = "https://api.openweathermap.org/data/2.5/{{ENDPOINT}}?appid=" + API_KEY;
    private static final double KELVIN_OFFSET = 273.15;


    private RequestQueue queue = null;
    private static WeatherRequest instance = null;

    private WeatherRequest(Context c) {
        queue = Volley.newRequestQueue(c);
    }

    public static synchronized WeatherRequest getInstance(Context c) {
        if (instance == null) {
            instance = new WeatherRequest(c);
        }

        return instance;
    }

    public void getForecast(String city,
                            Response.Listener<JSONObject> success,
                            Response.ErrorListener error) {

        String apiUrl = API_REQUEST_URL.replace("{{ENDPOINT}}", "forecast");
        apiUrl += "&q=" + city;

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                success,
                error
        );

        queue.add(request);
    }
}
