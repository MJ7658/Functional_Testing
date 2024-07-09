package org.infy.reportHelper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;

public class Policys {
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PipelineConfigResultHelper.class);

	public static void main(String[] args) throws IOException, InterruptedException {

		log.info(policySelect1());
		policySelect1();
	}

	public static String policySelect1() throws InterruptedException {

		String[] policys = { "Application", "Environment", "Pipeline", "Policy", "Release", "Variable", "Worker",
				"Workflow (Application)" };

		String[] Application = { "DELETE", "EDIT", "VIEW" };

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };

		String[] pipeline = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };

		String[] Policy = { "CREATE", "DELETE", "EDIT", "VIEW" };

		String[] Variable = { "CREATE", "DELETE", "EDIT", "VIEW" };

		String[] Release = { "CREATE", "DELETE", "EDIT", "VIEW" };

		String[] Worker = { "CREATE", "DELETE", "VIEW" };

		String[] Workflow = { "CREATE", "DELETE", "EDIT", "EXECUTE" };

		String app = null;

		for (int i = 0; i < policys.length; i++) {

			if (policys[i].equalsIgnoreCase("Application")) {

				for (int j = 0; j < Application.length; j++) {
					app += Application[j] + ":application:*;";
				}
			} else if (policys[i].equalsIgnoreCase("Policy")) {
				for (int j = 0; j < Policy.length; j++) {
					String app1 = Policy[j] + ":policy:*;";
					app += app1;
				}
			}

			else if (policys[i].equalsIgnoreCase("Variable")) {

				for (int j = 0; j < Variable.length; j++) {
					String app2 = Variable[j] + ":Variable:*;";
					app += app2;
				}
			} else if (policys[i].equalsIgnoreCase("Workflow (Application)")) {

				for (int j = 0; j < Workflow.length; j++) {
					String app3 = Workflow[j] + ":appWorkflow:*;";
					app += app3;

				}
			} else if (policys[i].equalsIgnoreCase("Environment")) {

				for (int j = 0; j < Environment.length; j++) {
					String app4 = Environment[j] + ":env:*;";
					app += app4;

				}
			} else if (policys[i].equalsIgnoreCase("Pipeline")) {

				for (int j = 0; j < pipeline.length; j++) {
					String app5 = pipeline[j] + ":pipeline:*;";
					app += app5;

				}
			} else if (policys[i].equalsIgnoreCase("Release")) {

				for (int j = 0; j < Release.length; j++) {
					String app6 = Release[j] + ":release:*;";
					app += app6;

				}
			} else if (policys[i].equalsIgnoreCase("Worker")) {

				for (int j = 0; j < Worker.length; j++) {
					String app7 = Worker[j] + ":worker:*;";
					app += app7;

				}
			}
		}
		String NewValue = app.replace("null", "");
		return NewValue;
	}
}