/**
 * @author NeekoDeveloper
 */
package com.tcs.phonegap.plugins.example;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

/**
 * @author 378069
 *
 */
public class Calendar extends CordovaPlugin{
	
	public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";
	
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		try {
		    if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
		    	return startCalendar(args, callbackContext);
		    }
		    callbackContext.error("Invalid action");
		    return false;
		} catch(JSONException e) {
		    System.err.println("Exception: " + e.getMessage());
		    callbackContext.error(e.getMessage());
		    return false;
		} 
	}
	
	
	private boolean startCalendar(JSONArray args, CallbackContext callbackContext) throws JSONException{
		JSONObject arg_object = args.getJSONObject(0);
        Intent calIntent = new Intent(Intent.ACTION_EDIT)
	   .setType("vnd.android.cursor.item/event")
	   .putExtra("beginTime", arg_object.getLong("startTimeMillis"))
	   .putExtra("endTime", arg_object.getLong("endTimeMillis"))
	   .putExtra("title", arg_object.getString("title"))
	   .putExtra("description", arg_object.getString("description"))
	   .putExtra("eventLocation", arg_object.getString("eventLocation"));
	
	  this.cordova.getActivity().startActivity(calIntent);
	  callbackContext.success();
	  return true;
	}
}
