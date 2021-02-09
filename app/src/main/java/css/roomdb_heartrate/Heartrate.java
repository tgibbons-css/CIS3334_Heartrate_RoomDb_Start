package css.roomdb_heartrate;


public class Heartrate {
    public Integer pulse;          // actual rate in beats per minute
    public Integer age;            // age when heart rate measurement was taken
    public Double maxHeartRate;    // calculated maximum rate based on age
    public Double percent;         // percent of maximum rate
    //public Date date;            // DEBT - Skip the date for now since the Java and SQL formats are different
    public Integer range;          // which range this heart rate is in, used as index into arrays below

    final String rangeNames[] = {"Resting", "Moderate", "Endurance", "Aerobic","Anaerobic","Red zone"};
    final String rangeDescriptions[] = {"In active or resting", "Weight maintenance and warm up", "Fitness and fat burning", "Cardio training and endurance","Hardcore interval training","Maximum Effort"};
    final Double rangeBounds[] = {.50, .60, .70, .80, .90, 1.00};


    public Heartrate(Integer pulse, Integer age) {
        this.pulse = pulse;
        this.age = age;
        calcHeartRange();
    }

    /**
     * Calulate the maximum heart rate and the percent the pulse is of the maximum
     * Then determine where this is in the list of ranges and return the index of the range
     * @return
     */
    public Integer calcHeartRange() {
        maxHeartRate = 220.0 - age;        // from  http://www.cdc.gov/physicalactivity/basics/measuring/heartrate.htm
        percent = pulse /maxHeartRate;
        for (int i=0; i<rangeNames.length; i++) {
            if ( percent < rangeBounds[i] ) {
                // heartrate is in this range
                range = i;
                return range;          // break out of this loop
            }
        }
        return rangeNames.length-1;                      // this should never happen
    }

    /**
     * Get the name of the cardio range associated with this person's age and heartrate
     * @return
     */
    public String getRangeName() {
        calcHeartRange();
        return rangeNames[range];
    }
    /**
     * Get the long description of the cardio range associated with this person's age and heartrate
     * @return
     */
    public String getRangeDescrtiption() {
        calcHeartRange();
        return rangeDescriptions[range];
    }

    public String toString() {
        return "Pulse of " + pulse + " - Cardio level: " + getRangeName();
    }


}
