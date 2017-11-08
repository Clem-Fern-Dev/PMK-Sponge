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
import org.yaml.snakeyaml.emitter.EmitterException;

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
	
	public void setBlocker() {
		
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
	 * perm config
	 */
	
	public String getGroupName(ChunkGroupTypes group) {
		if(ChunkGroupTypes.Co_Owner.equals(group)) {
			return "co-owner";
		}else if (ChunkGroupTypes.User.equals(group)) {
			return "user";
		}else if (ChunkGroupTypes.Invite_Perm.equals(group)) {
			return "perm-invite";
		}else if (ChunkGroupTypes.Invite_Temp.equals(group)) {
			return "temp-invite";
		}
		
		return null;		
		
	}
	
	// getter	
	public boolean getPermBlock(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"block").getBoolean();	
	}
	
	public boolean getPermVault(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"vault").getBoolean();	
	}
	
	public boolean getPermDoor(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"door").getBoolean();	
	}
	
	public boolean getPermButton(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"button").getBoolean();	
	}
	
	public boolean getPermPromoteUser(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"promote-user").getBoolean();	
	}
	
	public boolean getCommandsPermUnClaim(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","unclaim").getBoolean();	
	}
	
	public boolean getCommandsPermAddCoOwner(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","add-co-owner").getBoolean();	
	}
	
	public boolean getCommandsPermAddUser(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","adduser").getBoolean();	
	}
	
	public boolean getCommandsPermAddPrimInvite(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","addinvite-prim").getBoolean();	
	}
	
	public boolean getCommandsPermAddTempInvite(ChunkGroupTypes group) {
		return cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","addinvite-temp").getBoolean();	
	}
	
	// setter
	
	public void setPermBlock(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"block").setValue(b);	
	}
	
	public void setPermVault(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"vault").setValue(b);	
	}
	
	public void setPermDoor(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"door").setValue(b);	
	}
	
	public void setPermButton(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"button").setValue(b);	
	}
	
	public void setPermPromoteUser(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"promote-user").setValue(b);	
	}
	
	public void setCommandsPermUnClaim(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","unclaim").setValue(b);	
	}
	
	public void setCommandsPermAddCoOwner(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","add-co-owner").setValue(b);	
	}
	
	public void setCommandsPermAddUser(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","adduser").setValue(b);	
	}
	
	public void setCommandsPermAddPrimInvite(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","addinvite-prim").setValue(b);	
	}
	
	public void setCommandsPermAddTempInvite(ChunkGroupTypes group, boolean b) {
		cfgNode.getNode(name,"chunk-group-perm",getGroupName(group),"command-chunk","addinvite-temp").setValue(b);	
	}
}