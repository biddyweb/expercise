package com.expercise.controller.challenge.model;

import com.expercise.enums.DataType;
import com.expercise.enums.Lingo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ChallengeModel {

    private Long challengeId;

    private List<MultiLingoText> titles = new ArrayList<>();

    private List<MultiLingoText> descriptions = new ArrayList<>();

    private List<DataType> inputTypes = new ArrayList<>();

    private DataType outputType;

    private List<TestCase> testCases = new ArrayList<>();

    private Boolean approved;

    private Long level;

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public List<MultiLingoText> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<MultiLingoText> descriptions) {
        this.descriptions = descriptions;
    }

    public List<MultiLingoText> getTitles() {
        return titles;
    }

    public void setTitles(List<MultiLingoText> titles) {
        this.titles = titles;
    }

    public List<DataType> getInputTypes() {
        return inputTypes;
    }

    public void setInputTypes(List<DataType> inputTypes) {
        this.inputTypes = inputTypes;
    }

    public DataType getOutputType() {
        return outputType;
    }

    public boolean hasOutputType() {
        return outputType != null;
    }

    public void setOutputType(DataType outputType) {
        this.outputType = outputType;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTitleFor(Lingo lingo) {
        return filterByLingo(lingo, titles);
    }

    public String getDescriptionFor(Lingo lingo) {
        return filterByLingo(lingo, descriptions);
    }

    private String filterByLingo(Lingo lingo, List<MultiLingoText> descriptions1) {
        for (MultiLingoText multiLingoText : descriptions1) {
            if (lingo == multiLingoText.getLingo()) {
                return multiLingoText.getText();
            }
        }
        return StringUtils.EMPTY;
    }

    public static class MultiLingoText {

        private Lingo lingo;

        private String text;

        public MultiLingoText() {
        }

        public MultiLingoText(Lingo lingo, String text) {
            this.lingo = lingo;
            this.text = text;
        }

        public Lingo getLingo() {
            return lingo;
        }

        public void setLingo(Lingo lingo) {
            this.lingo = lingo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public static class TestCase {

        private List<String> inputValues = new ArrayList<>();

        private String outputValue;

        public List<String> getInputValues() {
            return inputValues;
        }

        public void setInputValues(List<String> inputValues) {
            this.inputValues = inputValues;
        }

        public String getOutputValue() {
            return outputValue;
        }

        public void setOutputValue(String outputValue) {
            this.outputValue = outputValue;
        }

    }

}
