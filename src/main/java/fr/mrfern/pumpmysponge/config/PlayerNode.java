package fr.mrfern.pumpmysponge.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class PlayerNode implements ConfigurationNode{

	private ConfigurationNode cfgNode;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	protected String name = "player";
	
	public ConfigurationNode getCfgNode() {
		return cfgNode;
	}
	
	public void save() {
		try {
			cfgLoader.save(cfgNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCfgNode(ConfigurationLoader<CommentedConfigurationNode> cfg) {
		cfgLoader = cfg;
		try {
			this.cfgNode = cfg.load();
			cfg.save(cfgNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getKey() {
		return cfgNode.getKey();
	}

	@Override
	public Object[] getPath() {
		return cfgNode.getPath();
	}

	@Override
	public ConfigurationNode getParent() {
		return cfgNode.getParent();
	}

	@Override
	public ConfigurationOptions getOptions() {
		return cfgNode.getOptions();
	}

	@Override
	public Object getValue(Object def) {
		return cfgNode.getValue(def);
	}

	@Override
	public Object getValue(Supplier<Object> defSupplier) {
		return cfgNode.getValue(defSupplier);
	}

	@Override
	public <T> T getValue(Function<Object, T> transformer, T def) {
		return cfgNode.getValue(transformer,def);
	}

	@Override
	public <T> T getValue(Function<Object, T> transformer, Supplier<T> defSupplier) {
		return cfgNode.getValue(transformer,defSupplier);
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer) {
		return cfgNode.getList(transformer);
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer, List<T> def) {
		return cfgNode.getList(transformer,def);
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer, Supplier<List<T>> defSupplier) {
		return cfgNode.getList(transformer,defSupplier);
	}

	@Override
	public <T> List<T> getList(TypeToken<T> type, List<T> def) throws ObjectMappingException {
		return cfgNode.getList(type, def);
	}

	@Override
	public <T> List<T> getList(TypeToken<T> type, Supplier<List<T>> defSupplier) throws ObjectMappingException {
		// TODO Auto-generated method stub
		return cfgNode.getList(type, defSupplier);
	}

	@Override
	public ConfigurationNode setValue(Object value) {
		return cfgNode.setValue(value);
	}

	@Override
	public <T> T getValue(TypeToken<T> type, T def) throws ObjectMappingException {
		return cfgNode.getValue(type, def);
	}

	@Override
	public <T> T getValue(TypeToken<T> type, Supplier<T> defSupplier) throws ObjectMappingException {
		return cfgNode.getValue(type, defSupplier);
	}

	@Override
	public ConfigurationNode mergeValuesFrom(ConfigurationNode other) {
		return cfgNode.mergeValuesFrom(other);
	}

	@Override
	public boolean hasListChildren() {
		return cfgNode.hasListChildren();
	}

	@Override
	public boolean hasMapChildren() {
		return cfgNode.hasMapChildren();
	}

	@Override
	public List<? extends ConfigurationNode> getChildrenList() {
		return cfgNode.getChildrenList();
	}

	@Override
	public Map<Object, ? extends ConfigurationNode> getChildrenMap() {
		return cfgNode.getChildrenMap();
	}

	@Override
	public boolean removeChild(Object key) {
		return removeChild(key);
	}

	@Override
	public ConfigurationNode getAppendedNode() {
		return cfgNode.getAppendedNode();
	}

	@Override
	public ConfigurationNode getNode(Object... path) {
		return cfgNode.getNode(path);
	}

	@Override
	public boolean isVirtual() {
		return cfgNode.isVirtual();
	}
	
	/*
	 * Ajout des getters et setter simplifiés
	 */
	
	// info player
	
	public String getPlayerUUIDString() {
		return cfgNode.getNode(name,"UUID").getString();
	}
	
	public UUID getPlayerUUID() {
		return UUID.fromString(cfgNode.getNode(name,"UUID").getString());
	}
	
	public String getPlayerName() {
		return cfgNode.getNode(name,"name").getString();
	}
	
	@Deprecated
	public List<String> getChunkOwned(){
		return cfgNode.getNode(name,"chunkOwner").getList(new Function<Object,String>() {
																@Override
																public String apply(Object input) {
																	if (input instanceof String) {
																		return (String) input;
																	} else {
																		return null;
																	}
																}
															});
	}
	//loaderRootNode.getNode(name,"chunkOwner").setValue(Arrays.asList("none"));*/
	
	public URL getForumProfileURL() {
		try {
			return new URL(cfgNode.getNode(name,"forum","profile-link").getString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getForumProfile() {
		return cfgNode.getNode(name,"forum","profile-link").getString();
	}
	
	public String getForumName() {
		return cfgNode.getNode(name,"forum","name").getString();
	}
	
	public boolean getForumProfileIsBan() {
		return cfgNode.getNode(name,"forum","isBan").getBoolean();
	}
	
	public String getPrimGroup() {
		return cfgNode.getNode(name,"grade","prim-grade").getString();
	}
	
	public List<String> getSubGroup(){
		
		Function<Object,String> stringTransformer = new Function<Object,String>() {
		    @Override
		    public String apply(Object input) {
		        if (input instanceof String) {
		            return (String) input;
		        } else {
		            return null;
		        }
		    }
		};
		
		return cfgNode.getNode(name,"grade","sub-grade").getList(stringTransformer);
	}
	
	public boolean getIsBanned() {
		return cfgNode.getNode(name,"ban","enable").getBoolean();
	}
	
	public String getPlayerBanRaison() {
		return cfgNode.getNode(name,"ban","raison").getString();
	}
	
	public int getPlayerBanTimeDay() {
		return cfgNode.getNode(name,"ban","time","day").getInt();
	}
	
	public int getPlayerBanTimeHour() {
		return cfgNode.getNode(name,"ban","time","day").getInt();
	}
	
	public int getPlayerBanTimeMinut() {
		return cfgNode.getNode(name,"ban","time","day").getInt();
	}	
	
	public UUID getBanAuthorUUID() {
		return UUID.fromString(cfgNode.getNode(name,"ban","author","UUID").getString());
	}
	
	public String getBanAuthorUUIDString() {
		return cfgNode.getNode(name,"ban","author","UUID").getString();
	}
	
	public String getBanAuthorName() {
		return cfgNode.getNode(name,"ban","author","name").getString();
	}
	
	public String getBanAuthorGrade() {
		return cfgNode.getNode(name,"ban","author","grade").getString();
	}
	
	public String getLangage() {
		return cfgNode.getNode(name,"config","langage").getString();
	}	
	
	public String getIP() {
		return cfgNode.getNode(name,"IP").getString();
	}
	
	public List<String> getIPList() {
		
		Function<Object,String> stringTransformer = new Function<Object,String>() {
		    @Override
		    public String apply(Object input) {
		        if (input instanceof String) {
		            return (String) input;
		        } else {
		            return null;
		        }
		    }
		};
		
		return cfgNode.getNode(name,"IP-list").getList(stringTransformer);
	}
	
	// setter
	
	public void setPlayerUUIDString(String uuid) {
		cfgNode.getNode(name,"UUID").setValue(uuid);
	}
	
	public void setPlayerUUID(UUID uuid) {
		UUID.fromString(cfgNode.getNode(name,"UUID").setValue(uuid).toString());
	}
	
	public void setPlayerName(String playerName) {
		cfgNode.getNode(name,"name").setValue(playerName);
	}
	
	@Deprecated
	public void setChunkOwned(List<String> list){
		cfgNode.getNode(name,"chunkOwner").setValue(list);
	}
	//loaderRootNode.getNode(name,"chunkOwner").setValue(Arrays.asList("none"));*/
	
	public void setForumProfileURL(URL url) {
		cfgNode.getNode(name,"forum","profile-link").setValue(url.getPath());
	}
	
	public void setForumProfile(String url) {
		cfgNode.getNode(name,"forum","profile-link").setValue(url);
	}
	
	public void setForumName(String forumName) {
		cfgNode.getNode(name,"forum","name").setValue(forumName);
	}
	
	public void setForumProfileIsBan(boolean b) {
		cfgNode.getNode(name,"forum","isBan").setValue(b);
	}
	
	public void setPrimGroup(String groupName) {
		cfgNode.getNode(name,"grade","prim-grade").setValue(groupName);
	}
	
	public void setSubGroup(List<String> subGroup){
		cfgNode.getNode(name,"grade","sub-grade").setValue(subGroup);
	}
	
	public void setPlayerIsBanned(boolean b) {
		cfgNode.getNode(name,"ban","enable").setValue(b);
	}
	
	public void setPlayerBanRaison(String raison) {
		cfgNode.getNode(name,"ban","raison").setValue(raison);
	}
	
	public void setPlayerBanTimeDay(int day) {
		cfgNode.getNode(name,"ban","time","day").setValue(day);
	}
	
	public void setPlayerBanTimeHour(int hour) {
		cfgNode.getNode(name,"ban","time","day").setValue(hour);
	}
	
	public void setPlayerBanTimeMinut(int minut) {
		cfgNode.getNode(name,"ban","time","day").setValue(minut);
	}	
	
	public void setPlayerBanAuthorUUID(UUID uuid) {
		cfgNode.getNode(name,"ban","author","UUID").setValue(uuid.toString());
	}
	
	public void setPlayerBanAuthorUUIDString(String uuid) {
		cfgNode.getNode(name,"ban","author","UUID").setValue(uuid);
	}
	
	public void setPlayerBanAuthorName(String authorName) {
		cfgNode.getNode(name,"ban","author","name").setValue(authorName);
	}
	
	public void setPlayerBanAuthorGrade(String gradeName) {
		cfgNode.getNode(name,"ban","author","grade").setValue(gradeName);
	}
	
	public void setLangage(String lang) {
		cfgNode.getNode(name,"config","langage").setValue(lang);
	}
	
	public void setIP(String ip) {
		cfgNode.getNode(name,"IP").setValue(ip);
	}
	
	public void setIPList(List<String> list) {		
		cfgNode.getNode(name,"IP-list").setValue(list);
	}
	
	// utils ban time
	
	public void setEndTimeBanYear(int year) {
		cfgNode.getNode(name,"ban","time_end","year").setValue(year);
	}
	
	public void setEndTimeBanMonth(int month) {
		cfgNode.getNode(name,"ban","time_end","month").setValue(month);
	}

	public void setEndTimeBanDay(int day) {
		cfgNode.getNode(name,"ban","time_end","day").setValue(day);
	}

	public void setEndTimeBanHour(int hour) {
		cfgNode.getNode(name,"ban","time_end","hour").setValue(hour);
	}
	public void setEndTimeBanMinute(int minute) {
		cfgNode.getNode(name,"ban","time_end","minute").setValue(minute);
	}
	
	public int getEndTimeBanYear() {
		return cfgNode.getNode(name,"ban","time_end","year").getInt();
	}
	
	public int getEndTimeBanMonth() {
		return cfgNode.getNode(name,"ban","time_end","month").getInt();
	}

	public int getEndTimeBanDay() {
		return cfgNode.getNode(name,"ban","time_end","day").getInt();
	}

	public int getEndTimeBanHour() {
		return cfgNode.getNode(name,"ban","time_end","hour").getInt();
	}
	
	public int getEndTimeBanMinute() {
		return cfgNode.getNode(name,"ban","time_end","minute").getInt();
	}
	
	public void setBeginTimeBanYear(int year) {
		cfgNode.getNode(name,"ban","time_begin","year").setValue(year);
	}
	
	public void setBeginTimeBanMonth(int month) {
		cfgNode.getNode(name,"ban","time_begin","month").setValue(month);
	}
	
	public void setBeginTimeMaxDayInMonth(int max_day) {
		cfgNode.getNode(name,"ban","time_begin","month-max-day").setValue(max_day);
	}

	public void setBeginTimeBanDay(int day) {
		cfgNode.getNode(name,"ban","time_begin","day").setValue(day);
	}

	public void setBeginTimeBanHour(int hour) {
		cfgNode.getNode(name,"ban","time_begin","hour").setValue(hour);
	}
	public void setBeginTimeBanMinute(int minute) {
		cfgNode.getNode(name,"ban","time_begin","minute").setValue(minute);
	}
	
	public int getBeginTimeBanYear() {
		return cfgNode.getNode(name,"ban","time_begin","year").getInt();
	}
	
	public int getBeginTimeBanMonth() {
		return cfgNode.getNode(name,"ban","time_begin","month").getInt();
	}
	
	public int getBeginTimeMaxDayInMonth() {
		return cfgNode.getNode(name,"ban","time_begin","month-max-day").getInt();
	}

	public int getBeginTimeBanDay() {
		return cfgNode.getNode(name,"ban","time_begin","day").getInt();
	}

	public int getBeginTimeBanHour() {
		return cfgNode.getNode(name,"ban","time_begin","hour").getInt();
	}
	
	public int getBeginTimeBanMinute() {
		return cfgNode.getNode(name,"ban","time_begin","minute").getInt();
	}
	
	// list de bannissement
	
	public void setPlayerBanList(List<String> banlist){
		cfgNode.getNode(name,"last-ban-list").setValue(banlist);
	}
	
	public List<String> getPlayerBanList(){
		
		Function<Object,String> stringTransformer = new Function<Object,String>() {
		    @Override
		    public String apply(Object input) {
		        if (input instanceof String) {
		            return (String) input;
		        } else {
		            return null;
		        }
		    }
		};
		
		return cfgNode.getNode(name,"last-ban-list").getList(stringTransformer);
	}
	
	public void clearBan() {
		setPlayerIsBanned(false);	 
		setPlayerBanRaison("");
		
		// ajout date de début bannissement A FAIRE
		
		setBeginTimeBanYear(0);
		setBeginTimeBanMonth(0);
		setBeginTimeBanDay(0);
		setBeginTimeMaxDayInMonth(0);
		setBeginTimeBanHour(0);
		setBeginTimeBanMinute(0);
		
		// ajout date de fin bannissement A FAIRE
		
		setEndTimeBanYear(0);
		setEndTimeBanMonth(0);
		setEndTimeBanDay(0);
		setEndTimeBanHour(0);
		setEndTimeBanMinute(0);
		
		// temps de bannissement
		setPlayerBanTimeDay(0);
		setPlayerBanTimeHour(0);
		setPlayerBanTimeMinut(0);
								
		// info du bannisseur
		setPlayerBanAuthorName("none");
		setPlayerBanAuthorUUIDString("none");
		
	}
	
	public String buildHistoryLineBan() {
		
		String UUID_author_of_ban = getBanAuthorUUIDString();
		String name_of_athor = getBanAuthorName();
		
		String date_begin = getBeginTimeBanYear() + ":" + getBeginTimeBanMonth() + ":" + getBeginTimeBanDay() + ":" + getBeginTimeBanHour() + ":" + getBeginTimeBanMinute() ; 	// A FAIRE
		String date_end = getEndTimeBanYear() + ":" + getEndTimeBanMonth() + ":" + getEndTimeBanDay() + ":" + getEndTimeBanHour() + ":" + getEndTimeBanMinute(); 		// A FAIRE
		String time = getPlayerBanTimeDay() + ":" + getPlayerBanTimeHour() + ":" + getPlayerBanTimeMinut();			// A FAIRE
		
		String player_name = getPlayerName();
		String raison = getPlayerBanRaison();
		
		String banLine;
		banLine = "|.|" + UUID_author_of_ban + "|.|" + name_of_athor + "|.|" + date_begin + "|.|" + date_end + "|.|" + time + "|.|" + player_name + "|.|" + raison + "|.|";
		return banLine;
	}
	
	public void revokeBanANDAddToList(){		
		
		String banLine = buildHistoryLineBan();
		
		List<String> list = new ArrayList<>(getPlayerBanList());				    	
    	list.add(banLine);
    	setPlayerBanList(list);
    	
    	clearBan();
    	
		//loaderRootNode.getNode(name,"last-ban-list").setValue(Arrays.asList("|.|UUID-author-of-ban=none|.|name-of-athor=none|.|date_begin=YY:MM:DD:HH:mm|.|date_end=YY:MM:DD:HH:mm|.|time=YY:MM:DD:HH:mm|.|player-name=none|.|raison=none|.|"));
	
	}
	
	public void calculEndTime() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(getBeginTimeBanYear(),getBeginTimeBanMonth(),getBeginTimeBanDay(),getBeginTimeBanHour(),getBeginTimeBanMinute());
		
		cal.add(Calendar.MINUTE, getPlayerBanTimeMinut());		
		cal.add(Calendar.HOUR, getPlayerBanTimeHour());
		cal.add(Calendar.DATE, getPlayerBanTimeDay());
		
		setEndTimeBanYear(cal.get(Calendar.YEAR));
		setEndTimeBanMonth(cal.get(Calendar.MONTH));
		setEndTimeBanDay(cal.get(Calendar.DAY_OF_MONTH));
		setEndTimeBanHour(cal.get(Calendar.HOUR));
		setEndTimeBanMinute(cal.get(Calendar.MINUTE));
	
	}	
	
}
