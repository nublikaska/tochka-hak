import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import org.gradle.api.Project
import java.io.File

object Library {

    fun getAffectedModules(project: File, subprojects: Set<Project>): List<String> {

        //println("UUUUUUUU" + project.absolutePath)

        val git: Git = Git.open(project)

        val diff = git.getDiffLastCommit()

        val affectedRootFiles = diff.mapTo(HashSet()) { it.substringBefore('/') }

        return affectedRootFiles.filter { affectedRootFileName ->

            subprojects.any { subproject -> subproject.name == affectedRootFileName }
        }
    }

    private fun Git.getDiffLastCommit(): List<String> {

        val head: ObjectId = repository.resolve("HEAD~1^{tree}" )!!
        val lastCommit = CanonicalTreeParser()
        repository.newObjectReader().use { reader -> lastCommit.reset(reader, head) }

        return diff().setOldTree(lastCommit).call().map { it.newPath }
    }
}