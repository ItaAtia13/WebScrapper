import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main   {
    public static void main(String[] args) {
        String url="https://lite.ip2location.com";
        try {
            Document document= Jsoup.connect("https://lite.ip2location.com/ip-address-ranges-by-country").get();
            Elements countryElements=document.getElementsByClass("card-columns");
            System.out.println(countryElements.text());
            for (Element element:countryElements) {
                String link=element.attr("href");
//                System.out.println("-------------");
                System.out.println(url+link);
                System.out.println(element.text()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
