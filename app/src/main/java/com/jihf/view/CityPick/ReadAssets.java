package com.jihf.view.CityPick;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadAssets {
	public String readAssetsData(Context c, String s) {
		StringBuffer sb = null;
		InputStream is = null;
		try {
			is = c.getAssets().open("data/" + s + ".txt");
			byte[] buff = new byte[1024];
			sb = new StringBuffer();
			int len = 0;
			while ((len = is.read(buff)) != -1) {
				sb.append(new String(buff, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			is = null;
		}
	}
	
	public String readAssetsDataV2(Context c, String s) {
		ByteArrayOutputStream bos = null;
		InputStream is = null;
		try {
			is = c.getAssets().open("data/" + s + ".txt");
			byte[] buff = new byte[1024];
			bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(buff)) != -1) {
				bos.write(buff, 0, len);
				bos.flush();
			}
			byte[] result = bos.toByteArray();
			String resultStr = new String(result);
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			is = null;
			
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
