package com.haki.rosarium.extras;

import com.google.gson.Gson;

import java.util.UUID;

public class Supporter {

    UUID supporterUUID;
    boolean isDev;
    public UUID getSupporterUUID() {
        return supporterUUID;
    }

    public boolean isDev() {
        return isDev;
    }
}
