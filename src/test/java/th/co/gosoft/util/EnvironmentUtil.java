package th.co.gosoft.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.FindByIndexOptions;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;

import th.co.gosoft.model.NewTopicModel;
import th.co.gosoft.model.NewLikeModel;

public class EnvironmentUtil {
	private static Database db = CloudantClientUtils.getDBNewInstance();
	private static DateFormat postFormat = createSimpleDateFormat("yyyy/MM/dd HH:mm:ss", "GMT+7");
	private static String ROOM_ID = "rm01";
	private static String stampDate;
	 
	public static void deleteTopic(){
		System.out.println(">>>>>>>>>>>>>>>>>>> deleteTopic()");
        List<NewTopicModel> topicModelList = db.findByIndex(getTopicByRoomIdJsonString(), NewTopicModel.class, new FindByIndexOptions()
             .fields("_id").fields("_rev").fields("avatarName").fields("avatarPic").fields("subject").fields("empEmail")
             .fields("content").fields("date").fields("type").fields("roomId").fields("countLike").fields("updateDate").sort(new IndexField("date", SortOrder.asc)));
        
        for(int i=0;i<topicModelList.size();i++){
        	 db.remove(topicModelList.get(i));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>> deleteTopic Complete");
	}

	public static void createTopic(){
		System.out.println(">>>>>>>>>>>>>>>>>>> cleateTopic()");
		stampDate = postFormat.format(new Date());
        NewTopicModel topicmodel = new NewTopicModel();
        topicmodel.setAvatarName("appiumTest");
        topicmodel.setAvatarPic("man09");
        topicmodel.setContent("new topic for test");
        topicmodel.setSubject("Post for Test Comment and Like Automate");
        topicmodel.setCountLike(0);
        topicmodel.setDate(stampDate);
        topicmodel.setEmpEmail("appium@gosoft.co.th");
        topicmodel.setRoomId(ROOM_ID);
        topicmodel.setType("host");
        topicmodel.setUpdateDate(stampDate);
        db.save(topicmodel);
        System.out.println(">>>>>>>>>>>>>>>>>>> create Complete");
	}
	
	public static void deleteLike(){
		System.out.println(">>>>>>>>>>>>>>>>>>> deleteLike()");
        List<NewLikeModel> likeModelList = db.findByIndex(getLikeModelByEmpEmailJsonString("appium@gosoft.co.th"), NewLikeModel.class, new FindByIndexOptions()
             .fields("_id").fields("_rev").fields("topicId").fields("empEmail")
             .fields("statusLike").fields("date").fields("type"));
        
        for(int i=0;i<likeModelList.size();i++){
        	 db.remove(likeModelList.get(i));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>> deleteLike Complete");
	}
	
	private static String getTopicByRoomIdJsonString( ){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"selector\": {");
        sb.append("\"_id\": {\"$gt\": 0},");
        sb.append("\"date\": {\"$gt\": 0},");
        sb.append("\"pin\": { \"$exists\": false },");
        sb.append("\"roomId\": \""+ROOM_ID+"\"");
        sb.append("}}");
        return sb.toString();
    }
	
	 private static String getLikeModelByEmpEmailJsonString(String empEmail){
	        StringBuilder sb = new StringBuilder();
	        sb.append("{\"selector\": {");
	        sb.append("\"_id\": {\"$gt\": 0},");
	        sb.append("\"$and\": [{\"type\":\"like\"}, {\"empEmail\":\""+empEmail+"\"}]");
	        sb.append("},");
	        sb.append("\"fields\": [\"_id\",\"_rev\",\"topicId\",\"empEmail\",\"statusLike\",\"type\"]}");
	        return sb.toString();
	    }
	private static DateFormat createSimpleDateFormat(String formatString, String timeZone) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat;
    }
	
	public static void main(String [] args){
//		deleteTopic();
//		createTopic();
		deleteLike();
	}
}
