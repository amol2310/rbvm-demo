import jenkins.model.*
import hudson.model.*
import hudson.triggers.SCMTrigger
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.*

def jobName = "RBVM-demo"
def repoUrl = "https://github.com/amol2310/rbvm-demo.git"

def jenkins = Jenkins.instance
if (jenkins.getItem(jobName) == null) {
    println "--> Creating pipeline job: ${jobName}"

    def job = new WorkflowJob(jenkins, jobName)

    def scm = new GitSCM(
        [new UserRemoteConfig(repoUrl, null, null, null)],
        [new BranchSpec("*/main")],
        false,
        [],
        null,
        null,
        []
    )

    def definition = new CpsScmFlowDefinition(scm, "Jenkinsfile")
    definition.setLightweight(true)
    job.definition = definition

    job.addTrigger(new SCMTrigger("* * * * *"))  // every minute
    jenkins.add(job, jobName)
    job.save()

    println "--> Job '${jobName}' created and saved."
} else {
    println "--> Job '${jobName}' already exists."
}