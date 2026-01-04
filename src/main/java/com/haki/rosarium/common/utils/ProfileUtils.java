package com.haki.rosarium.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import org.codehaus.plexus.util.StringInputStream;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ProfileUtils {

    static HashMap<String, GameProfile> profileCache = new HashMap<>();

    public static CompletableFuture<Optional<GameProfile>> getProfile(String username) {

        return CompletableFuture.supplyAsync(() -> {
            try{

                //Cache!!
                if(profileCache.get(username.toLowerCase()) != null) {
                    return Optional.of(profileCache.get(username.toLowerCase()));
                }

                URLConnection connection = new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openConnection();
                connection.connect();

                BufferedReader streamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                Gson gson = new Gson();
                JsonObject object = gson.fromJson(streamReader.lines().collect(Collectors.joining("")), JsonObject.class);

                StringBuilder uuid = new StringBuilder(object.get("id").getAsString());


                uuid.insert(8, "-");
                uuid.insert(13, "-");
                uuid.insert(18, "-");
                uuid.insert(23, "-");

                GameProfile profile = Minecraft.getInstance().getMinecraftSessionService().fetchProfile(UUID.fromString(uuid.toString()), false).profile();

                profileCache.put(username.toLowerCase(), profile);

                return Optional.ofNullable(profile);
            }catch (Exception e){
                return Optional.empty();
            }
        });
    }
}
