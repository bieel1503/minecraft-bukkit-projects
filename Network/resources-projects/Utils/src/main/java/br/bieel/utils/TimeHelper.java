package br.bieel.utils;

public enum TimeHelper {
    SECONDS(1000), MINUTES(60*1000), HOURS((60*1000)*60), DAYS(((60*1000)*60)*24), WEEKS((((60*1000)*60)*24)*7), MONTHS((long)(((60*1000)*60)*24)*31), YEARS(((long)(((60*1000)*60)*24)*31)*12);

    private final long multiplier;
    TimeHelper(long multiplier){
        this.multiplier = multiplier;
    }

    public long asMillis(int amount){
        return this.multiplier*amount;
    }
    public long getMultiplier(){
        return this.multiplier;
    }
}