package comman;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import com.riya.product.weburly.R;


public class Methods {
	
	private static ProgressDialog progressDialog;
	
	public static void closeProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	public static void showMessage(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	
	public static void showProgressDialog(Context ctx) {
		progressDialog = ProgressDialog.show(ctx, "", "Please Wait", true);
		progressDialog.setContentView(R.layout.progress_dialog);
		progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	
	public static String getExceptionDetails(Activity act, Exception e) {
	    StackTraceElement[] stackTraceElement = e.getStackTrace();

	    String message="";
	    String fileName = "";
	    String methodName = "";
	    int lineNumber = 0;
	    

	    try {
	        String packageName = act.getApplicationInfo().packageName;
	        for (int i = 0; i < stackTraceElement.length; i++) {
	            if (stackTraceElement[i].getClassName().startsWith(packageName))
	            {
	                fileName = stackTraceElement[i].getFileName();
	                methodName = stackTraceElement[i].getMethodName();
	                lineNumber = stackTraceElement[i].getLineNumber();
	                message = e.getMessage();
	                
	                
	                break;
	            }
	        }
	    } catch (Exception e2) {
	    }

	    return fileName + ":" + methodName + "():line "
	            + String.valueOf(lineNumber)+" : Error Message:- "+message;
	}
	
	/*public static void customDialog(final Context context){
		
		final Dialog dialog=new Dialog(context);
		dialog.setContentView(R.layout.dialog_layout);
		  Button btncancel = (Button) dialog.findViewById(R.id.btn_no);
		  Button btnAddAgent=(Button) dialog.findViewById(R.id.btn_yes);
	
		  btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		  
		  btnAddAgent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent it=new Intent(context, AddAgentActivity.class);	
			context.startActivity(it);
			}
		});
		
	}*/
	

}
