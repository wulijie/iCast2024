package com.visz.tv.utils;

import android.content.Context;
import android.content.res.Resources;

import com.visz.tv.BuildConfig;
import com.visz.tv.Channel;
import com.visz.tv.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ChannelUtil {
    private static String channelData = "CCTV1 综合,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226231/index.m3u8\n" +
            "CCTV2 财经,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226195/index.m3u8\n" +
            "CCTV3 综艺,0\n" +
            "CCTV4 中文国际,http://39.134.24.161/dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226191/index.m3u8\n" +
            "CCTV5 体育,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226395/index.m3u8\n" +
            "CCTV6 电影,0\n" +
            "CCTV7 国防军事,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226192/index.m3u8\n" +
            "CCTV8 电视剧,0\n" +
            "CCTV9 记录,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226197/index.m3u8\n" +
            "CCTV10 科教,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226189/index.m3u8\n" +
            "CCTV11 戏曲,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226240/index.m3u8\n" +
            "CCTV12 社会与法,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226190/index.m3u8\n" +
            "CCTV13 新闻,https://live-play.cctvnews.cctv.com/cctv/merge_cctv13.m3u8     \n" +
            "CCTV14 少儿,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226193/index.m3u8\n" +
            "CCTV15 音乐,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221225785/index.m3u8\n" +
            "CCTV16 奥林匹克,http://39.134.24.162/dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226921/index.m3u8\n" +
            "CCTV17 农业农村,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226198/index.m3u8\n" +
            "CCTV5+ 体育赛事,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226221/index.m3u8\n" +
            "CCTV4K 超高清,0\n" +
            "CCTV8K 超高清,0\n" +
            "风云剧场,0\n" +
            "第一剧场,0\n" +
            "怀旧剧场,0\n" +
            "世界地理,0\n" +
            "风云音乐,0\n" +
            "兵器科技,0\n" +
            "风云足球,0\n" +
            "高尔夫网球,0\n" +
            "女性时尚,0\n" +
            "央视文化精品,0\n" +
            "央视台球,0\n" +
            "电视指南,0\n" +
            "卫生健康,0\n" +
            "东方卫视,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226217/index.m3u8\n" +
            "湖南卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226307/index.m3u8\n" +
            "湖北卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226477/index.m3u8\n" +
            "辽宁卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226546/index.m3u8\n" +
            "江苏卫视,http://39.134.24.166/dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226200/index.m3u8\n" +
            "江西卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226344/index.m3u8\n" +
            "山东卫视,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226209/index.m3u8\n" +
            "广东卫视,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226216/index.m3u8\n" +
            "广西卫视,http://live.gxrb.com.cn/tv/gxtvlive03/index.m3u8\n" +
            "重庆卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226409/index.m3u8\n" +
            "河南卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226480/index.m3u8\n" +
            "河北卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226406/index.m3u8\n" +
            "贵州卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226474/index.m3u8\n" +
            "北京卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225728/index.m3u8\n" +
            "黑龙江卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226327/index.m3u8\n" +
            "浙江卫视,http://hw-m-l.cztv.com/channels/lantian/channel01/1080p.m3u8      \n" +
            "安徽卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226391/index.m3u8\n" +
            "深圳卫视,http://39.134.24.166/dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226205/index.m3u8\n" +
            "四川卫视,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221225768/index.m3u8\n" +
            "东南卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226341/index.m3u8\n" +
            "海南卫视,http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221226465/index.m3u8\n" +
            "天津卫视,0\n" +
            "新疆卫视,0\n" +
            "CGTN,http://live.cgtn.com/1000/prog_index.m3u8\n" +
            "CGTN 法语频道,https://livefr.cgtn.com/1000f/prog_index.m3u8\n" +
            "CGTN 俄语频道,http://liveru.cgtn.com/1000r/prog_index.m3u8\n" +
            "CGTN 阿拉伯语频道,http://livear.cgtn.com/1000a/prog_index.m3u8\n" +
            "CGTN 西班牙语频道,http://livees.cgtn.com/500e/prog_index.m3u8\n" +
            "CGTN 纪录频道,https://livedoc.cgtn.com/500d/prog_index.m3u8";

    public static List<Channel> getChannelList(Context context) {
        if (BuildConfig.IS_IPV6) {
            return parseM3U2ChannelList(readM3UFile(context));
        } else {
            return parseString2ChannelList();
        }
    }

    public static List<Channel> parseString2ChannelList() {
        String[] ss = channelData.split("\n");
        List<Channel> channels = new ArrayList<>();
        int channelNum = 1;
        for (String s : ss) {
            String[] cc = s.split(",");
            if (cc.length >= 2) {
                if (cc[1].contains("m3u8")) {
                    Channel channel = new Channel();
                    channel.num = String.format("%03d", channelNum);
                    channelNum++;
                    channel.name = cc[0];
                    channel.name = channel.name.replace(" ", "\n");//为了换行好看
                    channel.url = cc[1];
                    channels.add(channel);
                }
            }
        }
        return channels;
    }

    public static String readM3UFile(Context context) {
        StringBuilder content = new StringBuilder();
        Resources resources = context.getResources();
        try (InputStream is = resources.openRawResource(R.raw.ipv6_channels);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static List<Channel> parseM3U2ChannelList(String m3uContent) {
        List<Channel> channels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(m3uContent))) {
            String line;
            Channel channel = new Channel();
            int channelNum = 1;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#EXTINF")) {
                    channel = new Channel();
                    String[] parts = line.split(",", 2);
                    String infoPart = parts[0];
                    String[] attributes = infoPart.split(" ");
                    for (String attribute : attributes) {
                        if (attribute.startsWith("tvg-id")) {
                            channel.tvgId = attribute.split("=")[1].replaceAll("\"", "");
                        } else if (attribute.startsWith("tvg-name")) {
                            channel.tvgName = attribute.split("=")[1].replaceAll("\"", "");
                        } else if (attribute.startsWith("tvg-logo")) {
                            channel.tvgLogo = attribute.split("=")[1].replaceAll("\"", "");
                        } else if (attribute.startsWith("group-title")) {
                            channel.groupTitle = attribute.split("=")[1].replaceAll("\"", "");
                        }
                    }
                    channel.name = parts[1];
                    channel.name = channel.name.replace(" ", "\n");//为了换行好看
                } else if (line.startsWith("http")) {
                    channel.url = line;
                    channel.num = String.format("%03d", channelNum);
                    channelNum++;
                    channels.add(channel);
                    LogUtil.i(channel.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channels;
    }
}
