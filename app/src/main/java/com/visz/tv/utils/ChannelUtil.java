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
    private static String IPV6_CHANNEL = "CCTV-1 综合,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001331/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CCTV-2 财经,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001332/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CCTV-3 综艺,http://[2409:8087:2001:20:2800:0:df6e:eb22]/ott.mobaibox.com/PLTV/4/224/3221228392/index.m3u8\n" +
            "CCTV-4 中文国际,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001333/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CCTV-5 体育,http://[2409:8087:2001:20:2800:0:df6e:eb22]/ott.mobaibox.com/PLTV/4/224/3221228502/index.m3u8\n" +
            "CCTV-5+ 体育赛事,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001334/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CCTV-6 电影,http://[2409:8087:2001:20:2800:0:df6e:eb14]:80/ott.mobaibox.com/PLTV/4/224/3221228123/index.m3u8\n" +
            "CCTV-7 国防军事,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225671/index.m3u8\n" +
            "CCTV-8 电视剧,http://[2409:8087:2001:20:2800:0:df6e:eb13]:80/ott.mobaibox.com/PLTV/4/224/3221227473/index.m3u8\n" +
            "CCTV-9 纪录,http://[2409:8087:2001:20:2800:0:df6e:eb13]:80/ott.mobaibox.com/PLTV/4/224/3221227614/index.m3u8\n" +
            "CCTV-10 科教,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225677/index.m3u8\n" +
            "CCTV-11 戏曲,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225517/index.m3u8\n" +
            "CCTV-12 社会与法,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225669/index.m3u8\n" +
            "CCTV-13 新闻,http://[2409:8087:2001:20:2800:0:df6e:eb17]:80/ott.mobaibox.com/PLTV/4/224/3221227387/index.m3u8\n" +
            "CCTV-14 少儿,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225674/index.m3u8\n" +
            "CCTV-15 音乐,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225513/index.m3u8\n" +
            "CCTV-16 奥林匹克,http://[2409:8087:2001:20:2800:0:df6e:eb17]:80/ott.mobaibox.com/PLTV/4/224/3221228112/index.m3u8\n" +
            "CCTV-17 农业农村,http://[2409:8087:2001:20:2800:0:df6e:eb17]:80/ott.mobaibox.com/PLTV/4/224/3221227592/index.m3u8\n" +
            "CCTV-4K 超高清,http://[2409:8087:2001:20:2800:0:df6e:eb13]/ott.mobaibox.com/PLTV/3/224/3221228228/index.m3u8\n" +
            "CCTV-8K 超高清,http://[2409:8087:2001:20:2800:0:df6e:eb03]/ott.mobaibox.com/PLTV/4/224/3221228165/index.m3u8\n" +
            "CETV-1 综合教育,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001063/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CETV-2 空中课堂,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001064/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CETV-3 教育服务,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001309/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CETV-4 职业教育,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001308/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "湖南卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001339/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "浙江卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001345/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "东方卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001336/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "北京卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001335/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "江苏卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001344/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "安徽卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001346/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "重庆卫视,http://[2409:8087:2001:20:2800:0:df6e:eb09]:80/ott.mobaibox.com/PLTV/4/224/3221228133/index.m3u8\n" +
            "东南卫视,http://[2409:8087:1a01:df::4077]/PLTV/88888888/224/3221225950/index.m3u8\n" +
            "甘肃卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001059/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "广东卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001337/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "广西卫视,http://[2409:8087:2001:20:2800:0:df6e:eb04]:80/ott.mobaibox.com/PLTV/4/224/3221228183/index.m3u8\n" +
            "贵州卫视,http://[2409:8087:2001:20:2800:0:df6e:eb09]:80/ott.mobaibox.com/PLTV/4/224/3221228136/index.m3u8\n" +
            "海南卫视,http://[2409:8087:2001:20:2800:0:df6e:eb09]:80/ott.mobaibox.com/PLTV/4/224/3221228139/index.m3u8\n" +
            "河北卫视,http://[2409:8087:2001:20:2800:0:df6e:eb04]:80/ott.mobaibox.com/PLTV/4/224/3221228106/index.m3u8\n" +
            "黑龙江卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001338/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "河南卫视,http://[2409:8087:2001:20:2800:0:df6e:eb16]:80/ott.mobaibox.com/PLTV/4/224/3221228221/index.m3u8\n" +
            "湖北卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001347/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "江西卫视,http://[2409:8087:2001:20:2800:0:df6e:eb17]:80/ott.mobaibox.com/PLTV/4/224/3221228109/index.m3u8\n" +
            "吉林卫视,http://[2409:8087:2001:20:2800:0:df6e:eb03]:80/ott.mobaibox.com/PLTV/4/224/3221228130/index.m3u8\n" +
            "辽宁卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001340/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "山东卫视,http://[2409:8087:2001:20:2800:0:df6e:eb11]:80/ott.mobaibox.com/PLTV/4/224/3221227517/index.m3u8\n" +
            "深圳卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001342/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "四川卫视,http://[2409:8087:2001:20:2800:0:df6e:eb08]:80/ott.mobaibox.com/PLTV/4/224/3221228171/index.m3u8\n" +
            "天津卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001343/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "云南卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001046/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "新疆卫视,http://[2409:8087:2001:20:2800:0:df6e:eb0b]/wh7f454c46tw993550557_162751766/ott.mobaibox.com/PLTV/3/224/3221228290/index.m3u8?icpid=3&RTS=1668604377&from=40&popid=40&hms_devid=2038&prioritypopid=40&vqe=3\n" +
            "三沙卫视,http://[2409:8087:2001:20:2800:0:df6e:eb21]:80/ott.mobaibox.com/PLTV/4/224/3221228626/index.m3u8\n" +
            "青海卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001033/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001062/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "山西卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001038/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "西藏卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001302/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "内蒙古卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001035/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "宁夏卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001061/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "兵团卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001304/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "安多卫视,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001311/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "康巴卫视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001313/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "大湾区卫视,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001305/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "农林卫视,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001312/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "延边卫视,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001307/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "厦门卫视,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001306/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CHC高清电影,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002065/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CHC家庭影院,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002085/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CHC动作电影,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002055/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "求索纪录,http://[2409:8087:7001:20:1000::95]:6610/000000001000/6000000002000032052/index.m3u8?channel-id=wasusyt&Contentid=6000000002000032052&livemode=1&stbId=3\n" +
            "求索科学,http://[2409:8087:7001:20:1000::95]:6610/000000001000/6000000002000032344/index.m3u8?channel-id=wasusyt&Contentid=6000000002000032344&livemode=1&stbId=3\n" +
            "求索动物,http://[2409:8087:7001:20:1000::95]:6610/000000001000/6000000002000010046/index.m3u8?channel-id=wasusyt&Contentid=6000000002000010046&livemode=1&stbId=3\n" +
            "求索生活,http://[2409:8087:7001:20:1000::95]:6610/000000001000/6000000002000003382/index.m3u8?channel-id=wasusyt&Contentid=6000000002000003382&livemode=1&stbId=3\n" +
            "黑莓电影,http://[2409:8087:2001:20:2800:0:df6e:eb04]/ott.mobaibox.com/PLTV/3/224/3221225567/index.m3u8\n" +
            "黑莓动画,http://[2409:8087:1a01:df::7005]/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225662/index.m3u8\n" +
            "哒啵电竞,http://[2409:8087:7000:20::4]/dbiptv.sn.chinamobile.com/PLTV/88888888/224/3221226951/index.m3u8\n" +
            "哒啵赛事,http://[2409:8087:1a01:df::7005]/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225675/index.m3u8\n" +
            "乐游,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002157/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "纪实人文,http://[2409:8087:2001:20:2800:0:df6e:eb12]/wh7f454c46tw1293235676_155310184/ott.mobaibox.com/PLTV/3/224/3221227635/index.m3u8?icpid=3&RTS=1669704189&from=40&popid=40&hms_devid=2112&prioritypopid=40&vqe=3\n" +
            "纯享4K,http://[2409:8087:2001:20:2800:0:df6e:eb11]/ott.mobaibox.com/PLTV/3/224/3221228242/index.m3u8\n" +
            "风云剧场,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226950/index.m3u8\n" +
            "风云音乐,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226953/index.m3u8\n" +
            "第一剧场,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226959/index.m3u8\n" +
            "女性时尚,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226969/index.m3u8\n" +
            "风云足球,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226984/index.m3u8\n" +
            "兵器科技,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226975/index.m3u8\n" +
            "怀旧剧场,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226972/index.m3u8\n" +
            "世界地理,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226947/index.m3u8\n" +
            "文化精品,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226981/index.m3u8\n" +
            "央视台球,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226956/index.m3u8\n" +
            "高尔夫网球,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226978/index.m3u8\n" +
            "电视指南,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226987/index.m3u8\n" +
            "都市剧场,http://[2409:8087:7000:20::F]:80/dbiptv.sn.chinamobile.com/TVOD/88888890/224/3221226581/index.m3u8\n" +
            "金色学堂,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002675/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "哈哈炫动,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225657/index.m3u8\n" +
            "游戏风云,http://[2409:8087:7000:20::4]:80/dbiptv.sn.chinamobile.com/PLTV/88888888/224/3221226579/1.m3u8\n" +
            "欢笑剧场,http://[2409:8087:7000:20::4]:80/dbiptv.sn.chinamobile.com/PLTV/88888888/224/3221226582/1.m3u8\n" +
            "第一财经,http://[2409:8087:7001:20:2::3]:80/dbiptv.sn.chinamobile.com/PLTV/88888893/224/3221226966/index.m3u8\n" +
            "武术世界,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638078be5d654/index.m3u8?AuthInfo=toEYVdLfxymUP2l9NZpQI5%2BK6T7j%2FlRm%2BvbM9VO7bA0MVaBO3fffRWKM%2FzNpNYjRBAiSJylXYBAiox0V94ejMfwEtXXuH8b8M%2F%2FDznZB61s\n" +
            "文物宝库,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638078a346161/index.m3u8?AuthInfo=toEYVdLfxymUP2l9NZpQI5%2BK6T7j%2FlRm%2BvbM9VO7bA199v9hZiIrZ%2B5X675R6%2FGDjgcqn5UaJ6D814KM9%2FvF7QwvFfeEXMAK7LltfEC%2FKQQ\n" +
            "梨园,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN6380788ba7bed/index.m3u8?AuthInfo=toEYVdLfxymUP2l9NZpQI5%2BK6T7j%2FlRm%2BvbM9VO7bA0q1S1k1f36SqqriM0FZoFSAJRfCt8SS7X6sTRmXb81a8O4H%2FdroDKjLoDeaMQdyJQ\n" +
            "天元围棋,http://[2409:8087:2001:20:2800:0:df6e:eb23]/ott.mobaibox.com/PLTV/3/224/3221228513/index.m3u8\n" +
            "弈坛春秋,http://[2409:8087:7004:20:1000::22]:6610/yinhe/2/ch00000090990000001322/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "劲爆体育,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001326/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "凤凰中文,http://[2409:8087:2001:20:2800:0:df6e:eb22]:80/ott.mobaibox.com/PLTV/3/224/3221228527/1.m3u8\n" +
            "凤凰资讯,http://[2409:8087:2001:20:2800:0:df6e:eb19]:80/ott.mobaibox.com/PLTV/3/224/3221228524/1.m3u8\n" +
            "凤凰香港,http://[2409:8087:2001:20:2800:0:df6e:eb22]:80/ott.mobaibox.com/PLTV/1/224/3221228530/1.m3u8\n" +
            "翡翠台,https://edge6a.v2h-cdn.com/jade/jade.stream/chunklist.m3u8\n" +
            "明珠台,http://198.16.100.90:8278/pearl_twn/playlist.m3u8?tid=MC6C7041327570413275&ct=19226&tsum=681972baf66d69caf7cccc92a90e5aad\n" +
            "J2,http://198.16.100.90:8278/j2_twn/playlist.m3u8?tid=MFCF3274854132748541&ct=19254&tsum=699466c18aac56202ad1d2843fd21032\n" +
            "NHK World,https://nhkwlive-ojp.akamaized.net/hls/live/2003459/nhkwlive-ojp-en/index_4M.m3u8\n" +
            "CNA,https://d2e1asnsl7br7b.cloudfront.net/7782e205e72f43aeb4a48ec97f66ebbe/index_5.m3u8\n" +
            "AlJazeera,http://live-hls-web-aje.getaj.net/AJE/01.m3u8\n" +
            "Arirang TV,https://amdlive-ch01-ctnd-com.akamaized.net/arirang_1ch/smil:arirang_1ch.smil/chunklist_b3256000_sleng.m3u8\n" +
            "RT News,https://rt-glb.rttv.com/dvr/rtnews/playlist_4500Kb.m3u8\n" +
            "RT Documentary,https://rt-rtd.rttv.com/live/rtdoc/playlist_4500Kb.m3u8\n" +
            "CGTN,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001024/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CGTN Documentary,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002905/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CGTN Français,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002827/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CGTN Русский,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002883/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CGTN Español,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002716/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "CGTN العربية,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002826/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "茶频道,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002305/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "快乐垂钓,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000002264/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "金鹰纪实,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001699/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "金鹰卡通,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN6000057BESTVSMGSMG/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUTWDyIgRkFJBAHMhA2ayG0T0ZQXwaiVktPr6ILPKXqwOw2VoxnxHvBSRSzCCC3szSwIgrD0rUpRXeaqChwLXCf0\n" +
            "湖南经视,http://175.0.51.26:8899/tsfile/live/1042_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南都市,http://175.0.51.26:8899/tsfile/live/1044_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南爱晚,http://175.0.51.26:8899/tsfile/live/0012_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南国际,http://175.0.51.26:8899/tsfile/live/1002_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南娱乐,http://175.0.51.26:8899/tsfile/live/1001_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南电影,http://175.0.51.26:8899/tsfile/live/1000_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖南电视剧,http://175.0.51.26:8899/tsfile/live/1043_1.m3u8?key=txiptv&playlive=1&authid=0\n" +
            "湖北综合,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638072216f7da/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNLx3R3LdIBdA5q61lut1LwZcw4SC2tJubc%2Bq5t%2F61jvWCtjrJL0x%2BHncPgdmcbBW9I\n" +
            "湖北影视,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN6380728abccd2/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNKU1xiiKGJYFBZK9RpEkaggogtHJZW6nJQqLppbEYE%2BqJ9kJW%2FMvKldfS2dCii9VSI\n" +
            "湖北教育,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638072a6cbbf6/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNKTFQuOKNd5i0HMa3ydMWIQ6kJ2fBJGJ77BLTXK72CqUYQsX3ZguYt0954s9hfZTfc\n" +
            "湖北生活,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638072c62e405/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNKJPodw5BcDirSOw7egztI5bUvJjqPzSNAhrA37GrbRBIAjGQpzP0QmgsmuHnQyd2c\n" +
            "湖北公共,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN6380726d0ee3b/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNKgjpyhD%2FJd6SZjkcHAKXbsjwotAY57dianC4%2ByY7artm7J7F4GFO6G99D0bYMJRxI\n" +
            "湖北经视,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN63807203543e3/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNLVCQ3pDe4nBySab%2B3xPL123aTSCRugzF2ABe36IIJw2evZbYdWUo0ffUw53bflDgU\n" +
            "湖北垄上,http://[2409:8087:4c0a:22:1::11]:6410/170000001115/UmaiCHAN638072463c333/index.m3u8?AuthInfo=9kOOdBn7MFF%2F2bWjKgahUUAdKgLqNB5aOAUhcp5CeNL%2Be1h4FfA5QqwJ7IPOz3p2qIS7djSxm17Htd%2FckZkm%2FVqsKHEJFJt26ju9iibzMEo\n" +
            "浙江钱江都市,https://ali-m-l.cztv.com/channels/lantian/channel002/1080p.m3u8\n" +
            "浙江经济生活,https://ali-m-l.cztv.com/channels/lantian/channel003/1080p.m3u8\n" +
            "浙江科教影视,https://ali-m-l.cztv.com/channels/lantian/channel004/1080p.m3u8\n" +
            "浙江民生休闲,https://ali-m-l.cztv.com/channels/lantian/channel006/1080p.m3u8\n" +
            "浙江公共新闻,https://ali-m-l.cztv.com/channels/lantian/channel007/1080p.m3u8\n" +
            "浙江少儿,https://ali-m-l.cztv.com/channels/lantian/channel008/1080p.m3u8\n" +
            "浙江国际,https://ali-m-l.cztv.com/channels/lantian/channel010/1080p.m3u8\n" +
            "纪实科教,http://[2409:8087:7000:20::4]:80/dbiptv.sn.chinamobile.com/PLTV/88888888/224/3221226232/1.m3u8\n" +
            "卡酷少儿,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225654/index.m3u8\n" +
            "陕西新闻资讯,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001719/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西都市青春,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001593/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西生活,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001729/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西影视,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001730/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西公共,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001739/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西体育休闲,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001594/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "陕西西部电影,http://[2409:8087:7000:20:1000::22]:6060/yinhe/2/ch00000090990000001749/index.m3u8?virtualDomain=yinhe.live_hls.zte.com\n" +
            "NewTV军事评论,http://[2409:8087:2001:20:2800:0:df6e:eb18]:80/wh7f454c46tw3373254713_-1111569189/ott.mobaibox.com/PLTV/3/224/3221227544/index.m3u8\n" +
            "NewTV军旅剧场,http://[2409:8087:2001:20:2800:0:df6e:eb06]:80/wh7f454c46tw1807611386_-262631246/ott.mobaibox.com/PLTV/3/224/3221227603/index.m3u8\n" +
            "NewTV家庭剧场,http://[2409:8087:2001:20:2800:0:df6e:eb06]:80/wh7f454c46tw3441504651_1879058580/ott.mobaibox.com/PLTV/3/224/3221227600/index.m3u8\n" +
            "NewTV中国功夫,http://[2409:8087:2001:20:2800:0:df6e:eb19]:80/wh7f454c46tw1934355864_2070028581/ott.mobaibox.com/PLTV/3/224/3221227530/index.m3u8\n" +
            "NewTV东北热剧,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225741/index.m3u8\n" +
            "NewTV惊悚悬疑,http://[2409:8087:2001:20:2800:0:df6e:eb18]/wh7f454c46tw2482771376_951312812/ott.mobaibox.com/PLTV/3/224/3221227536/index.m3u8\n" +
            "NewTV明星大片,http://[2409:8087:2001:20:2800:0:df6e:eb18]:80/wh7f454c46tw2856695654_946966165/ott.mobaibox.com/PLTV/3/224/3221227594/index.m3u8\n" +
            "NewTV欢乐剧场,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225742/index.m3u8\n" +
            "NewTV潮妈辣婆,http://[2409:8087:2001:20:2800:0:df6e:eb19]:80/wh7f454c46tw1705588260_46164741/ott.mobaibox.com/PLTV/3/224/3221227527/index.m3u8\n" +
            "NewTV炫舞未来,http://[2409:8087:2001:20:2800:0:df6e:eb09]:80/wh7f454c46tw2582593423_1721070986/ott.mobaibox.com/PLTV/3/224/3221227475/index.m3u8\n" +
            "NewTV精品体育,http://[2409:8087:2001:20:2800:0:df6e:eb1b]:80/wh7f454c46tw2797725038_-2054878207/ott.mobaibox.com/PLTV/3/224/3221227615/index.m3u8\n" +
            "NewTV精品大剧,http://[2409:8087:2001:20:2800:0:df6e:eb1a]:80/wh7f454c46tw2817459161_-1430429466/ott.mobaibox.com/PLTV/3/224/3221227618/index.m3u8\n" +
            "NewTV超级电影,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225717/index.m3u8\n" +
            "NewTV超级综艺,http://[2409:8087:7000:20::4]:80/dbiptv.sn.chinamobile.com/PLTV/88888888/224/3221226009/index.m3u8\n" +
            "NewTV超级电视剧,http://[2409:8087:1a01:df::7005]:80/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225716/index.m3u8\n";
    private static String IPV4_CHANNEL = "CCTV1 综合,http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226231/index.m3u8\n" +
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
//            return parseM3U2ChannelList(readM3UFile(context));
            return parseString2ChannelList(IPV6_CHANNEL);
        } else {
            return parseString2ChannelList(IPV4_CHANNEL);
        }
    }

    public static List<Channel> parseString2ChannelList(String channelData) {
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
                    LogUtil.i(channel.name.replace("\n", " ") + "," + channel.url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channels;
    }
}
