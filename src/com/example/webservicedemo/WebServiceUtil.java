package com.example.webservicedemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebServiceUtil {
	public static final String SERVICE_NAMESPACE = "http://WebXml.com.cn/";
	public static final String SERVICE_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
		
	
	
	// TODO Auto-generated constructor stub
	public static List<String> getProvinceList() {
		String methodName = "getRegionProvince";
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		try {
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,
					methodName);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;

			ht.call(SERVICE_NAMESPACE + methodName, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");
				return parseProvinceOrCity(detail);
			}
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 鏍规嵁鐪佷唤鑾峰彇鍩庡競鍒楄〃
	public static List<String> getCityListsByProvince(String province) {
		// 璋冪敤鐨勬柟娉�
		String methodName = "getSupportCityString";
		// 鍒涘缓httptransportSE浼犺緭瀵硅薄
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// 瀹炰緥鍖朣oapObject瀵硅薄
		SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);
		// 娣诲姞涓�涓姹傚弬鏁�
		soapObject.addProperty("theRegionCode", province);
		// 浣跨敤soap1.1鍗忚鍒涘缓envelop瀵硅薄
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = soapObject;
		// 璁剧疆涓�.NET鎻愪緵鐨剋ebservice淇濇寔杈冨ソ鐨勫吋瀹规��
		envelope.dotNet = true;

		// 璋冪敤webservice
		try {
			ht.call(SERVICE_NAMESPACE + methodName, envelope);
			if (envelope.getResponse() != null) {
				// 鑾峰彇鏈嶅姟鍣ㄥ搷搴旇繑鍥炵殑SOAP娑堟伅
				SoapObject result = (SoapObject) envelope.bodyIn;
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");
				// 瑙ｆ瀽鏈嶅姟鍣ㄥ搷搴旂殑SOAP娑堟伅
				System.out.println("liulianhua----------------------------------"+methodName
						+ "Result"+detail);
				return parseProvinceOrCity(detail);
			}
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static void getDtoSampleMainPalns() {
		String methodName = "GetDtoSampleMainPalns";

		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		try {
			ht.debug = true;
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,
					methodName);
			soapObject.addProperty("PhonesIMEINumber", "358142035186228");
			soapObject.addProperty("page", 1);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;

			ht.call(SERVICE_NAMESPACE + methodName, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");
				System.out.println(detail+"------information-------------");
			
				
				
			}
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// 瑙ｆ瀽鐪佷唤鎴栧煄甯�
	public static List<String> parseProvinceOrCity(SoapObject detail) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < detail.getPropertyCount(); i++) {
			// 瑙ｆ瀽鍑烘瘡涓渷浠�
			result.add(detail.getProperty(i).toString().split(",")[0]);
		}
		return result;
	}

	// 鏍规嵁鍩庡競瀛楃涓茶幏鍙栫浉搴斿ぉ姘旀儏鍐�
	public static SoapObject getWeatherByCity(String cityName) {
		String methodName = "getWeather";
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);
		soapObject.addProperty("theCityCode", cityName);
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;

		try {
			ht.call(SERVICE_NAMESPACE + methodName, envelope);
			SoapObject result = (SoapObject) envelope.bodyIn;
			SoapObject detail = (SoapObject) result.getProperty(methodName
					+ "Result");
			return detail;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
