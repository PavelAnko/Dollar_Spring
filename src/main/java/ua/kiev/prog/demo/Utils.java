package ua.kiev.prog.demo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Component
public class Utils {
    private static String url = "https://bank.gov.ua/NBU_Exchange/exchange_site?";
    private String startData = "start=";
    private String finishData = "&end=";
    private String currencyCode = "&valcode=";
    private static String sortAndOrdersParams = "&sort=exchangedate&order=desc&json";

    public List<CurrencyUnit> getCurrencyStatistic(String startData, String finishData, String currencyCode) {
        Type itemsListType = new TypeToken<List<CurrencyUnit>>() {
        }.getType();
        String response = null;
        try {
            response = getStringFromResponse(getUrlWithParams(startData, finishData, currencyCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        List<CurrencyUnit> currencyUnits = gson.fromJson(response, itemsListType);
        return currencyUnits;
    }
    private static String getStringFromResponse(URL url) throws IOException {
        String strBuf;
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        InputStream is = http.getInputStream();
        try {
            byte[] buf = Utils.responseBodyToArray(is);
            strBuf = new String(buf, StandardCharsets.UTF_8);

        } finally {
            is.close();
        }
        return strBuf;
    }
    private URL getUrlWithParams(String startData, String finishData, String currencyCode) throws MalformedURLException {
        return new URL(url + this.startData + startData + this.finishData + finishData + this.currencyCode + currencyCode + sortAndOrdersParams);
    }
    private static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();
    }
}
