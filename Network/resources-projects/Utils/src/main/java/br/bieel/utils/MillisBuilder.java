package br.bieel.utils;

public class MillisBuilder {
    private long millis;
    private MillisHelper helper;

    public MillisBuilder(long millis){
        this.millis = millis;
    }

    public MillisBuilder addValue(long millis){
        this.millis += millis;
        return this;
    }
    public MillisBuilder removeValue(long millis){
        this.millis -= millis;
        return this;
    }
    public MillisHelper getHelper(){
        return this.helper == null || this.millis != helper.getMillis() ? new MillisHelper(this.millis) : this.helper;
    }
    public static MillisBuilder build(long millis){
        return new MillisBuilder(millis);
    }
}