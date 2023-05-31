package com.ideabobo.Javascript;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.cordova.CordovaActivity;
import org.json.JSONException;
import org.json.JSONObject;

import com.ideabobo.gap.MainActivity;
import com.ideabobo.gap.R;
import com.ideabobo.tool.FileTool;
import com.ideabobo.tool.SessionStorage;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import android.view.Display;
import android.webkit.WebView;
import android.widget.Toast;

public class MyObj {
	public Context ctx= null;
	public JSONObject json = new JSONObject();
    private CordovaActivity mGap;
    public MediaRecorder mRecorder;
    public static String fileName = "";
    private MediaPlayer mPlayer = null;  
    public MyObj(CordovaActivity mGap){
		this.mGap = mGap;
		ctx = mGap.getApplicationContext();		
	}
    
    public String Test(){
    	return "success";
    }
    
    public void recordSound(){
    	String fileDir = FileTool.getSDPath()+File.separator+"qqrecord"+File.separator;
    	File file = new File(fileDir);
    	if(!file.exists()){
    		file.mkdirs();
    	}
    	fileName =  fileDir+UUID.randomUUID()+".aac";
    	mRecorder = new MediaRecorder();  
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
        mRecorder.setOutputFile(fileName);  
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC); 
        try {  
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e){  
             
        }  
       
    }
    
    public void stopSound(){
    	mRecorder.stop();  
        mRecorder.release();  
        mRecorder = null;
        
    }
    
    public String getRecordFile(){
    	return fileName;
    }
    
    public void playAudio(String path){
    	String fileDir = FileTool.getSDPath()+File.separator+"qqrecord"+File.separator;
    	String filePath = "http://192.168.1.102:8080/huodongchat/upload/"+path;
    	mPlayer = new MediaPlayer();
    	mPlayer.setOnCompletionListener(new OnCompletionListener() {	
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				mPlayer.release();
				mPlayer = null;
			}
		}); 
        try{  
            mPlayer.setDataSource(filePath);  
            mPlayer.prepare();  
            mPlayer.start();  
        }catch(IOException e){  
            Log.e("phonegap","播放失败");  
        }  
    }
    
    public String getScreenInfo() {
		Display display = mGap.getWindowManager().getDefaultDisplay();
		Point outPoint = new Point();
		try {
			Method method = display.getClass().getMethod("getRealSize",
					new Class[] { Point.class });
			try {
				method.invoke(display, outPoint);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();	
		
		Log.i("ScreenInfo", "invoke onCreate screenWidth: " + display.getWidth()
				+ ", screenHeight: " + display.getHeight());
	
		float aspectRatio = (float)display.getWidth() / (float)display.getHeight();		
		try {
			obj.put("aspectRatio", aspectRatio);
			obj.put("width", display.getWidth());
			obj.put("height", display.getHeight());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
    
    /**
     * 
     * @param activity
     * @return > 0 success; <= 0 fail
     */
    public int getStatusHeight(){
        int statusHeight = 0;
        Rect localRect = new Rect();
        mGap.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight){
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = mGap.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
    
    public String getClientId(){
    	return SessionStorage.get("clientid");
    }
    public void showIntentActivityNotify(String title,String note,int id){		
		Notification notif = new Notification(R.drawable.icon, note, System.currentTimeMillis() );
		notif.flags = Notification.FLAG_AUTO_CANCEL;
		notif.defaults |= Notification.DEFAULT_SOUND;
		notif.defaults |= Notification.DEFAULT_VIBRATE;
		 
		Intent notificationIntent = new Intent(ctx, MainActivity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, notificationIntent, 0);
		 
		notif.setLatestEventInfo(ctx, title, note, contentIntent);
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager)
		ctx.getSystemService(ns);
		mNotificationManager.notify(id, notif);
	}
    
    public String getPath(String uristr){
    	uristr = uristr.replaceAll("%3A", ":");
    	Uri uri = Uri.parse(uristr);
    	/*String res = null;
	    String[] proj = { MediaStore.Images.Media.DATA };
	    Cursor cursor = ctx.getContentResolver().query(contentUri, proj, null, null, null);
	    if(cursor.moveToFirst()){;
	       int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	       res = cursor.getString(column_index);
	    }
	    cursor.close();
	    return res;*/
    	if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = ctx.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    
    public String getRealPath(String uristr){
        Uri uri = Uri.parse(uristr);//得到uri，后面就是将uri转化成file的过程。
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = mGap.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        File file = new File(img_path);
        return img_path;
    }
    
    public void myshare(String subject, String text) {
		// TODO Auto-generated method stub
		Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
		sendIntent.setType("text/plain");
		sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
		this.mGap.startActivity(sendIntent);
	}
    
    public void chooseFile(){
    	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mGap.startActivityForResult(intent,1);
    }
}
