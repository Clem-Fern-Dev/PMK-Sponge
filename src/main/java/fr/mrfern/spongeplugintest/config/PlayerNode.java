package fr.mrfern.spongeplugintest.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationOptions getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(Object def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(Supplier<Object> defSupplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getValue(Function<Object, T> transformer, T def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getValue(Function<Object, T> transformer, Supplier<T> defSupplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer, List<T> def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(Function<Object, T> transformer, Supplier<List<T>> defSupplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(TypeToken<T> type, List<T> def) throws ObjectMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(TypeToken<T> type, Supplier<List<T>> defSupplier) throws ObjectMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationNode setValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getValue(TypeToken<T> type, T def) throws ObjectMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getValue(TypeToken<T> type, Supplier<T> defSupplier) throws ObjectMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationNode mergeValuesFrom(ConfigurationNode other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasListChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasMapChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<? extends ConfigurationNode> getChildrenList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, ? extends ConfigurationNode> getChildrenMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeChild(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConfigurationNode getAppendedNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationNode getNode(Object... path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isVirtual() {
		// TODO Auto-generated method stub
		return false;
	}

}
