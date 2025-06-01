package com.haki.rosarium.client.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class RenderUtils {

    public static Vector3f sphereWithUVFromRadius(float u, float v, float r) {
        return new Vector3f(Mth.cos(u) * Mth.sin(v) * r, Mth.cos(v) * r, Mth.sin(u) * Mth.sin(v) * r);
    }

    public static void makeVertexPosColorUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v, int light) {
        vertexConsumer.addVertex(last, x, y, z).setColor(r, g, b, a).setUv(u, v).setUv2(light,light).setOverlay(0);
    }

    public static void drawSphere(VertexConsumer consumer, PoseStack stack, float radius, int longs, int lats, float r, float g, float b, float a, int light) {
        Matrix4f last = stack.last().pose();
        float startU = 0;
        float startV = 0;
        float endU = Mth.PI * 2;
        float endV = Mth.PI;
        float stepU = (endU - startU) / longs;
        float stepV = (endV - startV) / lats;
        for (int i = 0; i < longs; ++i) {
            for (int j = 0; j < lats; ++j) {
                float u = i * stepU + startU;
                float v = j * stepV + startV;
                float un = (i + 1 == longs) ? endU : (i + 1) * stepU + startU;
                float vn = (j + 1 == lats) ? endV : (j + 1) * stepV + startV;

                Vector3f p0 = RenderUtils.sphereWithUVFromRadius(u, v, radius);
                Vector3f p1 = RenderUtils.sphereWithUVFromRadius(u, vn, radius);
                Vector3f p2 = RenderUtils.sphereWithUVFromRadius(un, v, radius);
                Vector3f p3 = RenderUtils.sphereWithUVFromRadius(un, vn, radius);

                float textureU = u / endU * radius;
                float textureV = v / endV * radius;
                float textureUN = un / endU * radius;
                float textureVN = vn / endV * radius;

                RenderUtils.makeVertexPosColorUVLight(consumer, last, p0.x(), p0.y(), p0.z(), r, g, b, a, textureU, textureV, light);
                RenderUtils.makeVertexPosColorUVLight(consumer, last, p2.x(), p2.y(), p2.z(), r, g, b, a, textureUN, textureV, light);
                RenderUtils.makeVertexPosColorUVLight(consumer, last, p1.x(), p1.y(), p1.z(), r, g, b, a, textureU, textureVN, light);

                RenderUtils.makeVertexPosColorUVLight(consumer, last, p2.x(), p2.y(), p2.z(), r, g, b, a, textureUN, textureV, light);
                RenderUtils.makeVertexPosColorUVLight(consumer, last, p1.x(), p1.y(), p1.z(), r, g, b, a, textureU, textureVN, light);
                RenderUtils.makeVertexPosColorUVLight(consumer, last, p3.x(), p3.y(), p3.z(), r, g, b, a, textureUN, textureVN, light);
            }
        }
    }
}
