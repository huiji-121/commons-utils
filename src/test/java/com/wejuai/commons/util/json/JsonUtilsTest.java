package com.jifenke.commons.util.json;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author YQ.Huang
 */
public class JsonUtilsTest {

    public static class Anything {
        private String s;
        private int i;
        private float f;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public float getF() {
            return f;
        }

        public void setF(float f) {
            this.f = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Anything anything = (Anything) o;

            if (i != anything.i) return false;
            if (Float.compare(anything.f, f) != 0) return false;
            return s != null ? s.equals(anything.s) : anything.s == null;

        }

        @Override
        public int hashCode() {
            int result = s != null ? s.hashCode() : 0;
            result = 31 * result + i;
            result = 31 * result + (f != +0.0f ? Float.floatToIntBits(f) : 0);
            return result;
        }
    }

    @Test
    public void toJson() throws Exception {
        Anything anything = new Anything();
        String json = JsonUtils.toJson(anything);
        Anything actual = JsonUtils.fromJson(json, Anything.class);
        Assert.assertEquals(anything, actual);
    }

}