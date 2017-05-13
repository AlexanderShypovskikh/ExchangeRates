package com.currencyAdviser.model;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.currencyAdviser.model.CurrencyClass;
import com.google.gson.Gson;

/*
 * 
Класс - загрузчик данных с сервиса http://apilayer.net
*
*/


 public class CurrencylayerProvider {
		private static String ACCESS_KEY = "4270bb7f08096449c0ad220fa7b998e2";
		private static String currencies = "GBP,EUR,UAH,RUB,CNY";
	
	//провести проверку
	public static CurrencyClass getCurrencyData() throws IOException{
		 String json = readUrl("http://apilayer.net/api/live"+"?access_key="+ACCESS_KEY+"&currencies="+currencies+"&format=1");
         CurrencyClass currency = new CurrencyClass();
         com.google.gson.Gson gson = new com.google.gson.Gson();        
         currency = gson.fromJson(json, CurrencyClass.class);
		return currency; 
	} 
	
	public static List<CurrencyClass> getCyrrencyHistory(){
		List<CurrencyClass> currencyHistory = new ArrayList();
      //  Thread[] threadPool = new Thread[5];	
		Date date = new Date();
		for(int i = 0; i < 6; i++){
			
		}
		return null;
		
	}
	

	
	private static String readUrl(String urlString) throws IOException {
		BufferedReader reader = null;
		try{
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			int read; 
			char[] chars = new char[1024];
			StringBuffer buffer = new StringBuffer();
			while((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			    return buffer.toString();
			} catch(IOException e){
				e.printStackTrace();
			}finally {
			   if (reader != null)
			   reader.close();
			 }
		return null;		
		}
}
