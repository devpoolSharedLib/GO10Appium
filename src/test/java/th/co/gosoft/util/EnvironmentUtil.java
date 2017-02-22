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

import th.co.gosoft.model.NewLikeModel;
import th.co.gosoft.model.NewTopicModel;
import th.co.gosoft.model.ReadModel;
import th.co.gosoft.model.RoomModel;

public class EnvironmentUtil {
	private static final String ROOM_ID = "rm01";
	private static Database db = CloudantClientUtils.getDBNewInstance();
	private static DateFormat postFormat = createSimpleDateFormat("yyyy/MM/dd HH:mm:ss", "GMT+7");
	private static String stampDate;
	 
	public static void deleteTopic(){
		System.out.println(">>>>>>>>>>>>>>>>>>> deleteTopic()");
        List<NewTopicModel> topicModelList = db.findByIndex(getTopicByRoomIdJsonString(), NewTopicModel.class, new FindByIndexOptions()
             .fields("_id").fields("_rev").fields("avatarName").fields("avatarPic").fields("subject").fields("empEmail")
             .fields("content").fields("date").fields("type").fields("roomId").fields("countLike").fields("updateDate").sort(new IndexField("date", SortOrder.asc)));
        
        System.out.println("size : "+topicModelList.size());
        for(int i=0;i<topicModelList.size();i++){
        	 db.remove(topicModelList.get(i));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>> deleteTopic Complete");
	}
	
	public static void resetTotalTopic() {
		System.out.println(">>>>>>>>>>>>>>>>>>> resetTotalTopic()");
		List<RoomModel> roomModelList = db.findByIndex(getRoomModelByRoomId(), RoomModel.class);
		RoomModel roomModel = roomModelList.get(0);
		roomModel.setTotalTopic(1);
		db.update(roomModel);
		System.out.println(">>>>>>>>>>>>>>>>>>> resetTotalTopic Complete");
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
        
        System.out.println("Like list size : "+likeModelList.size());
        for(int i=0;i<likeModelList.size();i++){
        	 db.remove(likeModelList.get(i));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>> deleteLike Complete");
	}
	
	public static void deleteRead(){
		System.out.println(">>>>>>>>>>>>>>>>>>> deleteRead()");
        List<ReadModel> readModelList = db.findByIndex(getReadModelByEmpEmailJsonString("appium@gosoft.co.th"), ReadModel.class);
        
        System.out.println("Read list size : "+readModelList.size());
        for(int i=0;i<readModelList.size();i++){
        	 db.remove(readModelList.get(i));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>> deleteRead Complete");
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
	
	private static String getRoomModelByRoomId() {
		StringBuilder sb = new StringBuilder();
        sb.append("{\"selector\": {");
        sb.append("\"_id\": \""+ROOM_ID+"\",");
        sb.append("\"$and\": [{\"type\": \"room\"}]");
        sb.append("}}");
        System.out.println("query : "+sb.toString());
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
	
	private static String getReadModelByEmpEmailJsonString(String empEmail){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"selector\": {");
        sb.append("\"_id\": {\"$gt\": 0},");
        sb.append("\"$and\": [{\"type\":\"read\"}, {\"empEmail\":\""+empEmail+"\"}]");
        sb.append("},");
        sb.append("\"fields\": [\"_id\",\"_rev\",\"topicId\",\"empEmail\",\"type\"]}");
        return sb.toString();
	}	
	 
	private static DateFormat createSimpleDateFormat(String formatString, String timeZone) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat;
    }
	
}
