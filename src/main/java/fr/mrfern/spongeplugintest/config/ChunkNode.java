package fr.mrfern.spongeplugintest.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ChunkNode implements ConfigurationNode {
	
	private ConfigurationNode cfgNode;
	protected String name = "chunk";
	
	public ConfigurationNode getCfgNode() {
		return cfgNode;
	}

	public void setCfgNode(ConfigurationLoader<CommentedConfigurationNode> cfgNode) {
		try {
			this.cfgNode = cfgNode.load();
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
		 setLocationZ(location.getBlockX());
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
		new UUID(0, 0);
		return UUID.fromString(cfgNode.getNode(name,"chunk-config","author-of-blocked","UUID").getString());
	}
	/*
	
	loaderRootNode.getNode(name,"chunk-config","author-of-blocked","UUID").setValue("none");
	loaderRootNode.getNode(name,"chunk-config","author-of-blocked","name").setValue("none");
	loaderRootNode.getNode(name,"chunk-config","author-of-blocked","grade").setValue("none");
	loaderRootNode.getNode(name,"chunk-config","blocked","raison").setValue("none");
	loaderRootNode.getNode(name,"chunk-config","blocked","timer","enable").setValue(false);
	loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","days").setValue(0);
	loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","hour").setValue(0);
	loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","minute").setValue(0);		
	*/
}
