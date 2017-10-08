package com.xyz.hayhay.location.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vnsoftware.facebook.HTTPClient;
import com.xyz.hayhay.location.entirty.Location;
import com.xyz.hayhay.location.entirty.Photo;
import com.xyz.hayhay.location.entirty.Place;
import com.xyz.hayhay.location.entirty.PlaceDetail;
import com.xyz.hayhay.location.entirty.SearchType;
import com.xyz.hayhay.util.JSONHelper;

public class LocationService {
	private static final String googleKey = "AIzaSyD_lWvBcnRaXdGuR1kGoZzv3mnOx5QjAKs";
	
	public static List<Place> searchNearbyBK(Location location, int radius, String type, String searchText){
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location.getLat()+","+location.getLng()+"&radius="+radius+"&type="+type+"&key=" + googleKey;
		List<Place> places = new ArrayList<Place>();
		try {
			String result = HTTPClient.executeHttpGet(url, null);
			JSONObject jsons = (JSONObject)new JSONParser().parse(result);
			JSONArray results = (JSONArray)jsons.get("results");
			
			for(int i = 0; i < results.size(); i++){
				JSONObject place = (JSONObject) results.get(i);
				JSONObject geometry = (JSONObject)place.get("geometry");
				JSONObject jsonlocation = (JSONObject)geometry.get("location");
				Location l = new Location(getString(jsonlocation, "lat"), getString(jsonlocation, "lng"));
				List<Photo> photos = new  ArrayList<>();
				if(place.get("photos") != null){
					JSONArray photoJsons = (JSONArray) place.get("photos");
					for(int j =0 ; j < photoJsons.size();j++){
						JSONObject photo = (JSONObject)photoJsons.get(j);
						Photo p = new Photo(Integer.valueOf(getString(photo, "height")), Integer.valueOf(getString(photo, "width")), getString(photo, "html_attributions"), getString(photo, "photo_reference"));
						photos.add(p);
					}
				}
				
				String icon = getString(place, "icon");
				String id = getString(place, "id");
				String name = getString(place, "name");
				String place_id = getString(place, "place_id");
				String reference = getString(place, "reference");
				String vicinity = getString(place, "vicinity");
				String types = getString(place, "types");
				Place p = new Place(l, icon, id, name, photos, place_id, reference, types, vicinity);
				places.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return places;
	}
	public static JSONObject searchNearby(Location location, int radius, String type, String searchText){
		String name = "";
		if(searchText != null && !searchText.isEmpty())
			name = "&name=" + searchText;
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location.getLat()+","+location.getLng()+"&radius="+radius+"&type="+type + name + "&key=" + googleKey;
		JSONObject response = new JSONObject();
		response.put("errorCode", "failed");
		try {
			String result = HTTPClient.executeHttpGet(url, null);
			JSONObject jsons = (JSONObject)new JSONParser().parse(result);
			JSONArray results = (JSONArray)jsons.get("results");
			response.put("errorCode", "success");
			response.put("currentlocation", JSONHelper.toJSONObject(location));
			response.put("places", results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	public static JSONObject getPlaceDetail(String placeId){
		String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key="+googleKey;
		String result;
		JSONObject response = new JSONObject();
		response.put("errorCode", "failed");
		try {
			result = HTTPClient.executeHttpGet(url, null);
			JSONObject jsons = (JSONObject)new JSONParser().parse(result);
			JSONObject resultJson = (JSONObject)jsons.get("result");
			response.put("errorCode", "success");
			response.put("result", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static List<SearchType> getSearchTypes(){
		List<SearchType> types = new ArrayList<SearchType>();
		
		types.add(new SearchType("gas_station", "Cây Săng", ""));
		types.add(new SearchType("atm", "ATM", ""));
		types.add(new SearchType("bank", "Ngân Hàng", ""));
		
		
		types.add(new SearchType("restaurant", "Nhà Hàng", ""));
		types.add(new SearchType("cafe", "Quán Cafe", ""));
		types.add(new SearchType("meal_takeaway", "Đồ Ăn Nhanh", ""));
		types.add(new SearchType("bakery", "Tiệm Bánh", ""));
		types.add(new SearchType("bar", "Quá Bar", ""));
		types.add(new SearchType("night_club", "Câu Lạc Bộ Đêm", ""));
		types.add(new SearchType("movie_theater", "Rạp Chiếu Phim", ""));
		
		types.add(new SearchType("bus_station", "Tram Xe Bus", ""));
		types.add(new SearchType("taxi_stand", "Xe Khách", ""));
		types.add(new SearchType("train_station", "Ga Xe Lửa", ""));
		types.add(new SearchType("transit_station", "Bến Xe", ""));
//		types.add(new SearchType("subway_station", "Tầu Điện Ngầm", ""));
		types.add(new SearchType("car_repair", "Sửa Xe", ""));
		types.add(new SearchType("car_wash", "Rửa Xe", ""));
		types.add(new SearchType("parking", "Bãi Đâu Xe", ""));
		types.add(new SearchType("travel_agency", "Đại Lý Du Lịch", ""));
		
		
		types.add(new SearchType("beauty_salon", "Trung Tâm Làm Đẹp", ""));
		types.add(new SearchType("spa", "Spa", ""));
		types.add(new SearchType("hair_care", "Chăm Sóc Tóc", ""));
		types.add(new SearchType("gym", "Phòng Gym", ""));
		
		
		types.add(new SearchType("clothing_store", "Cửa Hàng Bán Quần Áo", ""));
		types.add(new SearchType("jewelry_store", "Cửa Hàng Vàng Bạc/Trang Sức", ""));
		types.add(new SearchType("pet_store", "Của Hàng Bán Thú Cưng", ""));
		types.add(new SearchType("shoe_store", "Cửa Hàng Bán Giầy/Dép", ""));
		types.add(new SearchType("shopping_mall", "Trung Tâm Mua Sắm", ""));
		
		types.add(new SearchType("hospital", "Bệnh Viện", ""));
		types.add(new SearchType("dentist", "Phòng Nha Sỹ", ""));
		types.add(new SearchType("doctor", "Phòng Khám", ""));
		types.add(new SearchType("pharmacy", "Nhà Thuốc", ""));
		
		
		types.add(new SearchType("book_store", "Nhà Sách", ""));
		types.add(new SearchType("library", "Thư Viện", ""));
		types.add(new SearchType("museum", "Viện Bảo Tàng", ""));
		types.add(new SearchType("zoo", "Sở Thú", ""));
		types.add(new SearchType("park", "Công Viên", ""));
		types.add(new SearchType("post_office", "Bưu Điện", ""));
		

		return types;
	}
	
	private static String getString(JSONObject json, String key){
		if(json.get(key) == null){
			return "";
		}else{
			return json.get(key).toString();
		}
	}
	public static void main(String[] args) {
		Location l = new Location("10.8528929", "106.6236189");
		List<Place> places = LocationService.searchNearbyBK(l, 500, "food", "searchText");
		for(Place p : places){
			System.out.println(p.getVicinity());
		}
	}
}
