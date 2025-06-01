package com.haki.rosarium.extras;

import com.google.gson.Gson;

import java.util.*;

public class SupporterHelper {
    //static String urlStub = "";

    static Gson gson = new Gson();
    static List<Supporter> supporters = new ArrayList<Supporter>();

    public static void loadSupporterData() {
        supporters = Arrays.stream(gson.fromJson("""
                [
                    // Debug
                    {
                        "supporterUUID": "380df991-f603-344c-a090-369bad2a924a",
                        "isDev": true
                    },
                    // me Rose :3
                    {
                        "supporterUUID": "8123e85b-4871-4354-8cbe-3980c995726c",
                        "isDev": true
                    }
                ]
                """, Supporter[].class)).toList();
    }

    public static List<Supporter> getSupporters() {
        return supporters;
    }

    public static boolean isSupporter(UUID uuid) {
        return supporters.stream().anyMatch(supporter -> supporter.supporterUUID.equals(uuid));
    }

    public static Optional<Supporter> getSupporter(UUID uuid) {
        return supporters.stream().filter(supporter -> supporter.supporterUUID.equals(uuid)).findFirst();
    }
}
