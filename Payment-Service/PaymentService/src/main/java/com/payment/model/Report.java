package com.payment.model;

import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class Report {
	private String result;
	private TreeMap<String, String> parameters;

	public Report() {
		super();
	}

	public Report(String result, TreeMap<String, String> parameters) {
		super();
		this.result = result;
		this.parameters = parameters;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TreeMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(TreeMap<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Report [result=" + result + ", parameters=" + parameters + "]";
	}

}
