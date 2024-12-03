
package data_mahasiswa;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.*;


public class Data_mahasiswa {

    public static void main(String[] args) {   
        
        String token = "ufb2a73ed1e2bae2403ea3b3e9b5eb86ed6fdb66b49";
        String query = "select * from t_mhs WHERE mhs_nama = 'Moch Toriqul Huda'";
        
        // Melakukan permintaan ke API
        HttpResponse<String> response = Unirest.post("https://siakad.itmnganjuk.ac.id/api/select")
                .header("Content-Type", "application/json")
                .header("Cookie", "siakaditm1=q1399g4dor4strf9b227ojlghp")
                .body("{\"token\":\"" + token + "\",\r\n\"query\":\"" + query + "\"}")
                .asString();

        // Memproses respons JSON
        String jsonResponse = response.getBody();
        JSONObject obj = new JSONObject(jsonResponse);
        int rows = obj.getInt("rows");
        JSONArray results = obj.getJSONArray("results");
        
        // Menampilkan URL foto jika ada
        if (rows != 0) {
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                if (item.has("mhs_foto") && !item.getString("mhs_foto").isEmpty()) {  
                    String photoUrl = item.getString("mhs_foto");
                    System.out.println("Photo URL: " + photoUrl);
                } else {
                    System.out.println("No photo available for student ID: " + item.getString("mhs_id")); // contoh ID
                }
            }
        }
    }
}
