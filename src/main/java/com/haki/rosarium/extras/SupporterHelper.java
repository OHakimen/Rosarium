package com.haki.rosarium.extras;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupporterHelper {
    //static String urlStub = "";

    static Gson gson = new Gson();
    static List<Supporter> supporters = new ArrayList<Supporter>();

    public static void loadSupporterData(){
        supporters = Arrays.stream(gson.fromJson("""
                [
                    {
                        "supporterUUID": "8123e85b-4871-4354-8cbe-3980c995726c"
                    }
                ]
                """, Supporter[].class)).toList();
    }

    public static List<Supporter> getSupporters() {
        return supporters;
    }
}
