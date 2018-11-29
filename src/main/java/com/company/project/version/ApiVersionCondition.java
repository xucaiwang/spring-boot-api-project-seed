package com.company.project.version;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+_\\d+_\\d++)/");

    private String apiVersion;

    public ApiVersionCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion());
    }

    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            String version = m.group(1);
            if (convertVersion(version) >= convertVersion(this.apiVersion)) {
                return this;
            }
        }
        return null;
    }

    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return convertVersion(other.getApiVersion()) - convertVersion(this.apiVersion);
    }

    private int convertVersion(String version){
        String[] versions = version.split("_");
        return Integer.parseInt(versions[0])*1000 + Integer.parseInt(versions[1])*100 + Integer.parseInt(versions[2]);
    }

    public String getApiVersion() {
        return apiVersion;
    }
}
