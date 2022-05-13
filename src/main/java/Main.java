import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    ArrayList<String> ipAddresses;


    public static void main(String[] args) {
        String url = "https://www.xmyip.com";
        boolean found = false;
//        //scan the user input
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextInt();
        String userInput = "37.0.172.1";
        String firstByte = userInput.split("\\.")[0];
        String secByte = userInput.split("\\.")[1];
        String bothBytes = firstByte + "." + secByte;
        System.out.println(bothBytes);


        try {
            Document document = Jsoup.connect("https://www.xmyip.com/ip-blocks-countries").get();
            Elements countryElements = document.getElementsByClass("divTableCell");

            for (Element country : countryElements) {
                Elements aTags = country.getElementsByTag("a");
                String link = aTags.attr("href");
                System.out.println("-------------");
                System.out.println(country.text());
                System.out.println(url + link);
                //enter to each countryCard
                Document allCountryIp = Jsoup.connect(url + link).get();
                Elements ipAddressRange = allCountryIp.getElementsByClass("divTableCell");
                String string = (Arrays.toString(
                        (ipAddressRange.select("a").text().replaceAll("[^0-9?!\\.]", " ")).split("\\s+")));

//                String trimmedString=string.trim();


                ArrayList<String> ipAddresses = new ArrayList<>((Arrays.asList(string.split(","))));
                ipAddresses.add(string);


                //test
                System.out.println("the whole IP addresses size is: " + ipAddresses.size() + " and there are they: " +
                        ipAddresses);

                for (String str : ipAddresses) {
                    //trim each ip address
                    str = str.trim();
                    if (str.startsWith(bothBytes)) {
                        System.out.println("This ip belongs to: " + (country.text()));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("we cant find this ip address");
                    //stop searching when we found the IP Country
                }else {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
