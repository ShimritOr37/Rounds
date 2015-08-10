package com.example.simplechat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.database.Cursor;
import android.net.ParseException;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder>{
    public ArrayList<String[]> messages;
    String lastDate=null;
    private String mUnrealDate=  new SimpleDateFormat("dd-MM-yyyy").format(new Date());;

    public static class ViewHolder extends RecyclerView.ViewHolder{
    	 public TextView seperator;
        public TextView messageSender;
        public TextView messageReciever;
       

        public ViewHolder(View itemView){
            super(itemView);
            seperator = (TextView) itemView.findViewById(R.id.separetor);
            messageSender = (TextView) itemView.findViewById(R.id.user1);
            messageReciever = (TextView) itemView.findViewById(R.id.user2);
            
        }
    }

    public MessagesRecyclerViewAdapter(ArrayList<String[]> messages){
        this.messages = messages;
    }

    @Override
    public MessagesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_view, parent, false);

        ViewHolder viewholder = new ViewHolder(view);//(TextView) view.findViewById(R.id.messageContent));

        return viewholder;
    }
boolean updatedY,updatedT;
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
    	// MyListItem myListItem = MyListItem.fromCursor(cursor);
     	try {
   		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
                    Locale.US);  
   		viewHolder.seperator.setText(messages.get(position)[1]);
			if (!updatedT&&parseDate(messages.get(position)[1]).equals(parseDate(dateFormat.format(new Date())))){
				viewHolder.seperator.setVisibility(View.VISIBLE);
				viewHolder.seperator.setText("TODAY");
				updatedT = true;
				}
			else if (!updatedY&&parseDate(messages.get(position)[1]).equals(parseDate(getYesterdayDateString()))){
				viewHolder.seperator.setVisibility(View.VISIBLE);
				viewHolder.seperator.setText("YESTERDAY");
				updatedY = true;
			}else{
				if (lastDate!=null && !lastDate.equals(messages.get(position)[1])){
					viewHolder.seperator.setVisibility(View.VISIBLE);
					viewHolder.seperator.setText(messages.get(position)[1]);
				}
			}
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	viewHolder.messageSender.setText(messages.get(position)[2]+" (time:"+messages.get(position)[1]+")");
        viewHolder.messageReciever.setText(messages.get(position)[5]+" (time:"+messages.get(position)[4]+")");

        lastDate = messages.get(position)[1];
    }
    private static Date parseDate(String text)
    	    throws java.text.ParseException
    	{
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
    	                                                       Locale.US);      
    	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	    return dateFormat.parse(text);
    	}
    private String getYesterdayDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
                Locale.US); 
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);    
        return dateFormat.format(cal.getTime());
}
    @Override
    public int getItemCount(){
        return messages.size();
    }
    public class MyListItem{
    	  private String user1m;
    	  private String user2m;
    	  
    	  public void setMessage1(String user1m){
    	    this.user1m=user1m;
    	  }
    	  public void setMessage2(String user2m){
      	    this.user2m=user2m;
      	  }
    	  public String getMessage1(){
    	    return user1m;
    	  }
    	  public String getMessage2(){
    		  return user2m;
    	  }
    	  public  MyListItem fromCursor(Cursor cursor) {
    	    cursor.moveToFirst();
    	    setMessage1(cursor.getString(0));
    	    setMessage2(cursor.getString(1));
    	    return this;
    	  }
    	}

}