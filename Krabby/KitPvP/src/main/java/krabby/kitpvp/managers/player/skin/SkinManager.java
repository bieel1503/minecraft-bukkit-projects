package krabby.kitpvp.managers.player.skin;

import java.util.HashMap;
import java.util.Map;

public final class SkinManager {
    private static final Map<String, SkinBase> skins = new HashMap<>();

    public static SkinBase getSkin(String name){
        return skins.get(name);
    }
    public static SkinRequest requestSkin(String name){
        return new SkinRequest(name);
    }
    public static SkinRequest updateSkin(Skin skin){
        return new SkinRequest(skin);
    }
    public static SkinBase saveSkin(SkinBase skinBase){
        return skins.put(skinBase.getName().toLowerCase(), skinBase);
    }
    public static SkinBase removeSkin(SkinBase skinBase){
        return skins.remove(skinBase.getName().toLowerCase());
    }
}