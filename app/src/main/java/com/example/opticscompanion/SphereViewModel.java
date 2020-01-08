package com.example.opticscompanion;

public class SphereViewModel {
    private String sphereName;
    private String fNumber;
    private String coverage;

    /**
     *
     * @param sphereName - name of the sphere, can be anything
     * @param fNumber - string representing the fNumber, should only be a number. EX: 0.63
     * @param coverage - the percentage of the test lens that is covered by this particular sphere
     *
     *
     */
    public SphereViewModel(String sphereName, String fNumber, String coverage) {
        setSphereName(sphereName);
        setfNumber(fNumber);
        setCoverage(coverage);
    }

    public String getSphereName() {
        return sphereName;
    }

    public void setSphereName(String sphereName) {
        this.sphereName = sphereName;
    }

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
}
