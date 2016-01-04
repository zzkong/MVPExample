package lico.example.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import lico.example.bean.JcodeInfo;

/**
 * Created by zzk on 15/12/31.
 */
public class DataUtil {

    public static List<JcodeInfo> getJcodeData(String tid, int page) {
        List<JcodeInfo> mList = new ArrayList<>();
        try {
            String url = "http://www.jcodecraeer.com/plus/list.php?tid=" + tid + "&TotalResult=1415&PageNo=" + page;
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("div.archive-list-item");
            for (Element e : links) {
                JcodeInfo info = new JcodeInfo();
                info.detailUrl = e.select("a").attr("href");
                info.imageUrl = "http://www.jcodecraeer.com"+e.select("img").attr("src");
                info.title = e.select("div.post-intro").select("a[href]").attr("title");
                mList.add(info);
            }
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
