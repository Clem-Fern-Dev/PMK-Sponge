package fr.mrfern.spongeplugintest.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
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
	protected String name = "player";
	
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
	
	/*public List<String> getChunkOwned(){
		return cfgNode.getNode(name,"chunkOwner").getLi
	}
	loaderRootNode.getNode(name,"chunkOwner").setValue(Arrays.asList("none"));*/
	
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
		return null;
	}
	
	public boolean getIsBanned() {
		return cfgNode.getNode(name,"ban","enable").getBoolean();
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
	
	public List<String> getBanList(){
		return null;
	}
	
	public String getLangage() {
		return cfgNode.getNode(name,"config","langage").getString();
	}	
}
