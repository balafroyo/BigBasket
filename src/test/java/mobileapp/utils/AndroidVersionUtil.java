package mobileapp.utils;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Pinaki
 * Util class to fetch information about the android versions
 */
public class AndroidVersionUtil {
    private static HashMap<String,Integer> sMap =null;

    private static HashMap<String, Integer> getVersionMapInternal() {
        if(sMap ==null)
            initMap();
        return sMap;
    }

    private static void initMap() {
        sMap =new HashMap<String, Integer>();

        sMap.put("8.0.0",26);
        sMap.put("7.1",25);
        sMap.put("7.0",24);
        sMap.put("6.0",23);
        sMap.put("5.1",22);
        sMap.put("5.0",21);
        sMap.put("4.4",19);
    }

    public static Integer getApiLevelFromVersion(String version){
        HashMap<String, Integer> versionMap = getVersionMapInternal();
        Set<String> strings = versionMap.keySet();
        for (String ver : strings) {
            if(version.contains(ver)){
                return versionMap.get(ver);
            }
        }
        return -1;
    }


}
