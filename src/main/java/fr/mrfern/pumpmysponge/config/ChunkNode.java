package fr.mrfern.pumpmysponge.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.yaml.snakeyaml.emitter.EmitterException;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Deprecated
public class ChunkNode implements ConfigurationNode {
	
	private ConfigurationNode cfgNode;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	protected String name = "chunk";
	
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
	
	/*
	 *  Chunk info
	 */
	// Getter
	public int getLocationX() {
		return cfgNode.getNode(name,"chunk-info","posX").getInt();
	}
	
	public int getLocationZ() {
		return cfgNode.getNode(name,"chunk-info","posZ").getInt();
	}
	
	public Location<World> getLocation(){		
		return new Location<World>(getWorld(), getLocationX(),0, getLocationZ());
	}

	private  World getWorld() {
		return Sponge.getServer().getWorld(getWorldName()).get();
	}

	private String getWorldName() {
		return cfgNode.getNode(name,"chunk-info","world").getString();
	}
	// Setter
	public void setLocationX(int x) {
		cfgNode.getNode(name,"chunk-info","posX").setValue(x);
	}
	
	public void setLocationZ(int z) {
		cfgNode.getNode(name,"chunk-info","posZ").setValue(z);
	}
	
	public void setLocation(Location<World> location){		
		 setLocationX(location.getBlockX());
		 setLocationZ(location.getBlockZ());
		 setWorld(location.getExtent());
	}

	private void setWorld(World world) {
		setWorldName(world.getName());
	}

	private void setWorldName(String worldname) {
		cfgNode.getNode(name,"chunk-info","world").getString();
	}
	
	/*
	 * ChunkConfig
	 */
	//getter
	
	public boolean isBloked() {
		return cfgNode.getNode(name,"chunk-config","blocked").getBoolean();
	}
	
	public UUID getUUIDofBlocker() {
		return UUID.fromString(cfgNode.getNode(name,"chunk-config","author-of-blocked","UUID").getString());
	}
	
	public String getNameofBlocker() {
		return cfgNode.getNode(name,"chunk-config","author-of-blocked","name").getString();
	}
	
	public String getGradeofBlocker() {
		return cfgNode.getNode(name,"chunk-config","author-of-blocked","grade").getString();
	}
	
	public String getBlockedRaison() {
		return cfgNode.getNode(name,"chunk-config","blocked","raison").getString();
	}
	
	public boolean getBlockerTimed() {
		return cfgNode.getNode(name,"chunk-config","blocked","timer").getBoolean();
	}
	
	public int getBlockedTimeDay() {
		return cfgNode.getNode(name,"chunk-config","blocked","timer","time","days").getInt();
	}
	
	public int getBlockedTimeHour() {
		return cfgNode.getNode(name,"chunk-config","blocked","timer","time","hour").getInt();
	}
	
	public int getBlockedTimeMinute() {
		return cfgNode.getNode(name,"chunk-config","blocked","timer","time","minute").getInt();
	}
	
	// Setter
	
	public void setIsBloked(boolean b) {
		cfgNode.getNode(name,"chunk-config","blocked").setValue(b);
	}
	
	public void setUUIDofBlocker(UUID uuid) {
		cfgNode.getNode(name,"chunk-config","author-of-blocked","UUID").setValue(uuid.toString());
	}
	
	public String setNameofBlocker() {
		return cfgNode.getNode(name,"chunk-config","author-of-blocked","name").getString();
	}
	
	public void setGradeofBlocker(String gradeName) {
		cfgNode.getNode(name,"chunk-config","author-of-blocked","grade").setValue(gradeName);
	}
	
	public void setBlockedRaison(String raison) {
		cfgNode.getNode(name,"chunk-config","blocked","raison").setValue(raison);
	}
	
	public void setBlockerTimed(boolean b) {
		cfgNode.getNode(name,"chunk-config","blocked","timer").setValue(b);
	}
	
	public void setBlockedTimeDay(int day) {
		if(day < 0) {
			throw new EmitterException("day non compris entre 0 et infini");
		}else {
			cfgNode.getNode(name,"chunk-config","blocked","timer","time","days").setValue(day);
		}
	}
	
	public void setBlockedTimeHour(int hour) {
		if(hour > 60 | hour < 0) {
			throw new EmitterException("hour non compris entre 0 et 60");
		}else {
			cfgNode.getNode(name,"chunk-config","blocked","timer","time","hour").setValue(hour);
		}		
	}
	
	public void setBlockedTimeMinute(int minute) {
		if(minute > 60 | minute < 0) {
			throw new EmitterException("minute non compris entre 0 et 60");
		}else {
			cfgNode.getNode(name,"chunk-config","blocked","timer","time","minute").setValue(minute);
		}
	}
	
	/*
	 * Chunk info
	 */
	
	// getter
	
	public String getNameDiscoverer() {
		return cfgNode.getNode(name,"chunk-player-info","discover-by").getString();
	}
	
	public String getClaimedBy() {
		return cfgNode.getNode(name,"chunk-player-claim","claim-by").getString();
	}
	
	@Deprecated
	public ArrayList<String> getGroupOverpassList(){
		return null;
	}
	
	public List<String> getUserList(){
		
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
		
		return cfgNode.getNode(name,"chunk-player-perm","user").getList(stringTransformer);
	}
	
	// setter
	
	public void setNameDiscoverer(String discovererName) {
		cfgNode.getNode(name,"chunk-player-info","discover-by").setValue(discovererName);
	}
	
	public void setClaimedBy(String claimerName) {
		cfgNode.getNode(name,"chunk-player-claim","claim-by").setValue(claimerName);
	}
	

	@Deprecated
	public void setGroupOverpassList(ArrayList<String> overpassList){
		cfgNode.getNode(name,"chunk-player-perm","group-overpass").setValue(overpassList);
	}
	

	public void setUserList(List<String> list){
		cfgNode.getNode(name,"chunk-player-perm","user").setValue(list);
	}
	/*
	loaderRootNode.getNode(name,"chunk-player-info","discover-by").setValue("none");
	loaderRootNode.getNode(name,"chunk-player-claim","claim-by").setValue("none");
	loaderRootNode.getNode(name,"chunk-player-perm","group-overpass").setValue(Arrays.asList("none"));
	loaderRootNode.getNode(name,"chunk-player-perm","co-owner-list").setValue(Arrays.asList("none"));
	loaderRootNode.getNode(name,"chunk-player-perm","user").setValue(Arrays.asList("none"));
	loaderRootNode.getNode(name,"chunk-player-perm","perm-invite").setValue(Arrays.asList("none"));
	loaderRootNode.getNode(name,"chunk-player-perm","temp-invite").setValue(Arrays.asList("none"));
	*/
}