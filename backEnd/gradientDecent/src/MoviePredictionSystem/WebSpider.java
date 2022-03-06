package MoviePredictionSystem;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class WebSpider {

    private String IMDBLink = "https://www.imdb.com/?ref_=nv_home";
    private CloseableHttpClient client = HttpClients.createDefault();

    public String[] getFilmName(String type){
        return null;
    }

    public ArrayList<String> getMovieInfoByName(String name,String releaseDate) throws IOException {
        String movieName = name.replace(" ","+");
        String demandName = "https://www.imdb.com/find?q="+movieName+"&ref_=nv_sr_sm";
        CloseableHttpClient foo = HttpClients.createDefault();
        HttpGet get = new HttpGet(demandName);
        CloseableHttpResponse response = foo.execute(get);
        StatusLine line = response.getStatusLine();
        System.out.println(line.getStatusCode());

        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity,"utf-8");
        response.close();
        foo.close();
        //System.out.println(html);

        System.out.println("start to initialize the jsoup class-------------------");
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("result_text");
        //System.out.println(elements);


        Element theEle = null;
        for(Element ele:elements){
            if(ele.toString().contains("title")&&ele.toString().contains(releaseDate)){
                theEle = ele;
                break;
            }
        }
        if(theEle == null){
            throw new IllegalAccessError("the movie not found");
        }
        System.out.println(theEle);
        String[] titleCode = theEle.toString().split("/");
        System.out.println(Arrays.toString(titleCode));

        String movieDemand = "https://www.imdb.com/title/"+titleCode[2]+"/?ref_=fn_al_tt_1";
        System.out.println(movieDemand);
        System.out.println("Stat to test inside the movie link-------- ");
        return this.helpGetFilmInfo(movieDemand);

        //return null;
    }

    private ArrayList<String> helpGetFilmInfo(String demand) throws IOException {
        CloseableHttpClient foo = HttpClients.createDefault();
        HttpGet get = new HttpGet(demand);
        CloseableHttpResponse response = foo.execute(get);
        StatusLine line = response.getStatusLine();
        System.out.println(line.getStatusCode());

        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity,"utf-8");
        response.close();
        foo.close();
        //System.out.println(html);

        Document document = Jsoup.parse(html);
        String[] elements = document.getElementsByAttribute("itemprop").text().split(" ");
       // System.out.println(Arrays.toString(elements));

        ArrayList<String> result = new ArrayList<>();
        result.add("Rating: "+elements[0]);
        result.add("num of rating: "+elements[1]);
        result.add("num of review: "+elements[2]);
        result.add("num of critic: "+elements[4]);




        return result;
    }

    public Map<String,String[]> getAllRentFilm(Date date){
        return null;
    }

    public Integer getFilmCommentNum(String name,String releaseDate){
        return null;
    }

    public Integer getFilmRating(String name){
        return null;
    }

}
