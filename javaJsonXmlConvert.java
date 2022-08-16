package javaTest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.XML; 

public class javaJsonXmlConvert {

	public static void main(String[] args) {
		 
		// 1. json2xml
		String str = "{'이름':'홍길동', '나이':'15', '전화번호':'010-1111-2222', '주소':'서울시 영등포구'}";
		JSONObject json = new JSONObject(str);
		String xml1 = XML.toString(json);
		
		System.out.println("1. json2xml");
		System.out.println(xml1);
		System.out.println("-----------------------------------------------------");
		
		//2. xml2json
		JSONObject jsonXml = XML.toJSONObject(xml1);
		String strJsonXml = jsonXml.toString();
		
		System.out.println("2. xml2json");
		System.out.println(strJsonXml);
		System.out.println("-----------------------------------------------------");
		
		// 3. 배열 json2xml
		String strArray = "{'개발자':[";
		strArray += "{'이름':'홍길동', '나이':'15', '전화번호':'010-1111-2222', '주소':'서울시 영등포구'},"; 
		strArray += "{'이름':'김개똥', '나이':'20', '전화번호':'010-3333-4444', '주소':'서울시 동대문구'}";
		strArray += "]}";
		
		JSONObject json2 = new JSONObject(strArray);
		
		String xml2 = "";
		xml2 = XML.toString(json2);
		
		System.out.println("3. 배열 json2xml");
		
		//JSONArray jsonArray = json2.getJSONArray("개발자");
		 
		//for(int i=0;i<jsonArray.length();i++) {
			
		//	xml2 += XML.toString(jsonArray.get(i));			
		//}
		
		System.out.println(xml2);
		System.out.println("-----------------------------------------------------");
		
		// 4. 배열 xml2json
		JSONObject jsonXmlArry = XML.toJSONObject(xml2);
		String strJsonXmlArray = jsonXmlArry.toString();
		
		System.out.println("4. 배열 xml2json");
		System.out.println(strJsonXmlArray);
		System.out.println("-----------------------------------------------------");
		System.out.println("원래json과 비교");
		System.out.println(strArray);
		
		  
	}
}
