package server_communication;

import android.util.Base64;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import modelclass.Responce;

/**
 * Created by product on 10/19/2016.
 */
public class Server_Connection {

    static InputStream is = null;
    JSONObject jObj = null;
  //  String json="";

    public Responce getMethod(String url) {
        final Responce res = new Responce();
            HttpURLConnection urlConnection = null;
            try {
                // create connection
                URL urlToRequest = new URL(url);
                urlConnection = (HttpURLConnection) urlToRequest.openConnection();
                urlConnection.setRequestMethod("GET");

                int statusCode = urlConnection.getResponseCode();

                if(statusCode==200 || statusCode==201 ) {
                    res.setStatus(true);
                    InputStream in = new BufferedInputStream( urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    res.setResponce(sb.toString());
                }
                if(statusCode==400 || statusCode==401 || statusCode==404 ) {
                    res.setStatus(false);
                    res.setMessage("Invalid Request Error...");
                }
                if(statusCode==500 ) {
                    res.setStatus(false);
                    res.setMessage("Server Error...! Try again Later");
                }
                if(statusCode>500 ) {
                    res.setStatus(false);
                    res.setMessage("Oops your net seems low!! Retry later...");
                }

            } catch (MalformedURLException e) {
                res.setStatus(false);
                res.setMessage("Unknown error !! contact app administrator");
            } catch (SocketTimeoutException e) {
                res.setStatus(false);
                res.setMessage("Oops your net seems low!! Retry later...");
            } catch (IOException e) {
                res.setStatus(false);
                res.setMessage("Unknown error !! contact app administrator");
            } catch (Exception e) {
                res.setStatus(false);
                res.setMessage("Unknown error !! contact app administrator");// response body is no valid JSON string
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return res;
        }

    public Responce  postMethod(final String json,final String url){
        final Responce res = new Responce();
        HttpURLConnection urlConnection = null;
        try {

            URL urlToRequest = new URL(url);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            String basicAuth = "Basic " + new String(Base64.encodeToString("intranet:MehUcASwa8RUgew7".getBytes(), Base64.DEFAULT));
            urlConnection.setRequestProperty("Authorization", basicAuth);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");
            OutputStream os = urlConnection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode==200 || statusCode==201 ) {
                res.setStatus(true);
                InputStream in = new BufferedInputStream( urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                res.setResponce(sb.toString());
            }
            if(statusCode==400 || statusCode==401 || statusCode==404 ) {
                res.setStatus(false);
                res.setMessage("Invalid Request Error...");
            }
            if(statusCode==500 ) {
                res.setStatus(false);
                res.setMessage("Server Error...! Try again Later");
            }
            if(statusCode>500 ) {
                res.setStatus(false);
                res.setMessage("Oops your net seems low!! Retry later...");
            }

        } catch (MalformedURLException e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");
        } catch (SocketTimeoutException e) {
            res.setStatus(false);
            res.setMessage("Oops your net seems low!! Retry later...");
        } catch (IOException e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");
        } catch (Exception e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");// response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return res;
    }

    public Responce  putMethod(final String json,final String url){
        final Responce res = new Responce();
        HttpURLConnection urlConnection = null;
        try {

            URL urlToRequest = new URL(url);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            String basicAuth = "Basic " + new String(Base64.encodeToString("intranet:MehUcASwa8RUgew7".getBytes(), Base64.DEFAULT));
            urlConnection.setRequestProperty("Authorization", basicAuth);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("PUT");
            OutputStream os = urlConnection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode==200 || statusCode==201 ) {
                res.setStatus(true);
                InputStream in = new BufferedInputStream( urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                res.setResponce(sb.toString());
            }
            if(statusCode==400 || statusCode==401 || statusCode==404 ) {
                res.setStatus(false);
                res.setMessage("Invalid Request Error...");
            }
            if(statusCode==500 ) {
                res.setStatus(false);
                res.setMessage("Server Error...! Try again Later");
            }
            if(statusCode>500 ) {
                res.setStatus(false);
                res.setMessage("Oops your net seems low!! Retry later...");
            }

        } catch (MalformedURLException e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");
        } catch (SocketTimeoutException e) {
            res.setStatus(false);
            res.setMessage("Oops your net seems low!! Retry later...");
        } catch (IOException e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");
        } catch (Exception e) {
            res.setStatus(false);
            res.setMessage("Unknown error !! contact app administrator");// response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return res;
    }


    public Responce multipartFromRequest(String filepath,final String urlTo){
        Responce res=new Responce();

        HttpURLConnection connection = null;
        DataOutputStream outputStream;

        String fileMimeType="image/*";
        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";
        String filefield="image";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filepath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + fileMimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            int statusCode=connection.getResponseCode();
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
            file.delete();

            if(statusCode==200 || statusCode==201 ) {
                res.setStatus(true);
                InputStream in = new BufferedInputStream( connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                res.setResponce(sb.toString());
            }
            if(statusCode==400 || statusCode==401 || statusCode==404 ) {
                res.setStatus(false);
                res.setMessage("Invalid Request Error...");
            }
            if(statusCode==500 ) {
                res.setStatus(false);
                res.setMessage("Server Error...! Try again Later");
            }
            if(statusCode>500 ) {
                res.setStatus(false);
                res.setMessage("Oops your net seems low!! Retry later...");
            }

        } catch (MalformedURLException e) {
        res.setStatus(false);
        res.setMessage("Unknown error !! contact app administrator");
    } catch (SocketTimeoutException e) {
        res.setStatus(false);
        res.setMessage("Oops your net seems low!! Retry later...");
    } catch (IOException e) {
        res.setStatus(false);
        res.setMessage("Unknown error !! contact app administrator");
    } catch (Exception e) {
        res.setStatus(false);
        res.setMessage("Unknown error !! contact app administrator");// response body is no valid JSON string
    } finally {

        if (connection != null) {
            connection.disconnect();
        }
    }

        return res;
    }
}
