package com.sail.simonli.server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CountryMap {
	
	private static HashMap<String,String>  cityMap =new HashMap<String,String>();
	private static HashMap<String,String>  provinceMap =new HashMap<String,String>();
	
	static {
		cityMap.put("440200", "shaoguanshi");
		cityMap.put("440300", "shenzhenshi");
		cityMap.put("440400", "zhuhaishi");
		cityMap.put("440500", "shantoushi");
		cityMap.put("440600", "foshanshi");
		cityMap.put("440700", "jiangmenshi");
		cityMap.put("440800", "zhanjiangshi");
		cityMap.put("440900", "maomingshi");
		cityMap.put("441200", "zhaoqingshi");
		cityMap.put("441300", "huizhoushi");
		cityMap.put("441400", "meizhoushi");
		cityMap.put("441500", "shanweishi");
		cityMap.put("441600", "heyuanshi");
		cityMap.put("441700", "yangjiangshi");
		cityMap.put("441800", "qingyuanshi");
		cityMap.put("441900", "dongguanshi");
		cityMap.put("442000", "zhongshanshi");
		cityMap.put("445100", "chaozhoushi");
		cityMap.put("445200", "jieyangshi");
		cityMap.put("445300", "yunfushi");
		cityMap.put("310000", "shanghaishi");
		cityMap.put("320100", "nanjingshi");
		cityMap.put("320200", "wuxishi");
		cityMap.put("320300", "xuzhoushi");
		cityMap.put("320400", "changzhoushi");
		cityMap.put("320500", "suzhoushi");
		cityMap.put("320600", "nantongshi");
		cityMap.put("320700", "lianyungangshi");
		cityMap.put("320800", "huaianshi");
		cityMap.put("320900", "yanchengshi");
		cityMap.put("321000", "yangzhoushi");
		cityMap.put("321100", "zhenjiangshi");
		cityMap.put("321200", "taizhoushi");
		cityMap.put("321300", "suqianshi");
		cityMap.put("330100", "hangzhoushi");
		cityMap.put("330200", "ningboshi");
		cityMap.put("330300", "wenzhoushi");
		cityMap.put("330400", "jiaxingshi");
		cityMap.put("330500", "huzhoushi");
		cityMap.put("330600", "shaoxingshi");
		cityMap.put("330700", "jinhuashi");
		cityMap.put("330800", "quzhoushi");
		cityMap.put("330900", "zhoushanshi");
		cityMap.put("331000", "taizhou");
		cityMap.put("331100", "lishuishi");
		cityMap.put("410100", "zhengzhoushi");
		cityMap.put("410200", "kaifengshi");
		cityMap.put("410300", "luoyangshi");
		cityMap.put("410400", "pingdingshanshi");
		cityMap.put("410500", "anyangshi");
		cityMap.put("410600", "hebishi");
		cityMap.put("410700", "xinxiangshi");
		cityMap.put("410800", "jiaozuoshi");
		cityMap.put("410900", "puyangshi");
		cityMap.put("411000", "xuchangshi");
		cityMap.put("411100", "luoheshi");
		cityMap.put("411200", "sanmenxiashi");
		cityMap.put("411300", "nanyangshi");
		cityMap.put("411400", "shangqiushi");
		cityMap.put("411500", "xinyangshi");
		cityMap.put("411600", "zhoukoushi");
		cityMap.put("411700", "zhumadianshi");
		cityMap.put("419001", "jiyuanshi");
		cityMap.put("110000", "beijingshi");
		cityMap.put("120000", "tianjinshi");
		cityMap.put("370100", "jinanshi");
		cityMap.put("370200", "qingdaoshi");
		cityMap.put("370300", "ziboshi");
		cityMap.put("370400", "zaozhuangshi");
		cityMap.put("370500", "dongyingshi");
		cityMap.put("370600", "yantaishi");
		cityMap.put("370700", "weifangshi");
		cityMap.put("370800", "jiningshi");
		cityMap.put("370900", "taianshi");
		cityMap.put("371000", "weihaishi");
		cityMap.put("371100", "rizhaoshi");
		cityMap.put("371200", "laiwushi");
		cityMap.put("371300", "linyishi");
		cityMap.put("371400", "dezhoushi");
		cityMap.put("371500", "liaochengshi");
		cityMap.put("371600", "binzhoushi");
		cityMap.put("371700", "hezeshi");
		cityMap.put("140100", "taiyuanshi");
		cityMap.put("140200", "datongshi");
		cityMap.put("140300", "yangquanshi");
		cityMap.put("140400", "changzhishi");
		cityMap.put("140500", "jinchengshi");
		cityMap.put("140600", "shuozhoushi");
		cityMap.put("140700", "jinzhongshi");
		cityMap.put("140800", "yunchengshi");
		cityMap.put("140900", "xinzhoushi");
		cityMap.put("141000", "linfenshi");
		cityMap.put("141100", "lvliangshi");
		cityMap.put("130100", "shijiazhuangshi");
		cityMap.put("130200", "tangshanshi");
		cityMap.put("130300", "qinhuangdaoshi");
		cityMap.put("130400", "handanshi");
		cityMap.put("130500", "xingtaishi");
		cityMap.put("130600", "baodingshi");
		cityMap.put("130700", "zhangjiakoushi");
		cityMap.put("130800", "chengdeshi");
		cityMap.put("130900", "cangzhoushi");
		cityMap.put("131000", "langfangshi");
		cityMap.put("131100", "hengshuishi");
		cityMap.put("420100", "wuhanshi");
		cityMap.put("420200", "huangshishi");
		cityMap.put("420300", "shiyanshi");
		cityMap.put("420500", "yichangshi");
		cityMap.put("420600", "xiangfanshi");
		cityMap.put("420700", "ezhoushi");
		cityMap.put("420800", "jingmenshi");
		cityMap.put("420900", "xiaoganshi");
		cityMap.put("421000", "jingzhoushi");
		cityMap.put("421100", "huanggangshi");
		cityMap.put("421200", "xianningshi");
		cityMap.put("421300", "suizhoushi");
		cityMap.put("422800", "enshizhou");
		cityMap.put("429004", "xiantaoshi");
		cityMap.put("429005", "qianjiangshi");
		cityMap.put("429006", "tianmenshi");
		cityMap.put("429021", "shennongjialinqu");
		cityMap.put("210100", "shenyangshi");
		cityMap.put("210200", "dalianshi");
		cityMap.put("210300", "anshanshi");
		cityMap.put("210400", "fushunshi");
		cityMap.put("210500", "benxishi");
		cityMap.put("210600", "dandongshi");
		cityMap.put("210700", "jinzhoushi");
		cityMap.put("210800", "yingkoushi");
		cityMap.put("210900", "fuxinshi");
		cityMap.put("211000", "liaoyangshi");
		cityMap.put("211100", "panjinshi");
		cityMap.put("211200", "tielingshi");
		cityMap.put("211300", "chaoyangshi");
		cityMap.put("211400", "huludaoshi");
		cityMap.put("510100", "cehngdushi");
		cityMap.put("510300", "zigongshi");
		cityMap.put("510400", "panzhihuashi");
		cityMap.put("510500", "luzhoushi");
		cityMap.put("510600", "deyangshi");
		cityMap.put("510700", "mianyangshi");
		cityMap.put("510800", "guangyuanshi");
		cityMap.put("510900", "suiningshi");
		cityMap.put("511000", "neijiangshi");
		cityMap.put("511100", "leshanshi");
		cityMap.put("511300", "nanchongshi");
		cityMap.put("511400", "meishanshi");
		cityMap.put("511500", "yibinshi");
		cityMap.put("511600", "guanganshi");
		cityMap.put("511700", "dazhosuhi");
		cityMap.put("511800", "yaanshi");
		cityMap.put("511900", "bazhongshi");
		cityMap.put("512000", "ziyangshi");
		cityMap.put("513200", "abazangzuqiangzuzizh");
		cityMap.put("513300", "ganzizangzuzizhizhou");
		cityMap.put("513400", "liangshanyizuzizhizh");
		cityMap.put("350100", "fuzhoushi");
		cityMap.put("350200", "xiamenshi");
		cityMap.put("350300", "putianshi");
		cityMap.put("350400", "sanmingshi");
		cityMap.put("350500", "quanzhoushi");
		cityMap.put("350600", "zhangzhoushi");
		cityMap.put("350700", "nanpingshi");
		cityMap.put("350800", "longyanshi");
		cityMap.put("350900", "ningdeshi");
		cityMap.put("610100", "xianshi");
		cityMap.put("610200", "tongchuanshi");
		cityMap.put("610300", "baojishi");
		cityMap.put("610400", "xianyangshi");
		cityMap.put("610500", "weinanshi");
		cityMap.put("610600", "yananshi");
		cityMap.put("610700", "hanzhongshi");
		cityMap.put("610800", "yulinshi");
		cityMap.put("610900", "ankangshi");
		cityMap.put("611000", "shangluoshi");
		cityMap.put("620100", "lanzhoushi");
		cityMap.put("620200", "jiayuguanshi");
		cityMap.put("620300", "jinchangshi");
		cityMap.put("620400", "baiyinshi");
		cityMap.put("620500", "tianshuishi");
		cityMap.put("620600", "wuweishi");
		cityMap.put("620700", "zhangyeshi");
		cityMap.put("620800", "pingliangshi");
		cityMap.put("620900", "jiuquanshi");
		cityMap.put("621000", "qingyangshi");
		cityMap.put("621100", "dingxishi");
		cityMap.put("621200", "longnanshi");
		cityMap.put("622900", "linxiahuizuzizhizhou");
		cityMap.put("623000", "gannanzangzuzizhizho");
		cityMap.put("340100", "hefeishi");
		cityMap.put("340200", "wuhushi");
		cityMap.put("340300", "bengbushi");
		cityMap.put("340400", "huainanshi");
		cityMap.put("340500", "maanshanshi");
		cityMap.put("340600", "huaibeishi");
		cityMap.put("340700", "tonglingshi");
		cityMap.put("340800", "anqingshi");
		cityMap.put("341000", "huangshanshi");
		cityMap.put("341100", "chuzhoushi");
		cityMap.put("341200", "fuyangshi");
		cityMap.put("341300", "suzhou");
		cityMap.put("340181", "chaohushi");
		cityMap.put("341500", "liuanshi");
		cityMap.put("341600", "bozhoushi");
		cityMap.put("341700", "chizhoushi");
		cityMap.put("341800", "xuanchengshi");
		cityMap.put("430100", "changshashi");
		cityMap.put("430200", "zhuzhoushi");
		cityMap.put("430300", "xaingtanshi");
		cityMap.put("430400", "hengyangshi");
		cityMap.put("430500", "shaoyangshi");
		cityMap.put("430600", "yueyangshi");
		cityMap.put("430700", "changdeshi");
		cityMap.put("430800", "zhangjiajieshi");
		cityMap.put("430900", "yiyangshi");
		cityMap.put("431000", "chenzhoushi");
		cityMap.put("431100", "yongzhoushi");
		cityMap.put("431200", "huaihuashi");
		cityMap.put("431300", "loudishi");
		cityMap.put("433100", "xaingxitujiazumiaozu");
		cityMap.put("500000", "chongqingshi");
		cityMap.put("220100", "changchunshi");
		cityMap.put("220200", "jilinshi");
		cityMap.put("220300", "sipingshi");
		cityMap.put("220400", "liaoyuanshi");
		cityMap.put("220500", "tonghuashi");
		cityMap.put("220600", "baishanshi");
		cityMap.put("220700", "songyuanshi");
		cityMap.put("220800", "baichengshi");
		cityMap.put("222400", "yanbianchaoxianzuziz");
		cityMap.put("230100", "haerbinshi");
		cityMap.put("230200", "qiqihaershi");
		cityMap.put("230300", "jixishi");
		cityMap.put("230400", "hegangshi");
		cityMap.put("230500", "shuangyashanshi");
		cityMap.put("230600", "daqingshi");
		cityMap.put("230700", "yichunshi");
		cityMap.put("230800", "jiamusishi");
		cityMap.put("230900", "qitaiheshi");
		cityMap.put("231000", "mudanjiangshi");
		cityMap.put("231100", "heiheshi");
		cityMap.put("231200", "suihuashi");
		cityMap.put("232700", "daxinganlingdiqu");
		cityMap.put("360100", "nanchangshi");
		cityMap.put("360200", "jingdezhenshi");
		cityMap.put("360300", "pingxiangshi");
		cityMap.put("360400", "jiujiangshi");
		cityMap.put("360500", "xinyushi");
		cityMap.put("360600", "yingtanshi");
		cityMap.put("360700", "ganzhoushi");
		cityMap.put("360800", "jianshi");
		cityMap.put("360900", "yichun");
		cityMap.put("361000", "fuzhou");
		cityMap.put("361100", "shangraoshi");
		cityMap.put("520100", "guiyangshi");
		cityMap.put("520200", "liupanshuishi");
		cityMap.put("520300", "zunyishi");
		cityMap.put("520400", "anshunshi");
		cityMap.put("520600", "tongrendiqu");
		cityMap.put("522300", "qianxinnanbuyizumiao");
		cityMap.put("520500", "bijiediqu");
		cityMap.put("522600", "qianxinanmiaozudongz");
		cityMap.put("522700", "qiannanbuyizumiaozuz");
		cityMap.put("530100", "kunmingshi");
		cityMap.put("530300", "qujingshi");
		cityMap.put("530400", "yuxishi");
		cityMap.put("530500", "baoshanshi");
		cityMap.put("530600", "zhaotongshi");
		cityMap.put("530700", "lijiangshi");
		cityMap.put("530800", "puershi");
		cityMap.put("530900", "lincangshi");
		cityMap.put("532300", "chuxiongyizuzizhizho");
		cityMap.put("532500", "honghehanizuyizuzizh");
		cityMap.put("532600", "wenshanzhuangzumiaoz");
		cityMap.put("532800", "xishuangbannadaizuzi");
		cityMap.put("532900", "dalibaizuzizhizhou");
		cityMap.put("533100", "dehong");
		cityMap.put("533300", "nujiang");
		cityMap.put("533400", "diqing");
		cityMap.put("450100", "nanning");
		cityMap.put("450200", "liuzhou");
		cityMap.put("450300", "guilin");
		cityMap.put("450400", "wuzhou");
		cityMap.put("450500", "beihai");
		cityMap.put("450600", "fangchenggang");
		cityMap.put("450700", "qinzhou");
		cityMap.put("450800", "guigang");
		cityMap.put("450900", "yulin");
		cityMap.put("451000", "baise");
		cityMap.put("451100", "hezhou");
		cityMap.put("451200", "hechi");
		cityMap.put("451300", "laibin");
		cityMap.put("451400", "chongzuo");
		cityMap.put("460100", "haikou");
		cityMap.put("460200", "sanya");
		cityMap.put("469001", "wuzhishan");
		cityMap.put("469002", "qionghai");
		cityMap.put("469003", "danzhou");
		cityMap.put("469005", "wenchang");
		cityMap.put("469006", "wanning");
		cityMap.put("469007", "dongfang");
		cityMap.put("469021", "dingan");
		cityMap.put("469022", "tunchang");
		cityMap.put("469023", "chengmai");
		cityMap.put("469024", "lingao");
		cityMap.put("469025", "baisha");
		cityMap.put("469026", "changjiang");
		cityMap.put("469027", "ledong");
		cityMap.put("469028", "lingshui");
		cityMap.put("469029", "baoting");
		cityMap.put("469030", "qiongzhong ");
		cityMap.put("460321", "xishaqundao");
		cityMap.put("460322", "nanshaqundao");
		cityMap.put("460323", "zhongshaqundao");
		cityMap.put("540100", "lasa");
		cityMap.put("540300", "changdu");
		cityMap.put("542200", "shannan");
		cityMap.put("540200", "rikaze");
		cityMap.put("542400", "naqu");
		cityMap.put("542500", "ali");
		cityMap.put("542600", "linzhi");
		cityMap.put("630100", "xining");
		cityMap.put("630200", "haidong");
		cityMap.put("632200", "haibei");
		cityMap.put("632300", "huangnan");
		cityMap.put("632500", "hainan");
		cityMap.put("632600", "guoluo");
		cityMap.put("632700", "yushu");
		cityMap.put("632800", "haixi");
		cityMap.put("640100", "yinchuan");
		cityMap.put("640200", "shizuishan");
		cityMap.put("640300", "wuzhong");
		cityMap.put("640400", "guyuan");
		cityMap.put("640500", "zhongwei");
		cityMap.put("650100", "wulumuqi");
		cityMap.put("650200", "kelamayi");
		cityMap.put("652100", "tulufan");
		cityMap.put("652200", "hami");
		cityMap.put("652300", "changji");
		cityMap.put("652700", "boertala");
		cityMap.put("652800", "bayinguoleng");
		cityMap.put("652900", "akesu");
		cityMap.put("653000", "kezilesu");
		cityMap.put("653100", "kashi");
		cityMap.put("653200", "hetian");
		cityMap.put("654000", "yili");
		cityMap.put("654200", "tacheng");
		cityMap.put("654300", "aletai");
		cityMap.put("659001", "shihezi");
		cityMap.put("659002", "alaer");
		cityMap.put("659003", "tumushuke");
		cityMap.put("659004", "wujiaqu");
		cityMap.put("150100", "huhehaote");
		cityMap.put("150200", "baotou");
		cityMap.put("150300", "wuhai");
		cityMap.put("150400", "chifeng");
		cityMap.put("150500", "tongliao");
		cityMap.put("150600", "eerduosi");
		cityMap.put("150700", "hulunbeier");
		cityMap.put("150800", "bayannaoer");
		cityMap.put("150900", "wulanchabu");
		cityMap.put("152200", "xinganmeng");
		cityMap.put("152500", "xilinguolemeng");
		cityMap.put("152900", "alashanmeng");
		cityMap.put("810100", "xianggangdao");
		cityMap.put("810200", "jiulong");
		cityMap.put("810300", "xinjie");
		cityMap.put("820100", "aomenbandao");
		cityMap.put("820200", "dangzai");
		cityMap.put("820300", "luhuan");
		cityMap.put("440100", "guangzhou");
		provinceMap.put("440000", "guangdong");
		provinceMap.put("310000", "Shanghai");
		provinceMap.put("320000", "jiangsu");
		provinceMap.put("330000", "zhejiang");
		provinceMap.put("410000", "henan");
		provinceMap.put("110000", "beijing");
		provinceMap.put("120000", "tianjin");
		provinceMap.put("370000", "shandong");
		provinceMap.put("140000", "shanxi");
		provinceMap.put("130000", "hebei");
		provinceMap.put("420000", "hubei");
		provinceMap.put("210000", "liaoning");
		provinceMap.put("510000", "sichuan");
		provinceMap.put("350000", "fujian");
		provinceMap.put("610000", "Shanxisheng");
		provinceMap.put("620000", "gansu");
		provinceMap.put("340000", "anhui");
		provinceMap.put("430000", "hunan");
		provinceMap.put("500000", "chongqing");
		provinceMap.put("220000", "jilin");
		provinceMap.put("230000", "heilongjiang");
		provinceMap.put("360000", "jiangxi");
		provinceMap.put("520000", "guizhou");
		provinceMap.put("530000", "yunnan");
		provinceMap.put("450000", "guangxi");
		provinceMap.put("460000", "hainan");
		provinceMap.put("540000", "xizang");
		provinceMap.put("630000", "qinghai");
		provinceMap.put("640000", "ningxia");
		provinceMap.put("650000", "xinjiang");
		provinceMap.put("150000", "neimenggu");
		provinceMap.put("810000", "xianggang");
		provinceMap.put("820000", "aomen");
	}
	
	
	public static String getCityMap(String key) {
		
		return cityMap.get(key);
		
	}
	
	public static String getProvinceMap(String key) {
		
		return provinceMap.get(key);
		
	}
	
	public static String getCityCodeMap(String apiname) {
		
		Set<String> set = cityMap.keySet();
		
		Iterator<String> iterator = set.iterator();
		
		while(iterator.hasNext()){
			
			String key =iterator.next();
			
			if(cityMap.get(key).equals(apiname)) {
				
				return key;
				
			}
		}
		
		return null;
		
	}
	
public static String getProvinceCodeMap(String apiname) {
		
		Set<String> set = provinceMap.keySet();
		
		Iterator<String> iterator = set.iterator();
		
		while(iterator.hasNext()){
			
			String key =iterator.next();
			
			if(provinceMap.get(key).equals(apiname)) {
				
				return key;
				
			}
		}
		
		return null;
		
	}
}
