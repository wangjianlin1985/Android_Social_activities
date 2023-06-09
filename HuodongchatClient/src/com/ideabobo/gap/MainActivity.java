/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.ideabobo.gap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.Toast;

import java.io.File;

import org.apache.cordova.*;

import com.ideabobo.Javascript.BaiduLocation;
import com.ideabobo.Javascript.MyObj;

public class MainActivity extends CordovaActivity {
	public Context ctx = null;
	private static final String MASTERSECRET = "WWcP8gLcqU6EkWdGmnJJl9";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init();
		ctx = this.getApplicationContext();
		MyObj myObj = new MyObj(this);
		BaiduLocation myLocation = new BaiduLocation(this);
		appView.addJavascriptInterface(myObj, "myObj");
		appView.addJavascriptInterface(myLocation, "myLocation");
		WebSettings settings = appView.getSettings();
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		appView.setInitialScale(1);
		// Set by <content src="index.html" /> in config.xml
		loadUrl("file:///android_asset/client/index.html");
	}

}
