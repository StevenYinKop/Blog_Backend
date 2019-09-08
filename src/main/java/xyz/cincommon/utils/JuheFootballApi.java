package xyz.cincommon.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import xyz.cincommon.vo.MatchInfoVo;

@Slf4j
public class JuheFootballApi {
	private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static final String USER_AGENT =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    private static final String TEAM_URL = "http://op.juhe.cn/onebox/football/team"; 
    private static final String RESULT_LIST = "list";
    private static final String ERROR_CODE = "error_code";
    
    private static final String MATCH_ENTITY_RESULT = "c4R";
    private static final String MATCH_ENTITY_HOME = "c4T1";
    private static final String MATCH_ENTITY_AWAY = "c4T2";
    private static final String MATCH_ENTITY_TYPE = "c1";
    private static final String MATCH_ENTITY_DATE = "c2";
    private static final String MATCH_ENTITY_TIME = "c3";
    
    
    private String appKey;
    
    public JuheFootballApi(String appKey) {
		super();
		this.appKey = appKey;
	}

	public List<MatchInfoVo> queryTeamSchedule(String team){
        List<MatchInfoVo> result = Lists.newArrayList();
        String url = TEAM_URL;//请求接口地址
        Map<String, Object> params = Maps.newHashMap();
            params.put("key", appKey);
            params.put("dtype", "json");
            params.put("team", team);
        try {
            String returnResult = net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(returnResult);
            if(object.getInteger(ERROR_CODE)==0){
            	JSONArray list = object.getJSONArray(RESULT_LIST);
            	for (int i = 0; i < list.size(); i++) {
            		JSONObject match = list.getJSONObject(i);
            		MatchInfoVo vo = new MatchInfoVo();
            		vo.setResult(match.get(MATCH_ENTITY_RESULT).toString());
            		vo.setHomeTeam(match.get(MATCH_ENTITY_HOME).toString());
            		vo.setAwayTeam(match.get(MATCH_ENTITY_AWAY).toString());
            		vo.setType(match.get(MATCH_ENTITY_TYPE).toString());
            		String date = match.get(MATCH_ENTITY_DATE).toString();
            		String time = match.get(MATCH_ENTITY_TIME).toString();
            		vo.setMatchDate(date + " " + time);
            		result.add(vo);
            	}
            }else{
            	log.error("invoke juhe api error: error msg: {}", object.get(ERROR_CODE)+":"+object.get("reason"));
            }
        } catch (Exception e) {
        	log.error("invoke juhe api error: error msg: {}", e);
        }
        return result;
    }
    
	/**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String net(String strUrl, Map<String, Object> params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setRequestProperty("User-agent", USER_AGENT);
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
            	   
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }

   //将map型转为请求参数型
   public static String urlencode(Map<String,Object> data) {
       StringBuilder sb = new StringBuilder();
       for (Map.Entry<String, Object> i: data.entrySet()) {
           try {
               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
       return sb.toString();
   }
}
