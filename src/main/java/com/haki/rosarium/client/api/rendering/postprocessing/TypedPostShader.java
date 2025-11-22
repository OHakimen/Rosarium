package com.haki.rosarium.client.api.rendering.postprocessing;

import com.haki.rosarium.mixins.client.PostShaderAccessor;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.Arrays;
import java.util.List;

public class TypedPostShader {
    public enum Type {
        SCREEN,
    }

    PostChain postPass;
    Type type;
    boolean active;


    public TypedPostShader(PostChain postPass, Type type) {
        this.postPass = postPass;
        this.type = type;

    }

    public PostChain getPostPass() {
        return postPass;
    }

    public TypedPostShader setPostPass(PostChain postPass) {
        this.postPass = postPass;
        return this;
    }

    public Type getType() {
        return type;
    }

    public TypedPostShader setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public TypedPostShader setActive(boolean active) {
        this.active = active;
        return this;
    }

    public void process(float pPartialTicks){
        if(active){
            postPass.process(pPartialTicks);
        }
    }

    public List<PostPass> getPostPasses(){
        return ((PostShaderAccessor) this.getPostPass()).getPasses();
    }

    public <T> void setUniform(PostPass postPass, String name, T value){
        switch (value){
            case Float val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }
            case Integer val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }
            case Float[] val -> {

                float[] vals = new float[val.length];

                for(int i = 0; i < vals.length; i++){
                    vals[i] = val[i].floatValue();
                }

                postPass.getEffect().getUniform(name)
                        .set(vals);
            }
            case Integer[] val -> {
                if(val.length == 2){
                    postPass.getEffect().getUniform(name)
                            .set(val[0], val[1]);
                }else if(val.length == 3){
                    postPass.getEffect().getUniform(name)
                            .set(val[0], val[1], val[2]);
                }else if(val.length == 4){
                    postPass.getEffect().getUniform(name)
                            .set(val[0], val[1], val[2], val[3]);
                }
            }

            case Vector3f val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }

            case Vector4f val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }

            case Matrix3f val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }

            case Matrix4f val -> {
                postPass.getEffect().getUniform(name)
                        .set(val);
            }

            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
