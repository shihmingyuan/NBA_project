package com.example.nba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Card extends AppCompatActivity {
    static String serverAddress = "192.168.10.88:580";
    static String id = "";
    static String password = "";
    static String response = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        /* got intent */
        Intent myIntent = getIntent(); // gets the previously created intent
        id = myIntent.getStringExtra("id");
        password = myIntent.getStringExtra("password");

        String website = "http://" + serverAddress + "/drawCard.php";

        URL url;
        try {
            url = new URL(website);

            Log.d("KK", "query drawCard.php ...");

            DoPostConnect doPostConnect = new DoPostConnect();
            doPostConnect.execute(url);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private class DoPostConnect extends AsyncTask<URL, Integer, String> {
        protected String doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            String response="-1";
            for (int i = 0; i < count; i++) {
                response = doPost(urls[i]);
//    			totalSize += Downloader.downloadFile(urls[i]);
//    			publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled()) break;
            }

            return response;
        }

        protected void onProgressUpdate(Integer... progress) {
//    		setProgressPercent(progress[0]);
        }
        protected void onPostExecute(String response) {
//    		showDialog("Downloaded " + result + " bytes");
            setDoPostConnectFinish(response);
        }

    }

    private void setDoPostConnectFinish(String resp) {
        Save.response = resp;

        Log.d("KK", "Card...setDoPostConnectFinish()");

        TextView textViewCard =  (TextView) findViewById(R.id.textViewCard);

        JSONObject o;
        try {
            o = new JSONObject(resp);

            Log.d("KK", "drawCalrd List...setDoPostConnectFinish() got "  + o.getString("result"));

            if ( o.getString("result").equals("OK")) {
                JSONArray jarray = o.getJSONArray("data");

                StringBuffer sb = new StringBuffer();

                for (int i=0; i< jarray.length(); i++ ) {
                    JSONObject data = jarray.getJSONObject(i);

                    sb.append(data.get("pid") + " ");
                    sb.append(data.get("Player") + " ");
                    sb.append(data.get("Pos") + " ");
                    sb.append(data.get("Age") + " ");
                    sb.append(data.get("Tm") + " ");
                    sb.append(data.get("BLK") + " ");
                    sb.append(data.get("TRB") + " ");
                    sb.append(data.get("AST") + " ");
                    sb.append(data.get("FT") + " ");
                    sb.append(data.get("PTS") + " ");
                    sb.append(data.get("STL") + " ");
                    sb.append(data.get("Ability") + " ");
                    sb.append(data.get("Year") + "\n" );

                }

                textViewCard.setText(sb.toString());

            } else {
                Log.d("KK", "Player List error...");
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String doPost(URL url) {

        String ret = "-1";
        Log.d("KK", "認證資訊傳送中...doPost()" + url.toString());

        try {
            // 1. 獲取訪問地址URL
            //URL url = new URL(apiUrl);
            // 2. 建立HttpURLConnection物件
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 設定請求引數等 */
            // 請求方式
            connection.setRequestMethod("POST");
            // 超時時間
            connection.setConnectTimeout(3000);
            // 設定是否輸出
            connection.setDoOutput(true);
            // 設定是否讀入
            connection.setDoInput(true);
            // 設定是否使用快取
            connection.setUseCaches(false);
            // 設定此 HttpURLConnection 例項是否應該自動執行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 設定使用標準編碼格式編碼引數的名-值對
            connection.setRequestProperty("Content-Type",
                    "application/json");
            // 連線
            connection.connect();
            /* 4. 處理輸入輸出 */
            // 寫入引數到請求中
            //String params = "username=test&password=123456";
            JSONObject obj = new JSONObject();
            obj.put("id", Depot.id);
            obj.put("password", Depot.password);

            String params = obj.toString();

            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();

            // 從連線中讀取響應資訊

            //// test begin..
            // Read Response
            InputStream is = connection.getInputStream();
//            InputStreamReader in = new InputStreamReader(is, encoding);
            InputStreamReader in = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(in);


            StringBuffer sb = new StringBuffer();

            int data = in.read();

            while ( data != -1 ) {
//				System.out.print((char) data);
                sb.append((char)data);

                data = in.read();
            }
//			Toast.makeText(EasyPay.this, sb.toString(), Toast.LENGTH_LONG).show();

//			o = new JSONObject(sb.toString());
//
//			resp = o.getString("Mobilephone");
            ret = sb.toString();

            Log.d("KK", "認證資訊傳送中...doPost() got ret " + ret);

            br.close();
            in.close();
            out.close();
            //// test end

//            int code = connection.getResponseCode();
//
//            Log.d("KK", "認證資訊傳送中...doPost() got code " + code);
//
//            if (code == 200) {
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(connection.getInputStream()));
//                String line;
//
//                ret = "";
//
//                while ((line = reader.readLine()) != null) {
//                    ret += line + "\n";
//                }
//                reader.close();
//            }
            // 5. 斷開連線
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }





}